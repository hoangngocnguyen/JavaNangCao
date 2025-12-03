<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lý Loại Sách</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
/* CSS Tùy chỉnh nhẹ */
body {
	background-color: #f8f9fa;
}

.table-responsive {
	border-radius: 0.75rem;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	background-color: white;
}

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
				<i class="fas fa-tags me-2 text-info"></i> Quản Lý Danh Mục Loại
				Sách
			</h2>

			<div class="row mb-4 align-items-center">
				<div class="col-md-8">
					<form action="/QuanLyLoai" method="get"
						id="searchAndPaginationForm"
						class="d-flex align-items-center bg-white search-form-container p-2">
						<i class="fas fa-search fs-5 text-muted me-3"></i> <input
							class="form-control me-3 border-0" type="search"
							name="search" id="searchInput"
							placeholder="Tìm kiếm theo Mã hoặc Tên Loại Sách..."
							aria-label="Search"
							value="${param.search != null ? param.search : ''}"> <input
							type="hidden" name="page" id="pageIndexInput"
							value="${pageIndexHienTai != null ? pageIndexHienTai : 1}">

						<button class="btn btn-primary fw-bold py-2"
							style="min-width: 120px;" type="submit">Tìm
							Kiếm</button>
					</form>
				</div>
				<div class="col-md-4 text-md-end mt-3 mt-md-0">
					<a href="/ThemLoai" class="btn btn-success btn-lg fw-bold w-100">
						<i class="fas fa-plus-circle me-2"></i> Thêm Loại Mới
					</a>
				</div>
			</div>

			<hr>

			<div class="table-responsive">
				<table class="table table-hover align-middle m-0">
					<thead class="table-dark">
						<tr>
							<th scope="col" style="width: 5%;">#</th>
							<th scope="col" style="width: 20%;">Mã Loại</th>
							<th scope="col" style="width: 50%;">Tên Loại Sách</th>
							<th scope="col" style="width: 25%;">Thao Tác</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty dsLoai}">
								<c:forEach var="loai" items="${dsLoai}" varStatus="status">
									<tr>
										<th scope="row">${status.index + 1}</th>
										<td><span class="fw-bold text-primary">${loai.maLoai}</span></td>
										<td>${loai.tenLoai}</td>
										<td><a href="/SuaLoai?maLoai=${loai.maLoai}"
											class="btn btn-sm btn-outline-primary me-2"> <i
												class="fas fa-edit"></i> Sửa
										</a> <a href="/XoaLoai?maLoai=${loai.maLoai}"
											class="btn btn-sm btn-outline-danger"
											onclick="return confirm('Bạn có chắc chắn muốn xóa loại ${loai.tenLoai}?');">
												<i class="fas fa-trash-alt"></i> Xóa
										</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4" class="text-center text-muted py-4">Không
										tìm thấy loại sách nào.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>

			<div class="d-flex justify-content-between align-items-center mt-4">
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
			if (document.getElementById('searchInput').value.trim() !== '' || pageIndexInput.value !== '1') {
				pageIndexInput.value = 1;
			}
		});
	</script>
</body>
</html>