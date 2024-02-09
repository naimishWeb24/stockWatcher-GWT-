package com.springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.EmployeeDao;
import com.springmvc.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao;
	
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
	        this.employeeDao = employeeDao;
	}
	 	
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
	}
	
	public void addEmployee(Employee employee) {
		 employeeDao.addEmployee(employee);
	}

	@Override
	public void EmployeeServiceImpl(EmployeeDao employeeDao) {
		// TODO Auto-generated method stub
	}
	 
	public List<Employee> getAllEmploye() {
		return employeeDao.getAllEmploye();
	}
	 
	public Employee getEmployeeById(int id) {
		return employeeDao.getEmployeeById(id);
	}
	 
	public Employee getEmpId(int id) {
		return employeeDao.getEmpId(id);
	}
	
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}
	
	public void deleteEmployee(int id) {
		employeeDao.deleteEmployee(id);
	}
}
