<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link
	href='https://fonts.googleapis.com/css?family=Indie+Flower|Candal|Sigmar+One'
	rel='stylesheet' type='text/css'>

<title>Welcome to Game Center</title>
</head>
<body>
	<div class="container">
		<%@include file="header.html"%>

		<jsp:include page="navbar.jsp" />

		<c:if test="${not empty param.error}">
			<div class="alert alert-danger" role="alert">
				<strong>Login Error!</strong> Invalid username or password, try
				again.
			</div>
		</c:if>

		<div class="row">
			<div class="center-form panel">
				<form method="post" action="login">
					<div class="panel-body">
						<h2 class="text-center">Please log in</h2>

						<center>
							<div class="form-group">
								<div id="failedLogin"></div>
								<input type="text" name="userName" value=""
									placeholder="Insert your username here"
									class="form-control input-lg" required autofocus />
							</div>
							<div class="form-group">

								<input type="password" name="password" value=""
									placeholder="Insert your password here"
									class="form-control input-lg" requried />
							</div>

							<input type="submit" value="Login"
								class="btn btn-lg btn-block btn-success" /> <a
								href="register"
								class="btn btn-lg btn-block btn-primary">Register</a> <a
								href="games"
								class="btn btn-lg btn-block btn-primary">Continue as guest</a>


						</center>
					</div>
				</form>
			</div>
		</div>
		<%@include file="footer.html"%>
	</div>

</body>
</html>


