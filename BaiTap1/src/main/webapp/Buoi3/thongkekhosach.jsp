<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thống Kê Sách</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
/* Đảm bảo sidebar và content-wrapper được định nghĩa trong sidebar-admin.jsp hoặc CSS ngoài */
.content-wrapper {
	padding: 20px;
}

/* Tùy chỉnh nhẹ cho form tìm kiếm */
.search-form-container {
	border: 1px solid #dee2e6;
	border-radius: 8px;
	transition: box-shadow 0.3s;
}

.search-form-container:focus-within {
	box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25); /* Focus effect */
}
</style>

</head>

<body>

	<jsp:include page="sidebar-admin.jsp"></jsp:include>

	<div class="content-wrapper">
		<div class="container-fluid p-0">
			<h2 class="mt-3 mb-4 text-dark">
				<i class="fas fa-chart-line text-primary me-2"></i>Thống Kê Kho Sách
			</h2>

			<div class="row mb-5">
				<div class="col-lg-4 col-md-6 mb-3">
					<div class="card card-stat card-stat-primary shadow-sm">
						<div class="card-body">
							<h5 class="card-title text-primary">
								<i class="fas fa-folder me-2"></i> Tổng số Loại Sách
							</h5>
							<p class="card-text fs-3 fw-bold">${thongKeTongQuan.tongSoLoai}</p>
						</div>
					</div>
				</div>

				<div class="col-lg-4 col-md-6 mb-3">
					<div class="card card-stat card-stat-info shadow-sm">
						<div class="card-body">
							<h5 class="card-title text-info">
								<i class="fas fa-book me-2"></i> Tổng số Đầu Sách
							</h5>
							<p class="card-text fs-3 fw-bold">${thongKeTongQuan.tongSoSach}</p>
						</div>
					</div>
				</div>

				<div class="col-lg-4 col-md-6 mb-3">
					<div class="card card-stat card-stat-warning shadow-sm">
						<div class="card-body">
							<h5 class="card-title text-warning">
								<i class="fas fa-box-open me-2"></i> Tổng Số Lượng Tồn
							</h5>
							<p class="card-text fs-3 fw-bold">${thongKeTongQuan.tongTonKho}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col-12">
					<form action="/ThongKeSach" method="get" id="searchAndPaginationForm"
						class="d-flex align-items-center search-form-container shadow-sm p-2 bg-white">

						<i class="fas fa-search fs-4 text-muted me-3 p-2"></i> <input
							class="form-control me-3 border-0" type="search"
							name="search" id="searchInput"
							placeholder="Tìm kiếm theo Tên Loại Sách..." aria-label="Search"
							value="${param.search != null ? param.search : ''}"> <input
							type="hidden" name="page" id="pageIndexInput"
							value="${currentPage != null ? currentPage : 1}">

						<button class="btn btn-primary fw-bold py-2" style="min-width: 120px;" type="submit">Tìm
							Kiếm</button>
					</form>
				</div>
			</div>
			<div class="table-responsive">
				<table class="table table-striped table-hover align-middle m-0">
					<thead class="table-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Tên Loại Sách</th>
							<th scope="col">Số Lượng Sách (Đầu Sách)</th>
							<th scope="col">Tổng Số Lượng Tồn</th>
							<th scope="col">Giá Bán Trung Bình</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tkSach" items="${dsThongKeKhoSach}"
							varStatus="status">

							<c:set var="isOutOfStock" value="${tkSach.tongSL eq 0}" />

							<tr class="${isOutOfStock ? 'table-danger fw-bold' : ''}">
								<th scope="row">${(currentPage - 1) * 10 + status.index + 1}</th>
								<td><span
									class="fw-bold ${isOutOfStock ? 'text-danger' : 'text-primary'}">${tkSach.tenLoai}</span></td>
								<td>${tkSach.soSach}</td>

								<td><span
									class="badge ${isOutOfStock ? 'bg-danger' : 'bg-secondary'}">
										${tkSach.tongSL} </span></td>

								<td><span class="fw-bold text-success"> <fmt:formatNumber
											value="${tkSach.tbcGia}" type="currency" currencySymbol="VND" />
								</span></td>
							</tr>
						</c:forEach>
						<c:if test="${empty dsThongKeKhoSach}">
							<tr>
								<td colspan="5" class="text-center text-muted py-4">Không
									tìm thấy dữ liệu thống kê nào.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="d-flex justify-content-between align-items-center mt-4">
				<small class="text-muted"> Trang hiện tại: <span
					class="fw-bold">${currentPage}</span> / Tổng số trang: <span
					class="fw-bold">${totalPages}</span>
				</small>

				<c:if test="${totalPages != 0}">
					<c:set var="pageRange" value="2" />
					<c:set var="beginPage" value="${currentPage - pageRange}" />
					<c:set var="endPage" value="${currentPage + pageRange}" />

					<c:if test="${beginPage < 1}">
						<c:set var="beginPage" value="1" />
						<c:set var="endPage" value="${beginPage + 2 * pageRange}" />
					</c:if>

					<c:if test="${endPage > totalPages}">
						<c:set var="endPage" value="${totalPages}" />
						<c:set var="beginPage" value="${endPage - 2 * pageRange}" />
						<c:if test="${beginPage < 1}">
							<c:set var="beginPage" value="1" />
						</c:if>
					</c:if>

					<nav aria-label="Pagination">
						<ul class="pagination pagination-sm m-0">
							<%-- Nút Previous (Sử dụng onclick JS) --%>
							<li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
								<a class="page-link" href="#"
								onclick="changePage(${currentPage - 1})" tabindex="-1"
								aria-disabled="${currentPage <= 1}"> <i
									class="fas fa-angle-left"></i> Trước
							</a>
							</li>

							<%-- Các nút số trang trong phạm vi (Sử dụng onclick JS) --%>
							<c:forEach var="i" begin="${beginPage}" end="${endPage}">
								<li class="page-item ${currentPage == i ? 'active' : ''}">
									<a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
								</li>
							</c:forEach>

							<%-- Nút Next (Sử dụng onclick JS) --%>
							<li
								class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
								<a class="page-link" href="#"
								onclick="changePage(${currentPage + 1})"
								aria-disabled="${currentPage >= totalPages}"> Sau <i
									class="fas fa-angle-right"></i>
							</a>
							</li>
						</ul>
					</nav>
				</c:if>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		const form = document.getElementById('searchAndPaginationForm');
		const pageIndexInput = document.getElementById('pageIndexInput');
		const searchInput = document.getElementById('searchInput');

		function changePage(newPage) {
			// Kiểm tra giới hạn trang hợp lệ trước khi gửi
			const totalPages = parseInt('${totalPages}');
			if (newPage < 1 || newPage > totalPages) {
				return;
			}
			
			// 1. Cập nhật số trang mới vào input ẩn
			pageIndexInput.value = newPage;
			
			// 2. Tự động gửi form (giữ nguyên tham số search)
			form.submit();
		}
		
		// Sự kiện submit của form (khi nhấn nút Tìm kiếm)
		form.addEventListener('submit', function(event) {
			// Khi tìm kiếm mới, luôn reset về trang 1
			pageIndexInput.value = 1;
		});

	</script>
</body>
</html>