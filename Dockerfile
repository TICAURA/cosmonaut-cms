FROM openjdk:11.0.10-jdk-slim
WORKDIR /home/app
COPY target/cm-1.0.0.jar /home/app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/application.jar","-Djdk.tls.client.protocols=TLSv1.2"]
