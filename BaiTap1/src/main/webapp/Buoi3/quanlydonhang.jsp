<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Quản Lý Đơn Hàng Admin</title>
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
					<li class="nav-item"><a class="nav-link"
						href="/Dashboard">Thống kê</a>
                       </li>
					
					<li class="nav-item"><a class="nav-link"
						href="/QuanLyDonHang">Quản lý đơn hàng</a>
                       </li>

					<c:choose>
						<c:when test="${empty sessionScope.ss }">
							<li class="nav-item"><a class="nav-link"
								href="/DangNhap">Đăng nhập</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="/DangXuat">Đăng xuất</a></li>
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

	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger mx-5 mt-3">${errorMesage}</div>
	</c:if>

	<div class="container my-5">

		<form action="/QuanLyDonHang" method="get" id="QuanLyDonHangForm"
			class="d-flex me-auto mb-4" role="search">
			<input class="form-control me-2" type="text" name="search"
				id="searchInput" placeholder="Tìm kiếm..." aria-label="Search"
				value="${search != null ? search : ''}"> 
			<input
				type="hidden" name="page" id="pageIndexInput"
				value="${page != null ? page : 1}">
		</form>


		<h2 class="mt-3 mb-4 text-primary">
			<i class="fas fa-receipt"></i> Quản Lý Đơn Hàng Cần Xử Lý
		</h2>



		<c:if test="${empty dsXacNhanMuaHang}">
			<div class="alert alert-warning">
				<i class="fas fa-bell"></i> Hiện không có đơn hàng nào cần xác nhận.
			</div>
		</c:if>

		<div class="table-responsive d-none d-md-block">
			<table class="table table-hover admin-table shadow-sm">
				<thead>
					<tr>
						<th>Mã HĐ</th>
						<th>Mã KH</th>
						<th>Tên Khách Hàng</th>
						<th>Tổng SL</th>
						<th>Tổng Giá</th>
						<th>Thành Tiền</th>
						<th>Ngày Mua</th>
						<th>Trạng Thái</th>
						<th>Hành Động</th>
						<th>Chi Tiết</th> </tr>
				</thead>
				<tbody>
					<c:forEach var="hd" items="${dsXacNhanMuaHang}">
						<tr class="${hd.damua ? 'table-light' : 'table-warning'}">
							<td class="fw-bold text-primary">${hd.maHoaDon}</td>
							<td>${hd.makh}</td>
							<td class="fw-bold">${hd.hoTen}</td>
							<td>${hd.tongSoLuong}</td>
							<td><fmt:formatNumber value="${hd.tongGia}" pattern="#,###" /> VND
							</td>
							<td class="fw-bold text-danger"><fmt:formatNumber
									value="${hd.thanhTien}" pattern="#,###" /> VND</td>
							<td><fmt:formatDate value="${hd.ngayMua}"
									pattern="dd/MM/yyyy HH:mm" /></td>
							<td><span
								class="badge ${hd.damua ? 'bg-success' : 'bg-warning text-dark'}">
									${hd.damua ? 'Đã Xác Nhận' : 'Chờ Xác Nhận'} </span></td>
							
							<td><c:choose>
									<c:when test="${hd.damua}">
										<button class="btn btn-sm btn-outline-secondary disabled"
											title="Đã xác nhận">
											<i class="fas fa-check"></i> Xong
										</button>
									</c:when>
									<c:otherwise>
										<a
											href="QuanLyDonHang?action=confirm&ma=${hd.maHoaDon}"
											class="btn btn-sm btn-success"
											onclick="return confirm('Xác nhận Đã Thanh Toán cho hóa đơn #${hd.maHoaDon}?');">
											<i class="fas fa-check-double"></i> Xác Nhận
										</a>
									</c:otherwise>
								</c:choose>
							</td>
							
							<td>
								<a href="XacNhanChiTietHoaDon?mahoadon=${hd.maHoaDon}"
									class="btn btn-sm btn-info text-white"
									title="Xem chi tiết hóa đơn">
									<i class="fas fa-info-circle"></i> Chi Tiết
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<div class="d-md-none">
			<c:forEach var="hd" items="${dsXacNhanMuaHang}">
				<div
					class="card order-card-admin mb-3 ${hd.damua ? 'border-success' : 'border-warning'}">
					<div class="card-body">
						<div class="d-flex justify-content-between align-items-start mb-2">
							<h6 class="mb-0 text-primary">HĐ ${hd.maHoaDon} - KH:
								${hd.makh}</h6>
							<span
								class="badge ${hd.damua ? 'bg-success' : 'bg-warning text-dark'}">
								${hd.damua ? 'Đã TT' : 'Chờ TT'} </span>
						</div>

						<p class="mb-1">Khách Hàng: <span class="fw-bold">${hd.hoTen}</span></p>

						<div class="d-flex justify-content-between small text-muted mb-3">
							<span>Tổng SL: ${hd.tongSoLuong}</span> 
							<span>Tổng Giá: <fmt:formatNumber value="${hd.tongGia}" pattern="#,###" /> VND</span>
						</div>

						<h5 class="text-danger mb-3">
							Thành Tiền:
							<fmt:formatNumber value="${hd.thanhTien}" pattern="#,###" />
							VND
						</h5>

						<div class="d-grid gap-2">
							<a href="XacNhanChiTietHoaDon?mahoadon=${hd.maHoaDon}"
								class="btn btn-sm btn-info text-white">
								<i class="fas fa-info-circle"></i> Xem Chi Tiết
							</a>
						<c:choose>
							<c:when test="${hd.damua}">
								<button class="btn btn-sm btn-outline-secondary disabled">
									<i class="fas fa-check"></i> Đã Xác Nhận
								</button>
							</c:when>
							<c:otherwise>
								<a href="QuanLyDonHang?action=confirm&ma=${hd.maHoaDon}"
									class="btn btn-sm btn-success"
									onclick="return confirm('Xác nhận Đã Thanh Toán cho hóa đơn #${hd.maHoaDon}?');">
									<i class="fas fa-check-double"></i> Xác Nhận Thanh Toán
								</a>
							</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div class="card-footer text-muted small">
						<i class="fas fa-clock"></i>
						<fmt:formatDate value="${hd.ngayMua}" pattern="dd/MM/yyyy HH:mm" />
					</div>
				</div>
			</c:forEach>
		</div>

		<div class="d-flex justify-content-center mt-4">
			<c:if test="${not empty dsXacNhanMuaHang}">
				<c:set var="totalPages"
					value="${totalPages != null ? totalPages : 1}" />
				<c:set var="pageRange" value="2" />
				<c:set var="beginPage" value="${page - pageRange}" />
				<c:set var="endPage" value="${page + pageRange}" />

				<c:if test="${beginPage < 1}">
					<c:set var="beginPage" value="1" />
					<c:set var="endPage" value="${page + 2*pageRange}" />
				</c:if>

				<c:if test="${endPage > totalPages}">
					<c:set var="endPage" value="${totalPages}" />
					<c:set var="beginPage" value="${endPage - 2 * pageRange}" />
					<c:if test="${beginPage < 1}">
						<c:set var="beginPage" value="1" />
					</c:if>
				</c:if>

				<nav>
					<ul class="pagination">
						<li class="page-item ${page <= 1 ? 'disabled' : ''}">
							<a class="page-link" href="#"
							onclick="changePage(${page - 1})">Trước</a>
						</li>

						<c:if test="${beginPage > 1}">
							<li class="page-item"><a class="page-link" href="#"
								onclick="changePage(1)">1</a></li>
							<c:if test="${beginPage > 2}">
								<li class="page-item disabled"><a class="page-link"
									href="#">...</a></li>
							</c:if>
						</c:if>

						<c:forEach var="i" begin="${beginPage}" end="${endPage}">
							<li class="page-item ${page == i ? 'active' : ''}">
								<a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
							</li>
						</c:forEach>

						<c:if test="${endPage < totalPages}">
							<c:if test="${endPage < totalPages - 1}">
								<li class="page-item disabled"><a class="page-link"
									href="#">...</a></li>
							</c:if>
							<li class="page-item"><a class="page-link" href="#"
								onclick="changePage(${totalPages})">${totalPages}</a></li>
						</c:if>

						<li
							class="page-item ${page >= totalPages ? 'disabled' : ''}">
							<a class="page-link" href="#"
							onclick="changePage(${page + 1})">Sau</a>
						</li>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

	<script>
					const form = document.getElementById('QuanLyDonHangForm');
					const pageIndexInput = document.getElementById('pageIndexInput');
					const searchInput = document.getElementById('searchInput');
					function changePage(newPage) {

						// 1. Cập nhật số trang mới
						pageIndexInput.value = newPage;

						// 2. Gửi form chung
						form.submit();

					}
				</script>
</body>

</html>