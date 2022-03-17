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
<title>Logged In as <% if(user != null)%><%=user.getUsername() %> </title>
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
					<li class="nav-item">
			            <a class="nav-link" href="UpdateManager.jsp">Update Info</a>
			        </li>
				</ul>
			</div>
		</div>
	</nav>
	<br/>
	
<div class="">
	<form class="updateTicket">
	<table class="table table-dark">
	<% 
		RequestDAO rdao = new RequestDAOImpl();
		ArrayList<ReimbursmentTicket> tickList = rdao.getAllArchived(); 
		if(tickList != null)
		{ %>
			<thead>
			<tr class="d-flex">
				<th class="col-1" scope="col">Ticket Id</th>
				<th class="col-1" scope="col">Username</th> 
				<th class="col-1" scope="col">Amount</th>
				<th class="col-4" scope="col">Description</th>
				<th class="col-1" scope="col">Employee Name</th> 
				<th class="col-1" scope="col">Request Date</th> 
				<th class="col-1" scope="col">Archive Date</th> 
				<th class="col-1" scope="col">Status</th>
				<th class="col-1" scope="col">By</th>
			</tr></thead>
			<%for(ReimbursmentTicket t : tickList)
			{
			%> <tr class="d-flex">
				<td class="col-1"> <%=t.getId()%> </td>
				<td class="col-1"> <%=t.getOwner().getUsername()%> </td>
				<td class="col-1"> $<%=t.getAmount()%> </td>
				<td class="col-4"> <%=t.getDescription()%> </td>
				<td class="col-1"> <%=t.getOwner().getFirstName() + " " + t.getOwner().getLastName() %> </td>
				<td class="col-1"> <%=t.getRequestDate()%> </td>
				<td class="col-1"> <%=t.getCloseDate()%> </td>
				<td class="col-1"> <%=t.getStatus()%> </td>
				<td class="col-1"> <%=t.getAuditor().getUsername()%> </td>
<%			}
		}%>
	</table>
</form>
</div>
</body>
</html>