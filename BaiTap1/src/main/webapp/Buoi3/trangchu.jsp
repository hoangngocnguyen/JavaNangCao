<%@page import="modal.SachBo"%>
<%@page import="modal.Sach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modal.Loai"%>
<%@page import="modal.LoaiBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
  </head>

  <body>
    <% String name="" ; if (session.getAttribute("ss") !=null) { name=session.getAttribute("ss").toString(); } %>
      <div class="container">

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="trangchu.jsp">Hoàng Shop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
              aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNavDropdown">
              <ul class="navbar-nav mb-2 mb-lg-0 me-2">
                <li class="nav-item">
                  <a class="nav-link active" href="#">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="trangchu.jsp?q=cart">Giỏ hàng</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="../GioHang/DatHang.jsp">Trang đặt hàng</a>
                </li>
                <% if (name.isEmpty()) { %>
                  <li class="nav-item"><a class="nav-link" href="dangnhap.jsp">Đăng nhập</a></li>
                  <% } else { %>
                   <li class="nav-item"><a class="nav-link" href="dangxuat.jsp">Đăng xuất</a></li>
                   <% } %>
              </ul>

              <!-- Search form -->
              <form action="trangchu.jsp" class="d-flex me-auto" role="search">
                <input class="form-control me-2" type="text" name="search" placeholder="Tìm kiếm..." aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
              </form>

              <!-- User greeting -->
              <% if (!name.isEmpty()) { %>
                <span class="navbar-text text-white">
                  👋 Xin chào, <b>
                    <%= name %>
                  </b>
                </span>
                <% } %>
            </div>
          </div>
        </nav>
      </div>

      <!-- Main content -->
      <div class="container mt-4">
        <div class="row">
          <div class="col-sm-3">
            <!-- Hiển thị loại -->
            <%
            	LoaiBo loaiBo = new LoaiBo();
            	for (Loai L : loaiBo.getLoai()) {
            %>
            	<a href="trangchu.jsp?maLoai=<%=L.getMaLoai()%>">
            		<%=L.getTenLoai()%>
            	</a>
            	<hr>
            <%} %>
          </div>
          
          
          <div class="col-sm-9">
          		<%
          			// Hiển thị giỏ hàng nếu q = cart
          			String q = request.getParameter("q");
          			if (q != null && q.equals("cart")) {
          		%>
          		<jsp:include page="giohang.jsp"></jsp:include>
          	
          		<%} else {%>
          		
          		
             <!-- Hiển thị sách -->
				<div class="row g-3">

              <%
              
              	// Báo unicode
              	request.setCharacterEncoding("utf-8");
              	response.setCharacterEncoding("utf-8");
              
              	String maLoai = request.getParameter("maLoai");
              	String search = request.getParameter("search");
              	//out.print(maLoai);
              	
              	SachBo sachBo = new SachBo(); 
              	ArrayList<Sach> ds = sachBo.getSach();
              	
              	if (maLoai != null) {
              		ds = sachBo.getSach(maLoai);      
              		//out.print("có" + maLoai);
              	}
              	
              	if (search != null) {
              		ds = sachBo.timSach(search);
              	}
              	
              	
              	for (Sach sach : ds) {
              %>
                <div class="col-sm-3">
                  <div class="card h-100">
                    <img style="background-image: url('<%=sach.getAnh()%>'); padding-top: 100%; background-size: contain; background-position: center; " src="" alt="" class="card-img-top">
                    <div class="card-body">
                      <div class="card-title"><%=sach.getTenSach() %></div>
                      <div class="card-text"><%=sach.getTacGia() %></div>
                      <div><%=sach.getGia() %></div>
                      
                      <!-- Nút đặt hàng-->
                      <a href="xuLyDatHang.jsp?ms=<%=sach.getMaSach()%>">
                          <img src="https://minhkhai.com.vn/store2/images/buynow.jpg" alt="">                      
                      </a>
                      
                    </div>
                  </div>
                </div>
                    
              
              	<%} %>
              <%} // Kết thúc khối else hiển thị q%> 
              </div>
          </div>
        </div>
      </div>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
  </body>

  </html>