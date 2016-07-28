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
</head>
<body>
	<div class="container">
<%@include file="header.html"%>
		
		<jsp:include page="navbar.jsp" />

		<h2 class="text-center indieText">Statistics of GameCenter</h2>
		<hr>
		<div class="row">
			<div class="col-md-4">
				
				<table class="table table-striped table-hover">
					<thead>
						<tr>

							<th>Game</th>
							<th>Count of comments</th>

						</tr>
					</thead>
					<c:forEach items="${commentsCount}" var="comcount">
						<tr>
							<td>${comcount.gameName}
							<td>${comcount.countComments}
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-4">
				
				<table class="table table-striped table-hover">
					<thead>
						<tr>

							<th>Game</th>
							<th>Count of successful playing</th>

						</tr>
					</thead>
					<c:forEach items="${gameCountScore}" var="scoreCount">
						<tr>
							<td>${scoreCount.game}
							<td>${scoreCount.countScores}
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="col-md-4">
				
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							
							<th>Game</th>
							<th>Count of game ratings</th>

						</tr>
					</thead>
					
					<c:forEach items="${getCountRatings}" var="ratingCount">
						
						<tr>
							
							<td>${ratingCount.gameName}
							<td>${ratingCount.countRatings}
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
		<br>
		
					<div class="col-md-4">
				
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							
							<th>Player name</th>
							<th>The Most active players (playing times)</th>

						</tr>
					</thead>
					
					<c:forEach items="${getEachPlayerPlays}" var="plays">
						
						<tr>
							
							<td>${plays.player}
							<td>${plays.countScore}
						</tr>
					</c:forEach>
				</table>
			</div>

	<div class="col-md-6">
				
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							
							<th>Players count</th>
							<th>Games</th>
							<th>Ratings</th>
							<th>Comments</th>
							<th>Skilled player</th>
							<th>Most commented game</th>
							<th>Favorite Game</th>
						</tr>
					</thead>
					
							<tr>
							
							<td>${countOfPlayers}
							<td>${countOfGames}
							<td>${countOfRating}
							<td>${countOfComments}
							<td>${getPlayerGambler}
							<td>${getMostCommentedGame}
							<td>${getFavoriteGame}
						</tr>
					
				</table>
			</div>
		</div>
	<%@include file="footer.html"%>
	</div>
</body>
</html>