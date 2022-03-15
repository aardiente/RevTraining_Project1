<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
   <%@ page import="Models.*"%>
   <%@ page import="DAO.*"%>
   <%@ page import="services.JSPHelper"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="Style.css">
<meta charset="ISO-8859-1">
<%
ManagerDAO dao = new ManagerDAOImpl();
	Manager user = dao.searchByUsername( session.getAttribute("username").toString() );
	
	StringBuffer url = request.getRequestURL();
	String res = JSPHelper.urlParser(url.toString());
%>
<title>Logged In as <%= user.getUsername() %> </title>
<body>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp">ERS</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
				aria-controls="navbarCollapse" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="ManagerHome.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ManagePending.jsp">View Pending</a></li>
					<li class="nav-item"><a class="nav-link active" href="#">View
							Archived</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br/>
	
<div class="form-check">
	<form class="updateTicket">
	<table class="table table-dark">
	<% 
		RequestDAO rdao = new RequestDAOImpl();
		ArrayList<ReimbursmentTicket> tickList = rdao.getAllArchived(); 
		if(tickList != null)
		{ %>
			<thead><tr>
				<th scope="col">Ticket Id</th>
				<th scope="col">Employee Name</th> 
				<th scope="col">Username</th> 
				<th scope="col">Amount</th>
				<th scope="col">Request Date</th> 
				<th scope="col">Archive Date</th> 
				<th scope="col">Status</th>
				<th scope="col">By</th>
			</tr></thead>
			<%for(ReimbursmentTicket t : tickList)
			{
			%> <tr>
				<td> <%=t.getId()%> </td>
				<td> <%=t.getOwner().getFirstName() + " " + t.getOwner().getLastName() %> </td>
				<td> <%=t.getOwner().getUsername()%> </td>
				<td> <%=t.getAmount()%> </td>
				<td> <%=t.getRequestDate()%> </td>
				<td> <%=t.getCloseDate()%> </td>
				<td> <%=t.getStatus()%> </td>
				<td> <%=t.getAuditor().getUsername()%> </td>
<%			}
		}%>
	</table>
</form>
</div>
</body>
</html>