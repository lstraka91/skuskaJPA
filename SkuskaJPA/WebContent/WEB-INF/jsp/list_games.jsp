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

<!--<jsp:include page="/Minesweeper" flush="true" />-->
 <h1>List of games</h1>
 <table>
            <c:forEach items="${gameList}" var="game">
                <tr>
                    <td>${game.getGameName()}
                     <td><a href="?action=play&name=${game.getGameName()}">Play</a>
                    
                    
                </tr>
            </c:forEach>
        </table>
 
</body>
</html>