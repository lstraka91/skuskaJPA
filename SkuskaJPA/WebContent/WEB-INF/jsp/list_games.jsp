<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<link href='https://fonts.googleapis.com/css?family=Indie+Flower|Candal|Sigmar+One' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/style.css">
<title>Game Center</title>
</head>
<body>
	<div class="container">

		<%@include file="header.html"%>

		<jsp:include page="navbar.jsp" />
		<!--<jsp:include page="/Minesweeper" flush="true" />-->
		<h1 class="text-center">Choose game you want to PLAY</h1>
		<table class="table table-hover">
			<c:forEach items="${gameList}" var="game">
				<tr>
					<td><a href="?action=play&name=${game.getGameName()}"
						class="btn btn-lg btn-block btn-primary">${game.getGameName()}</a>
				</tr>
			</c:forEach>
		</table>
		<%@include file="footer.html"%>
	</div>
</body>
</html>