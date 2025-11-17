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
	String th = request.getParameter("txtth");
	String gia = request.getParameter("txtgia");
	String sl = request.getParameter("txtsl");

	// Xử lý khi thêm sản phẩm
	if (th != null && gia != null && sl != null) {
		CGioHang gio = new CGioHang();
		// Nếu mua hàng lần đầu thì tạo session
		if (session.getAttribute("gh") == null) {
			session.setAttribute("gh", gio);
		}

		// Nếu đã có session, lấy nó ra
		gio = (CGioHang) session.getAttribute("gh");

		// Thêm sản phẩm vào giỏ hàng
		gio.them(th, Integer.parseInt(gia), Integer.parseInt(sl));

		// Lưu giỏ hàng vào session
		session.setAttribute("gh", gio);
		
		// Về lại trang DatHang
		response.sendRedirect("DatHang.jsp");
	}
	%>
</body>
</html>