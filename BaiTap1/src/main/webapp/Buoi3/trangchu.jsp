<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="modal.SachBo"%>
<%@page import="modal.Sach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modal.Loai"%>
<%@page import="modal.LoaiBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>

<body>
	<div class="container">

		<!-- Navbar -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="TrangChuController">Hoàng Shop</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
					aria-controls="navbarNavDropdown" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav mb-2 mb-lg-0 me-2">
						<li class="nav-item"><a class="nav-link active" href="#">Home</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="TrangChuController?q=cart">Giỏ hàng</a></li>
						<li class="nav-item"><a class="nav-link"
							href="DatHangController">Trang đặt hàng</a></li>

						<c:choose>
							<c:when test="${empty sessionScope.ss }">
								<li class="nav-item"><a class="nav-link"
									href="DangNhapController">Đăng nhập</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link"
									href="DangXuatController">Đăng xuất</a></li>
							</c:otherwise>
						</c:choose>

					</ul>

					<!-- Search form -->
					<form action="TrangChuController" class="d-flex me-auto"
						role="search">
						<input class="form-control me-2" type="text" name="search"
							placeholder="Tìm kiếm..." aria-label="Search">
						<button class="btn btn-outline-light" type="submit">Search</button>
					</form>

					<!-- User greeting -->
					<c:if test="${not empty sessionScope.ss}">
						<span class="navbar-text text-white"> 👋 Xin chào, <b>
								${sessionScope.ss}</b>
						</span>
					</c:if>



				</div>
			</div>
		</nav>
	</div>

	<!-- Main content -->
	<div class="container mt-4">
		<div class="row">
			<div class="col-sm-3">
				<!-- Hiển thị loại -->
				<c:forEach var="L" items="${dsLoai}">
					<a href="TrangChuController?maLoai=${L.maLoai}"> ${L.tenLoai} </a>
					<hr>
				</c:forEach>

			</div>

			<!-- Phần hiển thị bên phải:  giỏ hàng hoặc là danh sách sản phẩm -->
			<div class="col-sm-9">


				<c:choose>
					<c:when test="${param.q == 'cart'}">
						<jsp:include page="giohang.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
						<!-- Hiển thị sách -->
						<div class="row g-3">

							<c:forEach var="sach" items="${dsSach}">
								<div class="col-sm-3">
									<div class="card h-100">
										<img
											style="background-image: url('${sach.anh}'); padding-top: 100%; background-size: contain; background-position: center; "
											src="" alt="" class="card-img-top">
										<div class="card-body">
											<div class="card-title">${sach.tenSach}</div>
											<div class="card-text">${sach.tacGia}</div>
											<div>
												<fmt:formatNumber value="${sach.gia }" type="number" />
												VNĐ
											</div>

											<!-- Nút đặt hàng-->
											<a href="GioHangController?ms=${sach.maSach}"> <img
												src="https://minhkhai.com.vn/store2/images/buynow.jpg"
												alt="">
											</a>

										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>