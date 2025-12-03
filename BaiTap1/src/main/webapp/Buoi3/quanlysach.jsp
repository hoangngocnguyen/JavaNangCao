<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lý Sách</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
/* CSS Tùy chỉnh */
body {
	background-color: #f8f9fa;
}

.book-card {
	border: none;
	border-radius: 1rem;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
	transition: transform 0.2s;
}

.book-card:hover {
	transform: translateY(-3px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.card-img-top-custom {
	height: 200px;
	object-fit: cover;
	border-top-left-radius: 1rem;
	border-top-right-radius: 1rem;
}

/* Tùy chỉnh cho form tìm kiếm */
.search-form-container {
	border: 1px solid #dee2e6;
	border-radius: 0.5rem;
}
</style>
</head>

<body>
	<jsp:include page="sidebar-admin.jsp"></jsp:include>

	<div class="content-wrapper">
		<div class="container my-5">
			<h2 class="mb-4 text-dark fw-bold">
				<i class="fas fa-book-open me-2 text-primary"></i> Quản Lý Kho Sách
			</h2>

			<c:if test="${not empty err}">
				<div class="alert alert-danger alert-dismissible fade show mb-4"
					role="alert">
					<i class="fas fa-exclamation-triangle me-2"></i> <strong>Lỗi
						Xóa Sách:</strong> ${err}
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			
			
			<div class="row mb-4 align-items-center">
				<div class="col-md-8">
					<form action="/QuanLySach" method="get"
						id="searchAndPaginationForm"
						class="d-flex align-items-center bg-white search-form-container p-2">
						<i class="fas fa-search fs-5 text-muted me-3 p-2"></i> <input
							class="form-control me-3 border-0" type="search" name="search"
							id="searchInput" placeholder="Tìm kiếm theo Tên Sách"
							aria-label="Search"
							value="${param.search != null ? param.search : ''}"> <input
							type="hidden" name="page" id="pageIndexInput"
							value="${pageIndexHienTai != null ? pageIndexHienTai : 1}">

						<button class="btn btn-primary fw-bold py-2"
							style="min-width: 120px;" type="submit">Tìm Kiếm</button>
					</form>
				</div>
				<div class="col-md-4 text-md-end mt-3 mt-md-0">
					<a href="/ThemSach"
						class="btn btn-success btn-lg fw-bold w-100"> <i
						class="fas fa-plus-circle me-2"></i> Thêm Sách
					</a>
				</div>
			</div>

			<div
				class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4 mb-4">
				<c:choose>
					<c:when test="${not empty dsSach}">
						<c:forEach var="sach" items="${dsSach}">
							<div class="col">
								<div class="card book-card h-100">

									<img src="${sach.anh}" class="card-img-top-custom"
										alt="${sach.tenSach}">

									<div class="card-body">
										<h5 class="card-title fw-bold text-primary">${sach.tenSach}</h5>

										<p class="card-text text-muted mb-1 small">
											<i class="fas fa-user-edit me-1"></i> Tác giả: ${sach.tacGia}
										</p>

										<p class="card-text mb-2 small">
											<span class="badge bg-secondary me-2">#${sach.maSach}</span>
											<span class="badge bg-info text-dark">Loại:
												${sach.maLoai}</span>
										</p>

										<div
											class="d-flex justify-content-between align-items-center mb-3">
											<span class="fw-bold fs-5 text-danger"> <fmt:formatNumber
													value="${sach.gia}" pattern="#,###" /> VND
											</span> <span class="badge bg-warning text-dark"> Tồn:
												${sach.soLuong} </span>
										</div>

										<div class="d-grid gap-2">
											<a href="/SuaSach?maSach=${sach.maSach}"
												class="btn btn-sm btn-outline-primary"> <i
												class="fas fa-edit me-1"></i> Sửa
											</a> <a href="/XoaSach?maSach=${sach.maSach}"
												class="btn btn-sm btn-outline-danger"
												onclick="return confirm('Bạn có chắc chắn muốn xóa sách ${sach.tenSach}?');">
												<i class="fas fa-trash-alt me-1"></i> Xóa
											</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="col-12 text-center py-5 bg-white rounded-3">
							<i class="fas fa-frown-open fa-3x text-muted mb-3"></i>
							<p class="lead text-muted">Không tìm thấy sách nào khớp với
								tiêu chí tìm kiếm.</p>
						</div>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="d-flex justify-content-between align-items-center mt-5">
				<small class="text-muted"> Trang hiện tại: <span
					class="fw-bold">${pageIndexHienTai}</span> / Tổng số trang: <span
					class="fw-bold">${totalPages}</span>
				</small>

				<c:if test="${totalPages != 0}">
					<c:set var="pageRange" value="2" />
					<c:set var="beginPage" value="${pageIndexHienTai - pageRange}" />
					<c:set var="endPage" value="${pageIndexHienTai + pageRange}" />

					<c:if test="${beginPage < 1}">
						<c:set var="beginPage" value="1" />
					</c:if>
					<c:if test="${endPage > totalPages}">
						<c:set var="endPage" value="${totalPages}" />
					</c:if>
					<c:if test="${beginPage > totalPages}">
						<c:set var="beginPage" value="1" />
					</c:if>

					<nav aria-label="Pagination">
						<ul class="pagination pagination-sm m-0">
							<%-- Nút Previous (Sử dụng onclick JS) --%>
							<li class="page-item ${pageIndexHienTai <= 1 ? 'disabled' : ''}">
								<a class="page-link" href="#"
								onclick="changePage(${pageIndexHienTai - 1})" tabindex="-1"
								aria-disabled="${pageIndexHienTai <= 1}"> <i
									class="fas fa-angle-left"></i> Trước
							</a>
							</li>

							<%-- Các nút số trang trong phạm vi (Sử dụng onclick JS) --%>
							<c:forEach var="i" begin="${beginPage}" end="${endPage}">
								<li class="page-item ${pageIndexHienTai == i ? 'active' : ''}">
									<a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
								</li>
							</c:forEach>

							<%-- Nút Next (Sử dụng onclick JS) --%>
							<li
								class="page-item ${pageIndexHienTai >= totalPages ? 'disabled' : ''}">
								<a class="page-link" href="#"
								onclick="changePage(${pageIndexHienTai + 1})"
								aria-disabled="${pageIndexHienTai >= totalPages}"> Sau <i
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

		function changePage(newPage) {
			const totalPages = parseInt('${totalPages}');
			if (newPage < 1 || newPage > totalPages) {
				return;
			}

			// 1. Cập nhật số trang mới vào input ẩn
			pageIndexInput.value = newPage;

			// 2. Tự động gửi form (giữ nguyên tham số search)
			form.submit();
		}

		// Khi người dùng nhấn nút Tìm kiếm, luôn reset về trang 1
		form.addEventListener('submit', function(event) {
			// Đảm bảo khi tìm kiếm mới, trang luôn được set về 1
			// Chỉ thay đổi giá trị nếu người dùng nhập nội dung tìm kiếm
			if (document.getElementById('searchInput').value.trim() !== '') {
				pageIndexInput.value = 1;
			}
		});
	</script>
</body>
</html>