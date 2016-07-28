<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<link href='https://fonts.googleapis.com/css?family=Indie+Flower|Candal|Sigmar+One' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/style.css">
<title>Game Center</title>
</head>
<body>
	<div class="container">
		<%@include file="header.html"%>
		
		<jsp:include page="navbar.jsp" />

	
		<jsp:include page="/${param.name}" flush="true" />

		<jsp:include page="showAvgRate.jsp" />


		<h2 class="text-center">Top 10 Score in game: ${param.name}</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Player Name</th>
					<th>Score</th>
					<th>Date Played</th>
				</tr>
			</thead>
			<c:forEach items="${scoreList}" var="score">

				<tr>
					<td>${score.getPlayer().getName()}
					<td>${score.getScore()}
					<td><fmt:formatDate value="${score.date}" pattern="dd.MM.yyyy" />
				</tr>
			</c:forEach>
		</table>

		<h2 class="text-center">Users comments for game ${param.name}</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Player Name</th>
					<th>Comments</th>
					<th>Commented</th>
				</tr>
			</thead>
			<c:forEach items="${commentList}" var="comment">

				<tr>
					<td>${comment.getPlayer().getName()}
					<td>${comment.getUserComment()}
					<td><fmt:formatDate value="${comment.getDateCommented()}"
							pattern="dd.MM.yyyy-HH:mm" />
				</tr>
			</c:forEach>
		</table>
		
		<!-- show add rating and comment only for login users -->
		<c:choose>
			<c:when test="${sessionScope.user!=null}">

				<jsp:include page="add_comment.jsp" />

				<jsp:include page="add_rating.jsp" />
			</c:when>
			<c:otherwise>
			<div class="alert alert-warning" role="alert">
  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  <span class="sr-only">Info:</span>
 If you want to add comment or rating you have to <a href="/GameCenter/loginUser">log in !!</a>
</div>
			</c:otherwise>
		</c:choose>

		<%@include file="footer.html"%>

	</div>
</body>
</html>