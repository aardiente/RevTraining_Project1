<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="Style.css">
<head>
<meta charset="ISO-8859-1">
<title>Creating request for <%= session.getAttribute("username") %></title>



</head>

<script type="text/javascript" src="FieldValidation.js"></script>
<body>
	 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		 <div class="container-fluid">
	      <a class="navbar-brand" href="index.jsp">ERS</a>
	      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      <div class="collapse navbar-collapse" id="navbarCollapse">
	        <ul class="navbar-nav me-auto mb-2 mb-md-0">
	          <li class="nav-item">
	            <a class="nav-link" aria-current="page" href="EmployeeHome.jsp">Home</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link active" href="CreateRequest.jsp">Make Request</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="ViewRequests.jsp">View Request</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="UpdateEmployee.jsp">Update Info</a>
	          </li>
	        </ul>
		 </div>
		 </div>
	 </nav>
<br/><br/>
<div class="container-fluid">
 <form class="RequestForm was-validated" id="CreateRequestForm" action="CreateRequest" method="get" onsubmit="return validateAmount()">
	<div class="row g-3">
		<div class="col-4">
			<label for="amountTB" class="form-label"> Amount </label> 
			<input type="number" name="amountTB" class="form-control" required>
		</div>
		<div class="col-8"></div>
		<div class="col-8">
			<label for="descriptionTB" class="form-label"> Description </label> 
			<input type="text" name="descriptionTB" id="descriptionTB" class="form-control input-lg" required>
		</div>
		<div class="col-4"></div>
		<div class="col-4">
		<input class="btn bt-lg btn btn-dark" id="submitBtn" type="submit" value="Confirm request">
		</div>
	</div>
 </form>
</div>

</body>
</html>