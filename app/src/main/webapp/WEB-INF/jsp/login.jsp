<html>

<head>
<title>LOGIN PAGE</title>
<link href = "webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">

<style>

	.Login-form {
		width:400px;
		height:200px;
		position: absolute;
		background-color: #5B5EA6;
		border-radius: 20px;
		top:50%;
		left:50%;
		margin-right: -50%;
		transform:translate(-50%,-50%); 
	}

</style>

</head>

<body>

<h2>${errorMessage}</h2>

<div class = "Login-form">

	<c:if test = "${not empty errorMessage}">
		<div class = "alert alert-danger" role = "alert">${errorMessage}</div> 
	</c:if>
	 
	<div class = "container-fluid">
	<form method = "post">
		<input type = "text" name = "email" class = "form-control mt-2" placeholder = "Email">
		<input type = "password" name="password" class="form-control mt-2" placeholder="Password">
		<button class="btn btn-dark btn-block mt-1">Login</button> 
	</form>
 	</div>
 </div>

</body>

</html>