<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entity.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
    </head>
    <body>
    	<jsp:include page="header.jsp"/>
    
        <h1>Pridanie studenta</h1>
        
        <form>
        	<input type="hidden" name="action" value="insert">
        	Meno: <input type="text" name="firstName"> <br>
        	Priezvisko: <input type="text" name="lastName"> <br>
        	<input type="submit">
        </form>
    </body>
</html>
