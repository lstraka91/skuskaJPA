<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
<div class="container">
	<form  method="get">
	<div class="row">
			<div class="center-form panel">
		<center>
			<h3>Add Rating</h3>
			<input type="hidden" name="action" value="play">
			<input type="hidden" name="name" value="${name}">
			<input type="hidden" name="addRating" value="addRating">

			<div class="form-group">
			<input type="radio" id="rad1" name="rating" value="1" onclick="this.form.submit()" class="hide_radio">
			<label class="label_star" for="rad1"> <img src="images/star.png" width="50px"> </label>
            
            <input type="radio" id="rad2" name="rating" value="2" onclick="this.form.submit()" class="hide_radio">
            <label class="label_star" for="rad2"> <img src="images/star.png" width="50px"> </label>
           
            <input type="radio" id="rad3" name="rating" value="3" onclick="this.form.submit()" class="hide_radio">
            <label class="label_star" for="rad3"> <img src="images/star.png" width="50px"> </label>
            
            <input type="radio" id="rad4" name="rating" value="4" onclick="this.form.submit()" class="hide_radio">
            <label class="label_star" for="rad4"> <img src="images/star.png" width="50px"> </label>
            
            <input type="radio" id="rad5" name="rating" value="5" onclick="this.form.submit()" class="hide_radio">
			<label class="label_star" for="rad5"> <img src="images/star.png" width="50px"> </label>
			</div>
			
		</center>
		</div>
		</div>
	</form>
	</div>
</body>
</html>