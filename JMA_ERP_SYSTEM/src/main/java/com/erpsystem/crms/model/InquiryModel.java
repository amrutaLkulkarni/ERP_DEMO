package com.erpsystem.crms.model;



public class InquiryModel {

	private long InquiryId;
	private String datevalue; 
	private long branch;
	private long enquirysource;
	private long enquiryfor;
	private long targetProduct;
	private String description;
	private String processStatus;
	private String followupdate;
	private String remarkvalue;
	private long statusvalue;
	private String lastUpdate;
	public long getInquiryId() {
		return InquiryId;
	}
	public void setInquiryId(long inquiryId) {
		InquiryId = inquiryId;
	}
	public String getDatevalue() {
		return datevalue;
	}
	public void setDatevalue(String datevalue) {
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
	public String getFollowupdate() {
		return followupdate;
	}
	public void setFollowupdate(String followupdate) {
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
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
