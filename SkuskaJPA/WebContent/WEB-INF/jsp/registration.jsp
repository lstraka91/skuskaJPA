<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/style.css">


<title>Welcome to Game Center</title>
</head>
<body>
	<div class="container">
		<%@include file="header.html"%>

		<div class="row">
			<div class="center-form panel">
				<form method="post" action="/GameCenter/register">
					<div class="panel-body">
						<h2 class="text-center">Registration form</h2>

						<center>
							<div class="form-group">

								<input type="text" name="userName" value=""
									placeholder="Insert your username here"
									class="form-control input-lg" required/>
							</div>
							<div class="form-group">

								<input type="password" name="Password" value=""
									placeholder="Insert your password here"
									class="form-control input-lg" id="password" required />
							</div>
							<div class="form-group">

								<input type="password" name="PasswordC" value=""
									placeholder="Confirm password" class="form-control input-lg"
									id="confirm_password" required />
							</div>

							<div class="form-group">

								<input type="email" name="Email" value=""
									placeholder="Insert your email address" class="form-control input-lg"
									required />
							</div>
							<input type="submit" value="Register user"
								class="btn btn-lg btn-block btn-success" /> 
							
							<a href="/register"
								class="btn btn-lg btn-block btn-primary">Register</a>
						</center>
					</div>
				</form>
			</div>
		</div>
		<%@include file="footer.html"%>
	</div>
	<script type="text/javascript">
		var password = document.getElementById("password"), confirm_password = document
				.getElementById("confirm_password");

		function validatePassword() {
			if (password.value != confirm_password.value) {
				confirm_password.setCustomValidity("Passwords Don't Match");
			} else {
				confirm_password.setCustomValidity('');
			}
		}

		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
</body>
</html>


