package com.erpsystem.crms.model;

public class TaskModel {

	private long taskid;
	private String title;
	private long assignto;
	private String description;
	private long expdate;
	public long getTaskid() {
		return taskid;
	}
	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getAssignto() {
		return assignto;
	}
	public void setAssignto(long assignto) {
		this.assignto = assignto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getExpdate() {
		return expdate;
	}
	public void setExpdate(long expdate) {
		this.expdate = expdate;
	}
	
	
}
