<%@ include file="header.jsp" %>
<!-- JSTL Core Uri --> 
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
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-7 offset-md-3">
			<div class="card mt-5">
				<div class="card-header text-center  ">
					<h3>Enter Employee Details</h3>
				</div>
				<div class="card-body" >
						<form action="<%=request.getContextPath() %>/insert-employee" method="post" >		
							 <c:if test="${employee != null}">
            						<input type="hidden" name="employeeId" value="${employee.employeeId }">	
        					</c:if>
							 
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Name :</label>
							<input type="text" placeholder= "Enter Name" name="employeeName" class="form-control" value="${employee.employeeName }" id="exampleInputEmail1" aria-describedby="emailHelp" required>
						</div>
						
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee email :</label>
							<input type="email" placeholder= "xyz@gmail.com" name="employeeEmail" class="form-control" value="${employee.employeeEmail }"  aria-describedby="emailHelp" required>
						</div>
						<div class="mb-3">	
							<label for="exampleInputEmail1" class="form-label">Designation :</label>
							<input type="text" placeholder= "Intern" name="employeeDesignation" class="form-control"  value="${employee.employeeDesignation }" aria-describedby="emailHelp" required>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Salary :</label>
							<input type="number" placeholder= "Enter Salary" name="employeeSal" class="form-control" value="${employee.employeeSal }"  aria-describedby="emailHelp" required >
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">joining Date :</label>
							<input type="date" placeholder= "joining Date" name="joiningDate" class="form-control" value="${employee.joiningDate }" max="<%= java.time.LocalDate.now() %>" required>
						</div>
						<button type="submit" href=""  class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>