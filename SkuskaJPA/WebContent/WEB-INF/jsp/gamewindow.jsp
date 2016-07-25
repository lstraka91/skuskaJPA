<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game Center</title>
</head>
<body>

<jsp:include page="/${param.name}" flush="true" />

	<jsp:include page="add_comment.jsp"/>


<h2>Komentare ku hre ${param.name}</h2>
   <table>

            <c:forEach items="${commentList}" var="comment">
                <tr>
                <th>Meno</th>
                <th>Koment</th>
                </tr>
                <tr>
                    <td>${comment.getPlayer().getName()}
                    <td>${comment.getUserComment()}
                  
                </tr>
            </c:forEach>
        </table>
        
        <h2>Rating ku hre ${param.name}</h2>
   <table>

            <c:forEach items="${ratingList}" var="rating">
                <tr>
                <th>Meno</th>
                <th>Rating</th>
                </tr>
                <tr>
                    <td>${rating.getPlayerName()}
                    <td>${rating.getRating()}
                  
                </tr>
            </c:forEach>
        </table>
</body>
</html>