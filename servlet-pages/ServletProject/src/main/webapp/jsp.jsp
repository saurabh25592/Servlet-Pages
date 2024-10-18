<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display data on page </title>
</head>
<body>
<%

  request.setAttribute("email", "saurabhpachpute25@gmail.com");

  session.setAttribute("password", "123");

%>


<h1>welcome : ${requestScope.Email} </h1>
<h1> password : ${password} </h1>
</body>
</html>