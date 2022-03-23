<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="Models.*"%>
   <%@ page import="DAO.*"%>
   <%@ page import="services.JSPHelper"%>
   <%@ page import="Controllers.LoginController"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="Style.css">
<meta charset="ISO-8859-1">
<%
	ManagerDAO dao = new ManagerDAOImpl();
	Manager user = (Manager)LoginController.curUser;//(Manager)session.getAttribute("CurMan");//dao.searchByUsername( session.getAttribute("username").toString() );
	
	if(user == null || session == null)
	{
		response.sendRedirect("http://localhost:8080/Project_1/index.jsp");
	}
%>
	

<title>Logged In as <%= user.getUsername() %> </title>

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
	            <a class="nav-link active" aria-current="page" href="ManagerHome.jsp">Home</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="ManagePending.jsp">View Pending</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="ManageArchives.jsp">View Archived</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="UpdateManager.jsp">Update Info</a>
	          </li>
	        </ul>
	       	<form action="SignOut" method="get">
	        	<input type="submit" name="signOutBtn" class="btn btn-primary" value="Sign Out">
	        </form>
		 </div>
		 </div>
		 </nav>
		 <br/><br/>
		 <h3> Hello <%= user.getFirstName() + " " + user.getLastName() %> </h3>
</body>
</html>