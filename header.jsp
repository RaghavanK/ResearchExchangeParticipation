<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
        <title>Researchers Exchange Participations</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">
        <script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/fshare.js"></script>
        
        <!-- BootStrap -->
        
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"> -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <%-- Code to specify Header section of the page--%>
        <div id="header">
            <nav id="header_menu">
                <ul  class="left" >
                    <li><a href="home.jsp">Researchers Exchange Participations</a></li>
                </ul>
                <ul class="right">
                <c:choose>
                    <c:when test="${sessionScope.theUser == null}">
                        <li><a href="UserController?action=about">About Us</a></li>
                        <li><a href="UserController?action=how">How it Works</a></li>
                        <li><a href="login.jsp">Login</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="UserController?action=about">About Us</a></li>
                        <li><a href="UserController?action=how">How it Works</a></li>
                        <li><c:out value="${sessionScope.theUser.getName()}"/></li>
                        <li><a href="UserController?action=logout">Log Out</a></li>
                    </c:otherwise>    
                </c:choose>
                </ul>
            </nav>
        </div>

