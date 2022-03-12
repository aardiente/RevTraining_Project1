<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pending Requests</title>

 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
 <div class="container-fluid">
     <a class="navbar-brand" href="index.jsp">ERS</a>
     <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarCollapse">
       <ul class="navbar-nav me-auto mb-2 mb-md-0">
         <li class="nav-item">
           <a class="nav-link" aria-current="page" href="#">Home</a>
         </li>
         <li class="nav-item">
           <a class="nav-link " href="CreateRequest.jsp">Make Request</a>
         </li>
         <li class="nav-item active">
           <a class="nav-link" href="ViewRequest.jsp">View Request</a>
         </li>
       </ul>
 </div>
 </div>
 </nav>

</head>
<body>
	<form action="ViewRequestController" method="post">
		<input type="submit" value="View Pending" name="pendingBtn" id="pendingBtn" value="View Pending"> | <input type="submit" value="View All"  name="viewAllBtn" id="viewAllBtn" value="View All">
		
	</form>
</body>
</html>