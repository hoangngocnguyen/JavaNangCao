<%@page import="tam.CGioHang"%>
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
		// Lấy session giỏ hàng ra, lấy các tham số, sửa giỏ hàng, lưu vào session lại, trở về trang DatHang.jsp
		if (session.getAttribute("gh") != null ) {
			CGioHang gio = (CGioHang) session.getAttribute("gh");
			String th = request.getParameter("th");
			String sl = request.getParameter("txtsuaSL");
			
			// Giá giữ nguyên, chỉ sửa số lượng
			gio.them(th, 0, Integer.parseInt(sl));
			
			session.setAttribute("gh", gio);
			
			response.sendRedirect("DatHang.jsp");
		}
		
	%>
</body>
</html>