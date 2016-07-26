<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>

	<form>
		<center>
			
			<input type="hidden" name="action" value="play">
			<input type="hidden" name="name" value="${name}">
			<input type="hidden" name="addComment" value="addComment">

			Komentar:
			<textarea rows="10" cols="20" name="comment"> </textarea>
			<br> <input type="submit">
		</center>
	</form>
</body>
</html>