<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@page isELIgnored="false" %>
<%@page import="com.springmvc.dao.EmployeeDaoImpl" %>
<%@page import="com.springmvc.entity.Employee" %>
<%@page import="com.spring.mvc.controller.EmployeeController" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Employee Management System</title>
</head>
<body >
		<div class="container ">
			<div class="row">
				<div class="mt-3 mb-3">
					<center ><h1 >Employee Data</h1></center>
				</div>
			</div>
			<div class="container-fluid">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Employee Name</th>
							<th scope="col">Email</th>
							<th scope="col">Designation</th>
							<th scope="col">Employee Salary</th>
							<th scope="col">Joining Date</th>
							<th scope="col">Action</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${list}" varStatus="status">
                        	<tr>
                         		<td scope="row">${status.count}</td>
                            	<td scope="row">${employee.employeeName}</td>
	                            <td scope="row">${employee.employeeEmail}</td>
	                            <td scope="row">${employee.employeeDesignation}</td>
	                            <td scope="row"class="font-weight-bold">&#8377; ${employee.employeeSal}</td>
	                            <td scope="row">${employee.joiningDate}</td>
	                            <td><a href="/SpringMvcCrud/edit-employee/${employee.employeeId}" class="btn btn-primary">Edit</a></td>
	                            <td><a href="/SpringMvcCrud/delete-employee/${employee.employeeId}" class="btn btn-danger">Delete</a></td>
	                        </tr>
                        </c:forEach>
					</tbody>
					
				</table>
				<a class="btn btn-primary" href="<%=request.getContextPath() %>/insert-page">Add Employee</a>
			</div>
		</div>
</body>
</html>