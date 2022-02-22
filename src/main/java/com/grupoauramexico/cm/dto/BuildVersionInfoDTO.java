/**
 * 
 */
package com.grupoauramexico.cm.dto;

import java.io.Serializable;

/**
 * @author vaguire
 *
 */
public class BuildVersionInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3563352410623573819L;
	private String version;
	private String buildTime;
	private String buildHost;
	private String commitTime;
	private String commitEmail;
	private String commitId;
	private String commitMsg;
	private String urlRepo;
	private String branch;
	private String commitCount;

	/**
	 * 
	 */
	public BuildVersionInfoDTO() {
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the buildTime
	 */
	public String getBuildTime() {
		return buildTime;
	}

	/**
	 * @param buildTime the buildTime to set
	 */
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	/**
	 * @return the buildHost
	 */
	public String getBuildHost() {
		return buildHost;
	}

	/**
	 * @param buildHost the buildHost to set
	 */
	public void setBuildHost(String buildHost) {
		this.buildHost = buildHost;
	}

	/**
	 * @return the commitTime
	 */
	public String getCommitTime() {
		return commitTime;
	}

	/**
	 * @param commitTime the commitTime to set
	 */
	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	/**
	 * @return the commitEmail
	 */
	public String getCommitEmail() {
		return commitEmail;
	}

	/**
	 * @param commitEmail the commitEmail to set
	 */
	public void setCommitEmail(String commitEmail) {
		this.commitEmail = commitEmail;
	}

	/**
	 * @return the commitId
	 */
	public String getCommitId() {
		return commitId;
	}

	/**
	 * @param commitId the commitId to set
	 */
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	/**
	 * @return the commitMsg
	 */
	public String getCommitMsg() {
		return commitMsg;
	}

	/**
	 * @param commitMsg the commitMsg to set
	 */
	public void setCommitMsg(String commitMsg) {
		this.commitMsg = commitMsg;
	}

	/**
	 * @return the urlRepo
	 */
	public String getUrlRepo() {
		return urlRepo;
	}

	/**
	 * @param urlRepo the urlRepo to set
	 */
	public void setUrlRepo(String urlRepo) {
		this.urlRepo = urlRepo;
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return the commitCount
	 */
	public String getCommitCount() {
		return commitCount;
	}

	/**
	 * @param commitCount the commitCount to set
	 */
	public void setCommitCount(String commitCount) {
		this.commitCount = commitCount;
	}

}
