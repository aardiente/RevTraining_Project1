<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="Models.*"%>
   <%@ page import="DAO.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Details</title>
<script type="text/javascript" src="FieldValidation.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="Style.css">
<link rel="shortcut icon" href="">
<!-- Just so it shuts up. -->
</head>
<% Employee emp = (Employee)session.getAttribute("CurEmp"); 
	if(emp == null)
	{
		response.setStatus(response.SC_BAD_GATEWAY);
		response.setHeader("Location", "Login.html");
	}
%>

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
	            <a class="nav-link" aria-current="page" href="ManagerHome.jsp">Home</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="CreateRequest.jsp">Make Request</a>
	          </li>
	          <li class="nav-item">
	             <a class="nav-link" href="ViewRequests.jsp">View Request</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link active" href="UpdateEmployee.jsp">Update Info</a>
	          </li>
	        </ul>
		 </div>
		 </div>
		 </nav>
	<%	EmployeeDAO dao = new EmployeeDAOImpl();
		Employee user = dao.searchByUsername( session.getAttribute("username").toString() ); 
		String tStreet, tCity, tState, tZip, tCountry;
		String[] temp = user.getAddress().split(","); 
		tStreet = temp[0]; 
		String[] temp2 = temp[1].split(" "); 
		
		tCity = temp2[1]; 	tState = temp2[2]; 
		tZip = temp2[3]; 	tCountry = temp2[4];
						if(temp2.length > 5)
							for(int i = 5; i < temp2.length; i++)
							{
								tCountry += " " + temp2[i];
							}
	%>

<div class="container-fluid">
	<form action="UpdateEmployee" class="UpdateForm was-validated" id="SignUpForm" method="get"
		onSubmit="return validateFields()">

		<div class="row g-3">
			<div class="col-12">
				<label for="usernameTB" class="form-label"> Username </label> <input
					type="text" name="username" id="usernameTB" class="form-control is-invalid" value="<%=user.getUsername() %>" readonly>
			</div>
			<div class="col-sm-6">
				<label for="fnameTB" class="form-label"> First Name </label> <input
					type="text" name="fname" id="fnameTB" class="form-control is-invalid"  value="<%=user.getFirstName() %>" required>
			</div>
			<div class="col-sm-6">
				<label for="lnameTB" class="form-label"> Last Name </label> <input
					type="text" name="lname" id="lnameTB" class="form-control is-invalid"  value="<%=user.getLastName() %>" required>
			</div>
			<div class="col-12">
				<label for="emailTB" class="form-label"> Email </label> <input
					type="text" name="email" id="emailTB" class="form-control is-invalid"  value="<%=user.getEmail() %>" required>
			</div>
			<div class="col-12">
				<label for="addressTB" class="form-label"> Street </label> <input
					type="text" name="address" id="addressTB" class="form-control is-invalid"  value="<%=tStreet %>" required>
			</div>
			
			<div class="col-3">
				<label for="cityTB" class="form-label"> City </label> <input
					type="text" name="city" id="cityTB" class="form-control is-invalid" value="<%=tCity %>" required>
			</div>
			<div class="col-4">
				<label for="stateTB" class="form-label"> State </label> <input
					type="text" name="state" id="stateTB" class="form-control is-invalid" value="<%=tState %>"  required>
			</div>
			<div class="col-4">
				<label for="zipTB" class="form-label"> Zip </label> <input
					type="text" name="zip" id="zipTB" class="form-control is-invalid" value="<%=tZip %>" required>
			</div>
			<div class="col-4">
				<label for="countryTB" class="form-label"> Country </label> <input
					type="text" name="country" id="countryTB" class="form-control is-invalid" value="<%=tCountry %>"  required>
			</div>
			<div class="col-12">
				<label for="phonenumberTB" class="form-label"> Phone Number
				</label> <input type="text" name="phonenumber" id="phonenumberTB"
					class="form-control is-invalid"  value="<%=user.getPhoneNum() %>" required>
			</div>

		</div>
		<br/>
		<input class="w-100 btn bt-lg btn btn-dark" type="submit"
			id="signUpSubmitBtn" value="Update">
			
			<div class="invisible col-6">
				<label for="passwordTB" class="form-label"> Password </label> <input
					type="password" name="password" id="passwordTB"
					class="form-control is-invalid" value="<%=user.getPassword().hashCode() %>">
			</div>
			<div class="invisible col-6">
				<label for="conpasswordTB" class="form-label"> Confirm Password </label> <input
					type="password" name="conpassword" id="conpasswordTB"
					class="form-control is-invalid" value="<%=user.getPassword().hashCode() %>" required>
			</div>
	</form>
</div>
</body>
</html>