<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<h2 class="text-center">Statistics of GameCenter</h2>
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

</body>
</html>