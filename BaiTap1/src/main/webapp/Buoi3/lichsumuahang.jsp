<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử mua hàng</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
body {
	background-color: #f8f9fa; /* Nền nhẹ nhàng */
}

.order-item-card {
	border: 1px solid #e9ecef; /* Viền nhẹ */
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.05); /* Bóng nhẹ */
	transition: transform 0.2s, box-shadow 0.2s;
	border-radius: 0.75rem; /* Bo góc */
}

.order-item-card:hover {
	transform: translateY(-2px);
	box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
}

.product-image {
	width: 70px;
	height: 70px;
	object-fit: cover;
	border-radius: 0.5rem;
	border: 1px solid #dee2e6;
}

.status-paid {
	background-color: #d1e7dd;
	color: #0f5132;
	font-weight: 600;
}

.status-unpaid {
	background-color: #f8d7da;
	color: #842029;
	font-weight: 600;
}
</style>
</head>

<body>
	
	<%-- Giữ nguyên Header Include --%>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	
	<div class="container my-5">
		
		<h2 class="mb-4 text-primary fw-bold">
			<i class="fas fa-history me-2"></i> Lịch Sử Mua Hàng Của Bạn
		</h2>

		<div class="alert alert-info border-info-subtle" role="alert">
			<i class="fas fa-info-circle me-2"></i> Đơn hàng đã hoàn tất. Vui lòng kiểm tra email để xem chi tiết hóa đơn.
		</div>

		<%-- Vòng lặp hiển thị các chi tiết đơn hàng --%>
		<div class="space-y-4">
			<c:forEach var="ls" items="${dsLichSuMuaHang}" varStatus="loop">

				<div class="card order-item-card mb-3">
					<div class="card-body p-3">
						<div class="row align-items-center g-3">
							
							<div class="col-md-1 col-3 d-flex justify-content-center">
								<img src="${ls.anh}" alt="${ls.tensach}" 
									class="product-image">
							</div>

							<div class="col-md-4 col-9">
								<p class="mb-1 fw-semibold text-dark">${ls.tensach}</p>
								<div class="small text-muted d-flex align-items-center">
									<span title="Mã chi tiết hóa đơn" class="me-3"><i class="fas fa-barcode me-1"></i> CT: ${ls.maChiTietHoaDon}</span>
									<span title="Mã hóa đơn gốc"><i class="fas fa-receipt me-1"></i> HĐ: ${ls.maHoaDon}</span>
								</div>
							</div>
							
							<div class="col-md-3 col-6 text-md-center text-start">
								<p class="mb-1 small text-muted">Số lượng: <span class="fw-bold text-dark">${ls.soLuongMua}</span></p>
								<p class="mb-1 small text-muted">Giá: 
									<span class="text-danger">
										<fmt:formatNumber value="${ls.gia}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0"/>
									</span>
								</p>
							</div>

							<div class="col-md-4 col-6 text-md-end text-end">
								<h5 class="fw-bolder text-success mb-2">
									<fmt:formatNumber value="${ls.thanhTien}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0"/>
								</h5>

								<c:choose>
									<c:when test="${ls.damua}">
										<span class="badge rounded-pill status-paid py-2 px-3">
											<i class="fas fa-check-circle me-1"></i> Đã Thanh Toán
										</span>
									</c:when>
									<c:otherwise>
										<a href="ThanhToan?ma=${ls.maChiTietHoaDon}"
											class="btn btn-primary btn-sm shadow-sm"> 
											<i class="fas fa-credit-card me-1"></i> Thanh Toán Ngay
										</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="card-footer bg-light text-end text-muted small py-2">
						Đã đặt hàng lúc: <fmt:formatDate value="${ls.ngayMua}" pattern="dd/MM/yyyy HH:mm" />
					</div>
				</div>

			</c:forEach>
			
			<c:if test="${empty dsLichSuMuaHang}">
				<div class="alert alert-warning text-center mt-4">
					<i class="fas fa-box-open fa-lg me-2"></i> Bạn chưa có đơn hàng nào trong lịch sử mua hàng.
				</div>
			</c:if>
		</div>

		<%-- PHẦN PHÂN TRANG (Giữ nguyên logic và class Bootstrap) --%>
		<div class="d-flex justify-content-center mt-5">
			<c:if test="${totalPages != 0}">
				<c:set var="totalPages"
					value="${totalPages != null ? totalPages : 1}" />
				<c:set var="pageRange" value="2" />

				<c:set var="beginPage" value="${currentPage - pageRange}" />
				<c:set var="endPage" value="${currentPage + pageRange}" />

				<c:if test="${beginPage < 1}">
					<c:set var="beginPage" value="1" />
					<c:set var="endPage" value="${currentPage + 2*pageRange}" />
				</c:if>

				<c:if test="${endPage > totalPages}">
					<c:set var="endPage" value="${totalPages}" />
					<c:set var="beginPage" value="${endPage - 2 * pageRange}" />
					<c:if test="${beginPage < 1}">
						<c:set var="beginPage" value="1" />
					</c:if>
				</c:if>

				<nav>
					<ul class="pagination shadow-sm">
						<%-- Nút Previous --%>
						<li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
							<a class="page-link"
							href="/LichSuMuaHang?page=${currentPage - 1}">Trước</a>
						</li>

						<%-- Hiển thị nút "..." nếu cần [1]...[n]--%>
						<c:if test="${beginPage > 1}">
							<li class="page-item"><a class="page-link"
								href="/LichSuMuaHang?page=1">1</a></li>
							<c:if test="${beginPage > 2}">
								<li class="page-item disabled"><a class="page-link"
									href="#">...</a></li>
							</c:if>
						</c:if>

						<%-- Các nút số trang trong phạm vi --%>
						<c:forEach var="i" begin="${beginPage}" end="${endPage}">
							<li class="page-item ${currentPage == i ? 'active' : ''}"><a
								class="page-link" href="/LichSuMuaHang?page=${i}">${i}</a></li>
						</c:forEach>

						<%-- Hiển thị nút "..." nếu cần --%>
						<c:if test="${endPage < totalPages}">
							<c:if test="${endPage < totalPages - 1}">
								<li class="page-item disabled"><a class="page-link"
									href="#">...</a></li>
							</c:if>
							<li class="page-item"><a class="page-link"
								href="/LichSuMuaHang?page=${totalPages}">${totalPages}</a></li>
						</c:if>

						<%-- Nút Next --%>
						<li
							class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
							<a class="page-link"
							href="/LichSuMuaHang?page=${currentPage + 1}">Sau</a>
						</li>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
