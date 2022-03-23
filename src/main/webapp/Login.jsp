<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="Controllers.LoginController"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="Style.css">
</head>

<style>
body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

</style>

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
            <a class="nav-link" aria-current="page" href="index.jsp">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="Login.jsp">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="SignUp.html">Sign Up</a>
          </li>
        </ul>
	 </div>
	 </div>
	 </nav>
	<br/>
	
<div class="container">
	<div class="row">
	</div>
	<br/><br/>
	<div class="row justify-content-md-center">
		<div class="col-md-auto">
		<% 
		String res = (String)session.getAttribute("loginRes"); 
		if(res != null){%>
			<h2><%= res %></h2><%}%>
		</div>
	</div>
	<br/><br/>
	<div class="row">
		<div class="col-sm-12">
			<div class="container">
				<div class="container-fluid">
					<form class="Login" action="LoginController" method="post" onsubmit="return validateLogin()">
						<h1 class="h3 mb-3 fw-normal" align="center">Sign In</h1>
						<input type="text" class="form-control" name="loginUser" id="username" placeholder="username">	 
						<input type="password" class="form-control" name="loginPass" id="password" placeholder="password">
							
						<br/><input id="loginSubmitBtn" class="w-100 btn bt-lg btn btn-dark" type="submit" value="Login"> 
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>