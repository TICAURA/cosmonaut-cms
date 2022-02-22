properties([
	pipelineTriggers([githubPush()])
])
def tag=''
pipeline {
	agent {
		label 'docker-agent'
	}

	stages {
		stage("Compilacion") {
			agent{
				docker {
					label 'docker-agent'
					image 'wintermutetec/maven_docker:3.8.1-jdk-11'
					args '-v /var/run/docker.sock:/var/run/docker.sock'
				}
			}
		   steps {
			   script{
				   git url: 'https://github.com/ASG-BPM/cosmonaut-cms',branch:'master',credentialsId: 'winter_user'
				   tag = sh(script:'git describe --tags --always `git rev-list --tags` | grep DEV | head -1',returnStdout: true ).trim()
				   sh "git checkout $tag"
				   sh "mvn package jib:dockerBuild -Dtag=$tag"
			   }
		   }
		   post {
				success {
					archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
				}
		   }
		}
		stage('Sonarqube') {
			agent{
				docker {
					label 'docker-agent'
					image 'maven:3.8.1-jdk-11'
					args '--dns 192.168.0.154'
				}
			}
			steps{
				withSonarQubeEnv(installationName:'Asg Wintermute Server') {
				   git url: 'https://github.com/ASG-BPM/cosmonaut-cms',branch:'master',credentialsId: 'winter_user'
				   sh "git checkout $tag"
				   sh 'mvn package sonar:sonar -Dsonar.host.url=https://sonarqube.wintermute.services:9090 -e'
				}
			}
		}
		stage("Push de imagen") {
			agent{
				docker {
					label 'docker-agent'
					image 'wintermutetec/dockerclient:2021-02-12'
					args "-u root --dns 192.168.0.154 -v $env.REGISTRY_SA:/service_account.json -v /var/run/docker.sock:/var/run/docker.sock"
				}
			}
			steps{
				script{
					docker.withRegistry('https://gcr.io', 'gcr:cosmonaut-registry-sa') {
						docker.image("gcr.io/cosmonaut-299500/cosmonaut/cms:$tag").push(tag)
					}
				}
			}
		}
		stage('Deploy to Kubernetes') {
			agent{
				docker {
					label 'docker-agent'
					image 'gcr.io/google.com/cloudsdktool/cloud-sdk:327.0.0'
					args "--dns 192.168.0.154 -e HOME=/tmp -e TAG=$tag"
				}
			}
			steps{
				 script{
					 withCredentials([file(credentialsId: 'cosmonaut-k8s-sa', variable: 'serviceaccount')]) {
					   git url: 'https://github.com/ASG-BPM/cosmonaut-cms',branch:'master',credentialsId: 'winter_user'
					   sh "git checkout $tag"
					   sh "export SA_NAME=$env.COSMONAUT_SA"
					   sh 'gcloud auth activate-service-account $SA_NAME --key-file=$serviceaccount'
					   sh "gcloud container clusters get-credentials $env.COSMONAUT_CREDENTIALS --region=$COSMONAUT_REGION --project=$COSMONAUT_PROJECT"
					   sh "kubectl config view"
					   sh "kubectl get pods"
					   sh "sed -i 's/\$TAG/$tag/' src/jenkins/cosmonaut_cms_deployment.yml"
					   sh "cat src/jenkins/cosmonaut_cms_deployment.yml | kubectl apply -f -"
					 }
				 }
			}
		}
	}
}
