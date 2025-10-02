<%@page import="modal.GioHangBo"%>
<%@page import="modal.Sach"%>
<%@page import="modal.SachBo"%>
<%@page import="modal.GioHang"%>
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
		String maSach = request.getParameter("ms");
		
		if (session.getAttribute("gh") == null) {
			session.setAttribute("gh", new GioHangBo());
		}
		
		SachBo sbo = new SachBo();
		GioHangBo gbo = new GioHangBo();
		
		GioHangBo gio = (GioHangBo) session.getAttribute("gh");
		
		
		if (maSach != null) {
			// Thêm một sản phẩm vào giỏ hàng
			Sach sach = sbo.timSachTheoMa(maSach);
			
			if (sach != null) {
				gio.them(maSach, sach.getTenSach(), sach.getTacGia(), 
					 1, sach.getGia() , sach.getAnh());
				
				session.setAttribute("gh", gio);
				
			}
		}
		
		// Cập nhật, xóa
		String update = request.getParameter("update");
		String slParam = request.getParameter("quantity");
		String remove = request.getParameter("remove");
		
		out.print("ma: "+ maSach + "U:" + update + "sl: " + slParam + remove);
		
		if (update != null && update.equals("true") && slParam != null) {
			int sl = Integer.parseInt(slParam);
			gio.capNhat(maSach, sl);
		}
		
		if (remove != null && remove.equals("true")) {
			gio.xoa(maSach);
			
		}
		
		session.setAttribute("gh", gio);
		response.sendRedirect("trangchu.jsp?q=cart");
	%>

</body>
</html>