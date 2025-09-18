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
		String temp_username = request.getParameter("loginID").toLowerCase();
		String temp_password = request.getParameter("password");
		
		if (temp_username == null || temp_password == null) {
			response.sendRedirect("trangtinchi.jsp");
		} else {
			if (temp_username.equals("23t1080015") && temp_password.equals("12345678")) {
				response.sendRedirect("https://student.husc.edu.vn/News");
			} else {
				response.sendRedirect("trangtinchi.jsp?err=1");
			}
		}
	%>
</body>
</html>