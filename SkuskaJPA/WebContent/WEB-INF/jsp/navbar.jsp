<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>

	<div class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="navbar-header">

			<a class="navbar-brand" href="/GameCenter/games"> 
			<span class="glyphicon glyphicon-triangle-right"></span>Game<strong>Center</strong>
			</a>

		</div>

		<ul class="nav navbar-nav">
			<li><a href="/GameCenter/games">Games Menu</a></li>
			<li><a href="/GameCenter/statistics">Statistics</a></li>
			<c:forEach items="${sessionScope.gameList}" var="game">
			<li><a href="/GameCenter/games?action=play&name=${game.getGameName()}">${game.getGameName()}</a></li>
				
			</c:forEach>
		</ul>

		<ul class="nav navbar-nav pull-right">
			<c:choose>
				<c:when test="${sessionScope.user!=null}">
					<li><a href="/GameCenter/logoutUser"><span class="glyphicon glyphicon-user"></span> ${sessionScope.user }</a></li>
					<li><a href="/GameCenter/logoutUser"><span
							class="glyphicon glyphicon-log-in"></span> Log Out</a></li>

				</c:when>
				<c:otherwise>

					<li><a href="/GameCenter/loginUser"><span
							class="glyphicon glyphicon-log-in"></span> Log in</a></li>
					<li><a href="/GameCenter/register"><span
							class="glyphicon glyphicon-log-in"></span> Registration</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
</body>
</html>