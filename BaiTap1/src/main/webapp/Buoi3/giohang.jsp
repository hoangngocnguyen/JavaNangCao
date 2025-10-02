<%@page import="java.util.ArrayList"%>
<%@page import="modal.GioHangBo"%>
<%@page import="modal.GioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	
	
	Giỏ hàng của bạn <hr>
	
	<%
		if (session.getAttribute("gh") == null) {
			session.setAttribute("gh", new GioHangBo());
		}
	
		GioHangBo gbo = (GioHangBo) session.getAttribute("gh");
	
		for (GioHang gio : gbo.ds) {
	%>	
<div class="d-flex mb-3 p-2 border-bottom">
  <!-- Ảnh sách -->
  <div class="" style="width: 60px;">
    <img src="<%=gio.getAnh()%>" alt="<%=gio.getTenSach()%>" class="img-fluid rounded">
  </div>

  <!-- Thông tin sách -->
  <div class="ms-2">
    <div class=" mb-1">
      <span class="fw-bold text-uppercase"><%=gio.getTenSach()%></span><span style=""> (Tác giả: <%=gio.getTacGia()%>)</span>
    </div>

    <div class="">
      <span class="me-2">Giá:</span>
      <span style="color: #6d2524;" class="fw-bold me-2"><%=gio.getGia()%> đ</span>
      <span class="me-2">x</span>
      
      <!-- Form cập nhật -->
      <form action="xuLyDatHang.jsp?update=true&ms=<%=gio.getMaSach()%>" method="post" class="d-inline-block">
      	<input type="text" maxlength="4" pattern="\d{1,4}" name="quantity" value="<%=gio.getSoLuong()%>" min="1"
             class="text-center" style="width:44px;">
        <button type="submit" class="btn btn-link cart-action p-0 ms-2">Cập nhật</button>
      </form>

      <!-- Form trả lại -->
      <form action="xuLyDatHang.jsp?remove=true&ms=<%=gio.getMaSach()%>" method="post" class="d-inline ms-2">
        <button type="submit" class="btn btn-link cart-action p-0">Trả lại</button>
      </form>
    </div>
  </div>
</div>


  
		<hr>		
	<%} %>
	
	<!-- Tổng cộng -->
	<div class="fw-bold fs-5 mt-3">
	Tổng cộng: <%=gbo.tongTien()%> VNĐ
	</div>

<style>
  .cart-action {
    color: #000;
    text-decoration: none;
    border-radius: 4px;
    transition: all 0.2s;
  }
  .cart-action:hover {
    background-color: #6d2524; 
    color: #fff !important;
    text-decoration: underline;
  }
</style>