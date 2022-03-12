<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="Style.css">
<head>
<meta charset="ISO-8859-1">
<title>Creating request for <%= session.getAttribute("username") %></title>

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
	        </ul>
		 </div>
		 </div>
	 </nav>

</head>

<script type="text/javascript" src="FieldValidation.js"></script>
<body>
	oh hi der
	<form id="CreateRequestForm" action="CreateRequest" method="post" onsubmit="return validateAmount()">
	<table>
		<tr> <td>Amount: </td> <td><input type="number" name="AmountField" id="amountTB"></td> </tr>
		<!-- Here I can add a field to take recipt image information... IF I HAD ONE -->
		<!-- Here I can add a field to take location information... IF I HAD ONE -->
		<tr> <td> <input id="submitBtn" type="submit" value="Confirm request"> </td> </tr>
	</table>
	</form>

	<a href="EmployeeHome.jsp">Return</a>
</body>
</html>