<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Chi Tiết Hóa Đơn - Admin</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
body {
	background-color: #f4f6f9;
}

.admin-table th {
	background-color: #007bff;
	color: white;
	vertical-align: middle;
}

.admin-table td {
	vertical-align: middle;
}

.order-card-admin {
	border-left: 5px solid #007bff;
}
</style>
</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/TrangChu">Hoàng Shop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav mb-2 mb-lg-0 me-2">
					<li class="nav-item"><a class="nav-link" href="/Dashboard">Thống
							kê</a></li>

					<li class="nav-item"><a class="nav-link" href="/QuanLyDonHang">Quản
							lý đơn hàng</a></li>

					<c:choose>
						<c:when test="${empty sessionScope.ss }">
							<li class="nav-item"><a class="nav-link" href="/DangNhap">Đăng
									nhập</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="/DangXuat">Đăng
									xuất</a></li>
						</c:otherwise>
					</c:choose>
				</ul>

				<c:if test="${not empty sessionScope.ss}">
					<span class="navbar-text text-white"> 👋 Xin chào, <b>
							${sessionScope.ss.hoten}</b>
					</span>
				</c:if>
			</div>
		</div>
	</nav>

	<div class="container my-5">

		<c:if test="${not empty dsChiTietHoaDon}">
			<c:set var="firstItem" value="${dsChiTietHoaDon[0]}" />
			<div class="row mb-4">
				<div class="col-md-8">
					<h2 class="text-primary mb-3">
						<i class="fas fa-file-invoice"></i> Chi Tiết Hóa Đơn
						#${firstItem.maHoaDon}
					</h2>
					<p class="lead">
						<i class="fas fa-user"></i> Khách hàng: <span class="fw-bold">${firstItem.hoTen}</span>
						(Mã KH: ${firstItem.makh})
					</p>
				</div>
				<div class="col-md-4 text-md-end">
					<a href="/QuanLyDonHang" class="btn btn-outline-secondary mb-3">
						<i class="fas fa-arrow-left"></i> Quay lại Đơn hàng
					</a>
					<p class="fw-bold">
						Trạng thái chung: <span
							class="badge ${firstItem.damua ? 'bg-success' : 'bg-warning text-dark'}">
							${firstItem.damua ? 'Đã xác nhận (Hoàn thành)' : 'Chờ xác nhận'}
						</span>
					</p>
				</div>
			</div>
			<hr>
		</c:if>

		<c:if test="${empty dsChiTietHoaDon}">
			<div class="alert alert-warning">
				<i class="fas fa-bell"></i> Không tìm thấy chi tiết cho hóa đơn này.
			</div>
		</c:if>

		<div class="table-responsive d-none d-md-block">
			<table class="table table-hover admin-table shadow-sm">
				<thead>
					<tr>
						<th>Mã CTHD</th>
						<th>Tên Sách</th>
						<th>SL Mua</th>
						<th>Giá (Đơn)</th>
						<th>Thành Tiền</th>
						<th>Trạng Thái</th>
						<th>Xác Nhận</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="totalFinalAmount" value="0" />
					<c:forEach var="cthd" items="${dsChiTietHoaDon}">
						<tr class="${cthd.damua ? 'table-light' : 'table-warning'}">
							<td>#${cthd.maChiTietHD}</td>
							<td class="fw-bold">${cthd.tenSach}</td>
							<td>${cthd.soLuongMua}</td>
							<td><fmt:formatNumber value="${cthd.gia}" pattern="#,###" />
								VND</td>
							<td class="fw-bold text-danger"><fmt:formatNumber
									value="${cthd.thanhTien}" pattern="#,###" /> VND</td>
							<td><span
								class="badge ${cthd.damua ? 'bg-success' : 'bg-warning text-dark'}">
									${cthd.damua ? 'Đã Thanh Toán' : 'Chờ Xác Nhận'} </span></td>

							<td><c:choose>
									<c:when test="${cthd.damua}">
										<button class="btn btn-sm btn-outline-secondary disabled"
											title="Đã xác nhận">
											<i class="fas fa-check"></i> Xong
										</button>
									</c:when>
									<c:otherwise>
										<a
											href="/XacNhanChiTietHoaDon?action=confirm_cthd&cthd=${cthd.maChiTietHD}"
											class="btn btn-sm btn-success"
											onclick="return confirm('Xác nhận Đã Thanh Toán cho chi tiết hóa đơn #${cthd.maChiTietHD}?');">
											<i class="fas fa-check-double"></i> Xác Nhận CTHD
										</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<c:set var="totalFinalAmount"
							value="${totalFinalAmount + cthd.thanhTien}" />
					</c:forEach>

					<tr class="table-primary fw-bold">
						<td colspan="4" class="text-end">TỔNG CỘNG HÓA ĐƠN:</td>
						<td class="text-danger"><fmt:formatNumber
								value="${totalFinalAmount}" pattern="#,###" /> VND</td>
						<td colspan="2"></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="d-md-none">
			<c:set var="totalFinalAmountMobile" value="0" />
			<c:forEach var="cthd" items="${dsChiTietHoaDon}">
				<div
					class="card order-card-admin mb-3 ${cthd.damua ? 'border-success' : 'border-warning'}">
					<div class="card-body">
						<div class="d-flex justify-content-between align-items-start mb-2">
							<h6 class="mb-0 text-primary">CTHD #${cthd.maChiTietHD}</h6>
							<span
								class="badge ${cthd.damua ? 'bg-success' : 'bg-warning text-dark'}">
								${cthd.damua ? 'Đã TT' : 'Chờ TT'} </span>
						</div>

						<p class="mb-1 fw-bold">${cthd.tenSach}(SL:
							${cthd.soLuongMua})</p>

						<div class="d-flex justify-content-between small text-muted mb-3">
							<span>Giá Đơn: <fmt:formatNumber value="${cthd.gia}"
									pattern="#,###" /> VND
							</span>
						</div>

						<h5 class="text-danger mb-3">
							Thành Tiền:
							<fmt:formatNumber value="${cthd.thanhTien}" pattern="#,###" />
							VND
						</h5>

						<c:choose>
							<c:when test="${cthd.damua}">
								<button class="btn btn-sm btn-outline-secondary w-100 disabled">
									<i class="fas fa-check"></i> Đã Xác Nhận
								</button>
							</c:when>
							<c:otherwise>
								<a
									href="/XacNhanChiTietHoaDon?action=confirm_cthd&cthd=${cthd.maChiTietHD}"
									class="btn btn-sm btn-success w-100"
									onclick="return confirm('Xác nhận Đã Thanh Toán cho chi tiết hóa đơn #${cthd.maChiTietHD}?');">
									<i class="fas fa-check-double"></i> Xác Nhận CTHD
								</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<c:set var="totalFinalAmountMobile"
					value="${totalFinalAmountMobile + cthd.thanhTien}" />
			</c:forEach>
			<div class="alert alert-primary fw-bold text-end">
				TỔNG CỘNG: <span class="text-danger"><fmt:formatNumber
						value="${totalFinalAmountMobile}" pattern="#,###" /> VND</span>
			</div>
		</div>

	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>