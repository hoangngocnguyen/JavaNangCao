<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		 String[] lst = request.getParameterValues("mail");
		for (String mail: lst) {
			out.print(mail + "<br>");
		}
	%>
</body>
</html>