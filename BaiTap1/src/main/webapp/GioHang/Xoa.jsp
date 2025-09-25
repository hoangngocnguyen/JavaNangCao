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
		// Note: không cần kiểm tra session vì những hành động này xuất phát từ sản phẩm, mà sản phẩm từ session ra
		
		// 1. Xóa 1 sản phẩm trong giỏ hàng. Xóa sản phẩm thông qua: tên hàng
		// Lấy session ra để xóa, rồi lưu lại
		String th = request.getParameter("th");
		CGioHang gio = new CGioHang();
		gio = (CGioHang) session.getAttribute("gh");
		
		
		
		if (th != null && session.getAttribute("gh") != null) {
			gio.xoa(th);
		}
		
		// 2. Xóa tất cả
		String delete = request.getParameter("delete");
		if (delete != null && session.getAttribute("gh") != null) {
			if (delete.equals("true")) {
				gio.xoaTatCa();				
			}
		}
		
		// 3. Xóa các sản phẩm đã chọn trong checkbox
		String[] dsTenHang = request.getParameterValues("checkbox");
		
		if (dsTenHang != null && session.getAttribute("gh") != null) {
			// Duyệt danh sách tên hàng, và xóa sản phẩm có tên hàng đó trong giỏ
			for(String tenHang : dsTenHang) {
				gio.xoa(tenHang);
			}
		}
		

		session.setAttribute("gh", gio);
		response.sendRedirect("DatHang.jsp");
	%>
</body>
</html>