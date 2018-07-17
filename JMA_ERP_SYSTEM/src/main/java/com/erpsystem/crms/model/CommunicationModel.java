package com.erpsystem.crms.model;

import java.time.LocalDate;

public class CommunicationModel {
	
	private long commid;
	private long medium;
	private LocalDate datevalue;
	private String description;
	private String processStatus;
	private String conclusion;
	private LocalDate followupdate;
	private String remarkvalue;
	private long personid;
	private long enquiryid;
	public long getCommid() {
		return commid;
	}
	public void setCommid(long commid) {
		this.commid = commid;
	}
	public long getMedium() {
		return medium;
	}
	public void setMedium(long medium) {
		this.medium = medium;
	}
	public LocalDate getDatevalue() {
		return datevalue;
	}
	public void setDatevalue(LocalDate datevalue) {
		this.datevalue = datevalue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public LocalDate getFollowupdate() {
		return followupdate;
	}
	public void setFollowupdate(LocalDate followupdate) {
		this.followupdate = followupdate;
	}
	public String getRemarkvalue() {
		return remarkvalue;
	}
	public void setRemarkvalue(String remarkvalue) {
		this.remarkvalue = remarkvalue;
	}
	public long getPersonid() {
		return personid;
	}
	public void setPersonid(long personid) {
		this.personid = personid;
	}
	public long getEnquiryid() {
		return enquiryid;
	}
	public void setEnquiryid(long enquiryid) {
		this.enquiryid = enquiryid;
	}
	
	
}
