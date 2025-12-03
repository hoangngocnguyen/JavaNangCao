<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="modal.Sach.SachBo"%>
<%@page import="modal.Sach.Sach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modal.Loai.Loai"%>
<%@page import="modal.Loai.LoaiBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<style>
/* Đảm bảo ảnh hiển thị đẹp trong card */
.card-img-top {
	height: 0;
	padding-top: 100%; /* 1:1 Aspect Ratio */
	background-size: contain !important;
	background-repeat: no-repeat;
	background-position: center;
}

/* Kiểu cho nút danh mục */
.category-link {
	cursor: pointer;
	color: #007bff; /* Màu mặc định của link */
	text-decoration: none;
}

.category-link:hover {
	color: #0056b3;
	text-decoration: underline;
}

.line-clamp-2 {
	display: -webkit-box;
	-webkit-line-clamp: 2; /* Giới hạn 2 dòng */
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	min-height: 2.8em; /* Đảm bảo chiều cao tối thiểu cho 2 dòng */
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
					<li class="nav-item"><a class="nav-link" href="/LichSuMuaHang">Lịch
							sử mua</a></li>
					<li class="nav-item"><a class="nav-link" href="/TrangChu?q=cart">Giỏ
							hàng</a></li>

					<c:if test="${sessionScope.ss.tendn == 'admin'}">
						<li class="nav-item"><a class="nav-link" href="ThongKeSach">ThongKeSach</a></li>
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

				<form action="/TrangChu" method="get" id="filterForm" class="d-flex me-auto"
					role="search" onsubmit="return optimizeForm()">

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

	<div class="container mt-4">
		<div class="row">
			<div class="col-sm-3 mb-4">
				<div class="card shadow-sm">

					<div class="card-header bg-primary text-white fw-bold">
						<i class="fas fa-list me-2"></i> Danh Mục Sách
					</div>

					<div class="card-body p-0">
						<div style="max-height: 500px; overflow-y: auto;">
							<ul class="list-group list-group-flush">

								<li
									class="list-group-item list-group-item-action ${empty maLoai ? 'text-primary fw-bold' : ''}"
									onclick="setCategory('')" style="cursor: pointer;"><i
									class="fas fa-book-open me-2"></i> Tất cả</li>

								<c:forEach var="L" items="${dsLoai}">
									<li
										class="list-group-item list-group-item-action d-flex justify-content-between align-items-center 
							${maLoai == L.maLoai ? 'text-primary fw-bold' : ''}	"
										onclick="setCategory('${L.maLoai}')" style="cursor: pointer;">

										${L.tenLoai}</li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-9">
				<c:choose>
					<c:when test="${param.q == 'cart'}">
						<jsp:include page="giohang.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
						<div class="row g-3">
							<c:if test="${empty dsSach}">
								<p class="text-danger">Không tìm thấy sản phẩm nào phù hợp.</p>
							</c:if>

							<c:forEach var="sach" items="${dsSach}">
								<div class="col-sm-4 col-md-3 mb-4">
									<div class="card h-100 shadow-sm">

										<div
											style="background-image: url('${sach.anh}'); height: 250px; object-fit: cover;"
											class="card-img-top"></div>

										<div class="card-body d-flex flex-column">

											<div class="card-title fw-bold line-clamp-2"
												title="${sach.tenSach}">${sach.tenSach}</div>

											<div class="card-text text-muted line-clamp-2 mb-2"
												title="${sach.tacGia}">${sach.tacGia}</div>

											<div class="text-danger fw-bold fs-5 mt-auto mb-3">
												<fmt:formatNumber value="${sach.gia }" type="number" />
												VNĐ
											</div>

											<div class="d-flex">
												<a href="/GioHang?ms=${sach.maSach}"><img
													src="https://minhkhai.com.vn/store2/images/buynow.jpg"
													alt=""> </a>
											</div>

										</div>
									</div>
								</div>
							</c:forEach>
						</div>

						<div class="d-flex justify-content-center mt-4">
							<c:if test="${not empty dsSach}">
								<c:set var="totalPages"
									value="${totalPages != null ? totalPages : 1}" />
								<c:set var="pageRange" value="2" />
								<!--số trang hiển thị mỗi bên-->

								<%-- Tính toán Phạm vi Hiển thị --%>
								<c:set var="beginPage" value="${pageIndexHienTai - pageRange}" />
								<c:set var="endPage" value="${pageIndexHienTai + pageRange}" />

								<%--Đảm bảo begin page >= 1--%>
								<c:if test="${beginPage < 1}">
									<c:set var="beginPage" value="1" />
									<c:set var="endPage" value="${pageIndexHienTai + 2*pageRange}" />
								</c:if>

								<%-- Đảm bảo endPage <= totalPages --%>
								<c:if test="${endPage > totalPages}">
									<c:set var="endPage" value="${totalPages}" />
									<%-- Điều chỉnh beginPage nếu tổng số trang không đủ 5-7 nút --%>
									<c:set var="beginPage" value="${endPage - 2 * pageRange}" />
									<c:if test="${beginPage < 1}">
										<c:set var="beginPage" value="1" />
									</c:if>
								</c:if>

								<nav>
									<ul class="pagination">
										<%-- Nút Previous --%>
										<li
											class="page-item ${pageIndexHienTai <= 1 ? 'disabled' : ''}">
											<a class="page-link" href="#"
											onclick="changePage(${pageIndexHienTai - 1})">Trước</a>
										</li>

										<%-- Hiển thị nút "..." nếu cần --%>
										<c:if test="${beginPage > 1}">
											<li class="page-item"><a class="page-link" href="#"
												onclick="changePage(1)">1</a></li>
											<c:if test="${beginPage > 2}">
												<li class="page-item disabled"><a class="page-link"
													href="#">...</a></li>
											</c:if>
										</c:if>

										<%-- Các nút số trang trong phạm vi --%>
										<c:forEach var="i" begin="${beginPage}" end="${endPage}">
											<li
												class="page-item ${pageIndexHienTai == i ? 'active' : ''}">
												<a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
											</li>
										</c:forEach>

										<%-- Hiển thị nút "..." nếu cần --%>
										<c:if test="${endPage < totalPages}">
											<c:if test="${endPage < totalPages - 1}">
												<li class="page-item disabled"><a class="page-link"
													href="#">...</a></li>
											</c:if>
											<li class="page-item"><a class="page-link" href="#"
												onclick="changePage(${totalPages})">${totalPages}</a></li>
										</c:if>

										<%-- Nút Next --%>
										<li
											class="page-item ${pageIndexHienTai >= totalPages ? 'disabled' : ''}">
											<a class="page-link" href="#"
											onclick="changePage(${pageIndexHienTai + 1})">Sau</a>
										</li>
									</ul>
								</nav>
							</c:if>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

	<script>
	    const filterForm = document.getElementById('filterForm');
	    const maLoaiInput = document.getElementById('maLoaiInput');
	    const pageIndexInput = document.getElementById('pageIndexInput');
	    const searchInput = document.getElementById('searchInput');

	    /**
	     * Đặt lại số trang về 1 khi người dùng thực hiện Tìm kiếm hoặc Lọc Danh mục mới
	     */
	    function resetPage() {
	        pageIndexInput.value = 1;
	    }

		function resetSearch() {
			searchInput.value = null;
		}

		function resetMaLoai() {
			maLoaiInput.value = null;
		}
	    
	    /**
	     * Đặt giá trị maLoai mới, reset trang và gửi form
	     * @param {string} maLoai - Mã loại được chọn.
	     */
	    function setCategory(maLoai) {
	        // 1. Thiết lập mã loại mới
	        maLoaiInput.value = maLoai;
	        
	        // 2. Reset số trang về 1
	        resetPage();

			// Reset tìm kiếm
			resetSearch();
	        
	        // 3. Gửi form chung
	        filterForm.submit();
	    }
	    
	    /**
	     * Thay đổi số trang và gửi form
	     * @param {number} newPage - Số trang muốn chuyển đến.
	     */
	    function changePage(newPage) {
	        const totalPages = ${totalPages != null ? totalPages : 1};
	        
	        if (newPage > 0 && newPage <= totalPages) {
	            // 1. Cập nhật số trang mới
	            pageIndexInput.value = newPage;
	            
	            // 2. Gửi form chung (giữ nguyên các bộ lọc khác)
	            filterForm.submit();
	        }
	    }
	    

		/**
		* Xử lý khi tìm kiếm
		*/
		function handleSearch() {
			// Reset danh mục
			resetMaLoai();

			// Reset page
			resetPage();

			filterForm.submit();
		}
		


	    /**
	     * Tối ưu hóa: Xóa các input ẩn rỗng hoặc có giá trị mặc định để URL sạch hơn
	     * Ví dụ: Bỏ &maLoai= khi maLoai = ''
	     */
	    function optimizeForm() {
	        const elements = filterForm.elements;
	        
	        // Duyệt qua tất cả các input
	        for (let i = 0; i < elements.length; i++) {
	            const element = elements[i];
	            
	            // Nếu giá trị rỗng hoặc là giá trị mặc định (page=1, maLoai=''), loại bỏ name
	            if (element.name && (element.value === '' || element.value === '1')) {
	                // Tạm thời xóa thuộc tính name nếu nó rỗng hoặc là mặc định 
	                // (trừ trường hợp người dùng gõ tìm kiếm rỗng, ta vẫn muốn gửi đi)
	                if (element.name !== 'search' && element.value === '') {
	                    element.removeAttribute('name');
	                }
	                if (element.name === 'page' && element.value === '1') {
	                    element.removeAttribute('name');
	                }
	            }
	        }
	        // Form tự động submit sau khi hàm này kết thúc (do không có preventDefault)
	        return true; 
	    }
	    
	</script>
</body>

</html>