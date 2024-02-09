package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.Employee;

public interface EmployeeDao {
	
	public void addEmployee(Employee employee);
	
	public List<Employee> getAllEmploye();
	
	public Employee getEmployeeById(int id);
	
	public Employee getEmpId(int id);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(int id);
}
