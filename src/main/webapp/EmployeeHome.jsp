<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="Models.*"%>
   <%@ page import="DAO.*"%>
   <%@ page import="services.JSPHelper"%>
   <%@ page import="Controllers.LoginController"%>
    <%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="Style.css">
<meta charset="ISO-8859-1">


<%
	Employee user = (Employee)LoginController.curUser;//(Employee)session.getAttribute("CurEmp");//dao.searchByUsername( session.getAttribute("username").toString() );
	
	if(session == null | user == null) 
	{
		response.sendRedirect("http://localhost:8080/Project_1/index.jsp");
	}
%>
<title>Welcome <%= user.getFirstName() + " " + user.getLastName() %></title>

</head>
<body>
	 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		 <div class="container-fluid">
	      <a class="navbar-brand">ERS</a>
	      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      <div class="collapse navbar-collapse" id="navbarCollapse">
	        <ul class="navbar-nav me-auto mb-2 mb-md-0">
	          <li class="nav-item">
	            <a class="nav-link active" aria-current="page" href="#">Home</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="CreateRequest.jsp">Make Request</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="ViewRequests.jsp">View Request</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="UpdateEmployee.jsp">Update Info</a>
	          </li>
	        </ul>
	        <form action="SignOut" method="get">
	        	<input type="submit" name="signOutBtn" class="btn btn-primary" value="Sign Out">
	        </form>
		 </div>
		 </div>
		 </nav>
		 <br/>
		 <h3> Hello <%= user.getFirstName() + " " + user.getLastName() %> </h3>

</body>
</html>