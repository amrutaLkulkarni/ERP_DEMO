package com.erpsystem.crms.model;

import java.time.LocalDate;

public class EmployeeModel {

	private long employeeId;
	private LocalDate dateofjoining;
	private String designation;
	private long branch;
	private long reportingTo;
	private long personId;
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public LocalDate getDateofjoining() {
		return dateofjoining;
	}
	public void setDateofjoining(LocalDate dateofjoining) {
		this.dateofjoining = dateofjoining;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public long getBranch() {
		return branch;
	}
	public void setBranch(long branch) {
		this.branch = branch;
	}
	public long getReportingTo() {
		return reportingTo;
	}
	public void setReportingTo(long reportingTo) {
		this.reportingTo = reportingTo;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	
}
