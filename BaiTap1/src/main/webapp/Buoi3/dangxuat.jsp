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
		// Đăng xuất: xóa session và quay lại trang chủ
		//if (session.getAttribute("ss") != null) {
			session.removeAttribute("ss");
			response.sendRedirect("trangchu.jsp");
		//}
	%>
</body>
</html>