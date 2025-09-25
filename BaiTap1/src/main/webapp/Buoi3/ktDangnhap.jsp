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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null && password != null) {
			if (username.equals("abc") && password.equals("123")) {
				out.print("đúng rồi");
				// Kiểm tra chưa có session thì tạo
				if (session.getAttribute("ss") == null) {
					session.setAttribute("ss", username);
				}
				
				response.sendRedirect("trangchu.jsp");
			} else {
				response.sendRedirect("dangnhap.jsp?err=1");
			}
		}
	%>
</body>
</html>