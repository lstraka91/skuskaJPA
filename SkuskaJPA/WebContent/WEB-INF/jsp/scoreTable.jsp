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