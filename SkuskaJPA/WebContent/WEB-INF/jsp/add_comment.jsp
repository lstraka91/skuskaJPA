<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>

	<form>
	<div class="row">
			<div class="center-form panel">
		<center>
			<h3>Add Comment</h3>
			<input type="hidden" name="action" value="play">
			<input type="hidden" name="name" value="${name}">
			<input type="hidden" name="addComment" value="addComment">

			<div class="form-group">
			<textarea rows="5"name="comment" class="form-control input-lg"> </textarea>
			</div>
			<br> <input type="submit" class="btn btn-lg btn-block btn-primary" value="Add Comment">
		</center>
		</div>
		</div>
	</form>
	
</body>
</html>