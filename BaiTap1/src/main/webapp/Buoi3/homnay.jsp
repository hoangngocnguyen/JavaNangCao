<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doanh Thu Ngày Hôm Nay</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
/* CSS Tùy chỉnh */
body {
	background-color: #f4f6f9; /* Nền nhẹ nhàng */
}

.revenue-card {
	border-radius: 1rem;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	background: linear-gradient(135deg, #0d6efd 0%, #00b4d8 100%);
	/* Gradient màu xanh */
	color: white;
}

.table-revenue thead th {
	background-color: #007bff;
	color: white;
	vertical-align: middle;
}

.table-responsive {
	border-radius: 0.75rem;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	background-color: white;
}
</style>
</head>
<body>
	<jsp:include page="sidebar-admin.jsp"></jsp:include>
	<div class="content-wrapper">
		<div class="container my-5">
			<h2 class="mb-4 text-dark">
				<i class="fas fa-chart-line me-2"></i> Báo Cáo Doanh Thu Ngày Hôm
				Nay
			</h2>
			<p class="text-muted">
				Ngày: <span class="fw-bold"><fmt:formatDate
						value="<%=new java.util.Date()%>" pattern="dd/MM/yyyy" /></span>
			</p>

			<div class="card revenue-card mb-5">
				<div class="card-body p-4">
					<div class="row align-items-center">
						<div class="col-auto">
							<i class="fas fa-money-bill-wave fa-3x"></i>
						</div>
						<div class="col">
							<h6 class="card-title text-uppercase mb-1">Tổng Thu Nhập
								Ngày Hôm Nay</h6>
							<h1 class="card-text fw-bold">
								<fmt:formatNumber value="${totalAmount}" pattern="#,###" />
								<small class="text-white-50">VND</small>
							</h1>
						</div>
						<div class="col-auto">
							<span class="badge bg-light text-primary fs-6 fw-normal">${totalElements}
								Hóa đơn</span>
						</div>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-hover align-middle m-0 table-revenue">
					<thead>
						<tr>
							<th scope="col">Mã HĐ</th>
							<th scope="col">Tên Khách Hàng</th>
							<th scope="col">Tổng SL</th>
							<th scope="col">Tổng Giá</th>
							<th scope="col">Thành Tiền (Thực thu)</th>
							<th scope="col">Trạng Thái</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="hd" items="${dsHoaDonHomNay}" varStatus="status">
							<tr
								class="${hd.damua ? 'table-success-light' : 'table-secondary'}">
								<td class="fw-bold text-primary">#${hd.maHoaDon}</td>
								<td>${hd.hoTen}</td>
								<td>${hd.tongSoLuong}</td>
								<td><fmt:formatNumber value="${hd.tongGia}" pattern="#,###" />
									VND</td>
								<td class="fw-bold text-danger"><fmt:formatNumber
										value="${hd.thanhTien}" pattern="#,###" /> VND</td>
								<td><span
									class="badge ${hd.damua ? 'bg-success' : 'bg-secondary'}">
										${hd.damua ? 'Đã Thanh Toán' : 'Chờ Xử Lý'} </span></td>
							</tr>
						</c:forEach>
						<c:if test="${empty dsHoaDonHomNay}">
							<tr>
								<td colspan="6" class="text-center text-muted py-4">Không
									có hóa đơn nào phát sinh trong ngày hôm nay.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="d-flex justify-content-between align-items-center mt-4">
				<small class="text-muted"> Trang hiện tại: <span
					class="fw-bold">${page}</span> / Tổng số trang: <span
					class="fw-bold">${totalPages}</span>
				</small>

				<c:if test="${totalPages > 1}">
					<nav aria-label="Pagination">
						<ul class="pagination pagination-sm m-0">
							<%-- Nút Previous --%>
							<li class="page-item ${page <= 1 ? 'disabled' : ''}"><a
								class="page-link" href="?page=${page - 1}" tabindex="-1"
								aria-disabled="${page <= 1}"> <i class="fas fa-angle-left"></i>
									Trước
							</a></li>

							<%-- Các nút số trang (Phân trang nhẹ nhàng) --%>
							<c:forEach var="i" begin="1" end="${totalPages}">
								<li class="page-item ${page == i ? 'active' : ''}"><a
									class="page-link" href="?page=${i}">${i}</a></li>
							</c:forEach>

							<%-- Nút Next --%>
							<li class="page-item ${page >= totalPages ? 'disabled' : ''}">
								<a class="page-link" href="?page=${page + 1}"
								aria-disabled="${page >= totalPages}"> Sau <i
									class="fas fa-angle-right"></i>
							</a>
							</li>
						</ul>
					</nav>
				</c:if>
			</div>
			<div class="d-grid gap-2 mt-3 mb-5">
				<a href="/QuanLyDonHang"
					class="btn btn-warning btn-lg text-dark fw-bold"> <i
					class="fas fa-check-circle me-2"></i> Đến quản lý đơn hàng ngay
				</a>
			</div>

		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>