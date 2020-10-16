<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OOPS!</title>
</head>
<body>
<h1><%=request.getAttribute("msg") %></h1>
<h2 style="color:red"><%=exception.getMessage() %></h2>
</body>
</html>