<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chăm sóc khách hàng</title>
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

/* Thêm CSS cho hàng nổi bật (Khách hàng chi tiêu cao) */
.highlight-row {
	background-color: #fff3cd; /* Màu vàng nhạt */
	border-left: 5px solid #ffc107; /* Viền vàng nổi bật */
}
</style>
</head>
<body>
	<jsp:include page="sidebar-admin.jsp"></jsp:include>

	<div class="content-wrapper p-4">
		<div class="container">
			<h2 class="mt-3 mb-4">
				<i class="fas fa-users me-2 text-primary"></i> Danh Sách Khách Hàng & Xếp Hạng
			</h2>

			<div class="row mb-4">
				<div class="col-md-4">
					<div class="revenue-card p-3">
						<div class="d-flex align-items-center">
							<i class="fas fa-hand-holding-usd fa-2x me-3"></i>
							<div>
								<p class="mb-0 fw-light">Tổng số khách hàng</p>
								<h3 class="mb-0 fw-bold">
									<c:out value="${dsKhachHang.size()}" />
								</h3>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-hover table-revenue align-middle mb-0">
					<thead>
						<tr>
							<th scope="col" class="text-center" style="width: 5%;">#</th>
							<th scope="col" style="width: 20%;">Thông tin Khách hàng</th>
							<th scope="col" style="width: 15%;">Liên hệ</th>
							<th scope="col" style="width: 15%;">Địa chỉ</th>
							<th scope="col" class="text-end" style="width: 20%;">Tổng
								chi tiêu</th>
							<th scope="col" class="text-center" style="width: 10%;">Tháng
								hoạt động</th>
							<th scope="col" class="text-center" style="width: 15%;">Hành
								động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="kh" items="${dsKhachHang}" varStatus="status">

							<c:set var="isVIP" value="${kh.tongChiTieu > 5000000}" />

							<tr class="${isVIP ? 'highlight-row' : ''}">
								<td class="text-center fw-bold text-muted">${status.index + 1}</td>

								<td>
									<p class="fw-bold mb-1">${kh.hoTen}</p>
									<p class="text-muted mb-0">Mã KH: ${kh.maKH}</p>
								</td>

								<td><c:if test="${not empty kh.sodt}">
										<p class="text-success fw-semibold mb-1">
											<i class="fas fa-phone-alt me-1"></i> ${kh.sodt}
										</p>
									</c:if> <c:if test="${empty kh.sodt}">
										<p class="text-success fw-semibold mb-1">Chưa có số điện
											thoại</p>
									</c:if>

									<p class="text-muted mb-0">${kh.email}</p></td>

								<td class="text-wrap"><c:if test="${not empty kh.diaChi}">
										<p class="text-success fw-semibold mb-1">
											<i class="fas fa-phone-alt me-1"></i> ${kh.diaChi}
										</p>
									</c:if> <c:if test="${empty kh.diaChi}">
										<p class="text-success fw-semibold mb-1">Địa chỉ trống</p>
									</c:if></td>

								<td class="text-end"><span
									class="${isVIP ? 'text-danger fw-bolder' : 'text-primary fw-bold'}">
										<fmt:formatNumber value="${kh.tongChiTieu}" type="number"
											pattern="#,###" /> đ <c:if test="${isVIP}">
											<i class="fas fa-crown text-warning ms-1"></i>
										</c:if>
								</span></td>

								<td class="text-center">${kh.thang}</td>

								<td class="text-center"><a
									href="/ChamSocKhachHang?makh=${kh.maKH }"
									class="btn btn-sm btn-outline-info me-1" title="Xem chi tiết">
										<i class="fas fa-eye"></i>
								</a> <a href="mailto:${kh.email}"
									class="btn btn-sm btn-outline-primary" title="Gửi email"> <i
										class="fas fa-envelope"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<c:if test="${empty dsKhachHang}">
				<div class="alert alert-warning text-center mt-3" role="alert">
					<i class="fas fa-exclamation-triangle me-2"></i> Không có dữ liệu
					khách hàng nào được tìm thấy.
				</div>
			</c:if>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>