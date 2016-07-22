<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List 2</title>
    </head>
    <body>
   		<jsp:include page="header.jsp"/>
    
        <h1>List of students</h1>

		<a href="?action=add">Pridaj studenta</a>

        <table>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.firstName}
                    <td>${student.lastName}
                    <td><a href="?action=view&id=${student.id}">View</a>
                    <td><a href="?action=edit&id=${student.id}">Edit</a>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
