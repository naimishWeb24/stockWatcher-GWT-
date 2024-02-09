package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.springmvc.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	 public Employee mapRow(ResultSet rs, int row) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employeeId"));
        employee.setEmployeeName(rs.getString("employeeName"));
        employee.setEmployeeEmail(rs.getString("employeeEmail"));
        employee.setEmployeeDesignation(rs.getString("employeeDes"));
        employee.setEmployeeSal(rs.getDouble("employeeSal"));
        employee.setJoiningDate(rs.getString("joiningDate"));
        return employee;
	 }
}
