<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="Style.css">
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
         <li class="nav-item">
           <a class="nav-link active" href="ViewRequest.jsp">View Request</a>
         </li>
       </ul>
 </div>
 </div>
 </nav>

</head>
<body>
	<br/>
	<form action="ViewRequestsController" method="post">
		<input type="submit" value="View Pending" name="pendingBtn" id="pendingBtn" class="btn btn-dark" value="View Pending"> | 
		<input type="submit" value="View All"  name="viewAllBtn" id="viewAllBtn" class="btn btn-dark" value="View All">
		
	</form>
</body>
</html>