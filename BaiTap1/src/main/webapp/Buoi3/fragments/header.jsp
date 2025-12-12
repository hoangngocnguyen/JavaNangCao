<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="/TrangChu">Hoàng Shop</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav mb-2 mb-lg-0 me-2">
				<c:if test="${not empty sessionScope.ss }">
					<li class="nav-item"><a class="nav-link" href="/LichSuMuaHang">Lịch
							sử mua</a></li>
				</c:if>

				<li class="nav-item"><a class="nav-link"
					href="/TrangChu?q=cart">Giỏ hàng</a></li>

				<c:if test="${sessionScope.ss.tendn == 'admin'}">
					<li class="nav-item"><a class="nav-link" href="/Admin">Trang admin</a></li>
				</c:if>


				<c:choose>
					<c:when test="${empty sessionScope.ss }">
						<li class="nav-item"><a class="nav-link" href="/DangNhap">Đăng
								nhập</a></li>

						<li class="nav-item"><a class="nav-link" href="/DangKy">Đăng
								ký</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/DangXuat">Đăng
								xuất</a></li>
					</c:otherwise>
				</c:choose>
			</ul>

			<form action="/TrangChu" method="get" id="filterForm"
				class="d-flex me-auto" role="search"
				onsubmit="return optimizeForm()">

				<input class="form-control me-2" type="text" name="search"
					id="searchInput" placeholder="Tìm kiếm..." aria-label="Search"
					value="${search != null ? search : ''}" onchange="handleSearch()">

				<button class="btn btn-outline-light" type="submit">Search</button>

				<input type="hidden" name="maLoai" id="maLoaiInput"
					value="${maLoai != null ? maLoai : ''}"> <input
					type="hidden" name="page" id="pageIndexInput"
					value="${page != null ? page : 1}">
			</form>
			<c:if test="${not empty sessionScope.ss}">
				<span class="navbar-text text-white"> 👋 Xin chào, <b>
						${sessionScope.ss.hoten}</b>
				</span>
			</c:if>
		</div>
	</div>
</nav>