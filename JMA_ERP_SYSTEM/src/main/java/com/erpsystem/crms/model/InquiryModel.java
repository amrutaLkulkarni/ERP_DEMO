package com.erpsystem.crms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InquiryModel {

	private long InquiryId;
	private LocalDate datevalue; 
	private Long branch;
	private Long enquirysource;
	private Long enquiryfor;
	private Long targetProduct;
	private String description;
	private String processStatus;
	private LocalDate followupdate;
	private String remarkvalue;
	private Long statusvalue;
	private LocalDateTime lastUpdate;
	public long getInquiryId() {
		return InquiryId;
	}
	public void setInquiryId(long inquiryId) {
		InquiryId = inquiryId;
	}
	public LocalDate getDatevalue() {
		return datevalue;
	}
	public void setDatevalue(LocalDate datevalue) {
		this.datevalue = datevalue;
	}
	public Long getBranch() {
		return branch;
	}
	public void setBranch(Long branch) {
		this.branch = branch;
	}
	public Long getEnquirysource() {
		return enquirysource;
	}
	public void setEnquirysource(Long enquirysource) {
		this.enquirysource = enquirysource;
	}
	public Long getEnquiryfor() {
		return enquiryfor;
	}
	public void setEnquiryfor(Long enquiryfor) {
		this.enquiryfor = enquiryfor;
	}
	public Long getTargetProduct() {
		return targetProduct;
	}
	public void setTargetProduct(Long targetProduct) {
		this.targetProduct = targetProduct;
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
	public Long getStatusvalue() {
		return statusvalue;
	}
	public void setStatusvalue(Long statusvalue) {
		this.statusvalue = statusvalue;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
