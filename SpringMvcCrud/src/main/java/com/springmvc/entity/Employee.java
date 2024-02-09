package com.springmvc.entity;

public class Employee {

	Integer employeeId;
	String employeeName;
	String employeeEmail;
	String employeeDesignation;
	double employeeSal;
	String joiningDate;

	public Integer getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}
	
	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}
	
	public double getEmployeeSal() {
		return employeeSal;
	}
	
	public void setEmployeeSal(double employeeSal) {
		this.employeeSal = employeeSal;
	}
	
	public String getJoiningDate() {
		return joiningDate;
	}
	
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeDesignation=" + employeeDesignation + ", employeeSal=" + employeeSal
				+ ", joiningDate=" + joiningDate + "]";
	}
}
