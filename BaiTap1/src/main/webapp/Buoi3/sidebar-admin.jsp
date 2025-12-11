<%@page import="java.util.Locale"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
/* CSS cũ */
body {
	background-color: #f4f7f6;
}

.card-stat {
	border-left: 5px solid;
	border-radius: 0.5rem;
}

.card-stat-primary {
	border-left-color: #0d6efd;
}

.card-stat-info {
	border-left-color: #0dcaf0;
}

.card-stat-warning {
	border-left-color: #ffc107;
}

.table-responsive {
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.05);
	border-radius: 0.5rem;
	background-color: white;
}

/* CSS MỚI CHO SIDEBAR */
.sidebar {
	min-height: 100vh;
	width: 250px;
	position: fixed;
	top: 0;
	left: 0;
	z-index: 1030; /* Cao hơn navbar/topbar */
	background-color: #212529; /* Màu nền tối */
	transition: all 0.3s;
	padding-top: 15px;
}

.sidebar .nav-link {
	color: #adb5bd; /* Màu chữ nhạt */
	padding: 10px 15px;
	transition: all 0.2s;
}

.sidebar .nav-link:hover, .sidebar .nav-link.active {
	background-color: #343a40; /* Màu nền khi hover/active */
	color: white;
	border-radius: 5px;
}

/* Vùng chứa nội dung chính */
.content-wrapper {
	margin-left: 250px; /* Bằng với chiều rộng sidebar */
	padding: 20px;
}

/* ĐIỀU CHỈNH CHO MOBILE */
@media ( max-width : 992px) {
	.sidebar {
		margin-left: -250px; /* Ẩn sidebar trên mobile */
	}
	.sidebar.show {
		margin-left: 0; /* Hiện sidebar khi toggle */
	}
	.content-wrapper {
		margin-left: 0;
		width: 100%;
	}
}
</style>
<nav id="sidebarMenu" class="sidebar">
	<div class="d-flex flex-column h-100 p-3">
		<a href="/TrangChu"
			class="sidebar-brand text-white fw-bold fs-4 mb-4 text-decoration-none">
			<i class="fas fa-cubes me-2"></i> Hoàng Shop Admin
		</a>

		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item"><a href="/HomNay"
				class="nav-link" aria-current="page"> <i
					class="fas fa-chart-line me-2"></i> Ngày hôm nay
			</a></li>
			<li class="nav-item"><a href="/DoanhThu"
				class="nav-link" aria-current="page"> <i
					class="fas fa-chart-line me-2"></i> Thống kê doanh thu
			</a></li>
			<li class="nav-item"><a href="/ThongKeKhoSach"
				class="nav-link" aria-current="page"> <i
					class="fas fa-chart-line me-2"></i> Thống kê kho sách
			</a></li>
			<li><a href="/QuanLyDonHang" class="nav-link"> <i
					class="fas fa-receipt me-2"></i> Quản lý đơn hàng
			</a></li>
			<li><a href="/QuanLySach" class="nav-link"> <i
					class="fas fa-book-open me-2"></i> Quản lý sách
			</a></li>
			<li><a href="/QuanLyLoai" class="nav-link"> <i
					class="fas fa-book-open me-2"></i> Quản lý loại
			</a></li>
			<li><a href="/ChamSocKhachHang" class="nav-link"> <i
					class="fas fa-users me-2"></i> Chăm sóc khách hàng
			</a></li>
		</ul>

		<hr class="text-secondary">

		<c:choose>
			<c:when test="${empty sessionScope.ss }">
				<a href="/DangNhap" class="btn btn-sm btn-outline-light w-100">
					<i class="fas fa-sign-in-alt"></i> Đăng nhập
				</a>
			</c:when>
			<c:otherwise>
				<div class="dropdown">
					<a href="#"
						class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
						id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
						<i class="fas fa-user-circle fs-4 me-2"></i> <span class="fw-bold">${sessionScope.ss.hoten}</span>
					</a>
					<ul class="dropdown-menu dropdown-menu-dark text-small shadow"
						aria-labelledby="dropdownUser1">
						<li><a class="dropdown-item text-danger" href="/DangXuat"><i
								class="fas fa-sign-out-alt"></i> Đăng xuất</a></li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</nav>