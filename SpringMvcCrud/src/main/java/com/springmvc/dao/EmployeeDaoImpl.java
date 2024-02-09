package com.springmvc.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.springmvc.entity.Employee;
import com.springmvc.mapper.EmployeeRowMapper;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	private JdbcTemplate jdbcTemplate;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}  

	public EmployeeDaoImpl() {
		super();
	}

	public void addEmployee(Employee employee) {
		String query="insert into Employee(employeeName,employeeEmail,employeeDes,employeeSal,joiningDate) values(?,?,?,?,?)";  
	     jdbcTemplate.update(query, employee.getEmployeeName(), employee.getEmployeeEmail(), employee.getEmployeeDesignation(), employee.getEmployeeSal(), employee.getJoiningDate()); 
	}

	public List<Employee> getAllEmploye() {
		return jdbcTemplate.query("Select * from Employee",new EmployeeRowMapper());    
	       
	}
	
	public Employee getEmpId(int id) {
		String query = "Select * from Employee where employeeId = ?";
		return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<Employee>(Employee.class)); 
	}		
	
	public Employee getEmployeeById(int id) {
		String query = "Select * from Employee where employeeId = ?";
		 return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	
	public void updateEmployee(Employee employee) {
		String updateQuery ="Update Employee SET employeeName=?, employeeEmail=?, employeeDes=?, employeeSal=?, joiningDate=? WHERE employeeId=?";
		jdbcTemplate.update(updateQuery, employee.getEmployeeName(), employee.getEmployeeEmail(), employee.getEmployeeDesignation(), employee.getEmployeeSal(), employee.getJoiningDate(), employee.getEmployeeId());
	}
	
	public void deleteEmployee(int id) {
		String query = " Delete from Employee where employeeId ="+id;
		jdbcTemplate.update(query);
	}   
}
