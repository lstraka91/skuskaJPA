<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entity.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>

 <form>
        	<input type="hidden" name="action" value="addComment">
        	Komentar: <input type="textarea"  name="commentText"> <br>
        	Komentar: <textarea rows="10"  name="comment"> </textarea><br>
        	<input type="submit">
        </form>
</body>
</html>