<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Thống Kê</title>

<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
    body {
        background-color: #f4f7f6;
    }
    .card-stat {
        border-left: 5px solid;
        border-radius: 0.5rem;
    }
    .card-stat-primary { border-left-color: #0d6efd; }
    .card-stat-info { border-left-color: #0dcaf0; }
    .card-stat-warning { border-left-color: #ffc107; }
    .table-responsive {
        box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.05);
        border-radius: 0.5rem;
        background-color: white;
    }
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="TrangChuController">Hoàng Shop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav mb-2 mb-lg-0 me-2">
					<li class="nav-item"><a class="nav-link"
						href="/LichSuMuaHang">Lịch sử mua</a></li>
					<li class="nav-item"><a class="nav-link"
						href="TrangChuController?q=cart">Giỏ hàng</a></li>
						
					<c:if test="${sessionScope.ss.tendn == 'admin'}">
						<li class="nav-item"><a class="nav-link"
						href="Dashboard">Dashboard</a></li>
					</c:if>
					

					<c:choose>
						<c:when test="${empty sessionScope.ss }">
							<li class="nav-item"><a class="nav-link"
								href="DangNhapController">Đăng nhập</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="DangXuatController">Đăng xuất</a></li>
						</c:otherwise>
					</c:choose>
				</ul>

				<form action="TrangChuController" method="get" id="filterForm"
					class="d-flex me-auto" role="search"
					onsubmit="return optimizeForm()">

					<input class="form-control me-2" type="text" name="search"
						id="searchInput" placeholder="Tìm kiếm..." aria-label="Search"
						value="${search != null ? search : ''}">

					<button class="btn btn-outline-light" type="submit"
						onclick="resetPage()">Search</button>

					<input type="hidden" name="maLoai" id="maLoaiInput"
						value="${maLoai != null ? maLoai : ''}"> 
					<input
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
    <div class="container my-5">
			<h2 class="mb-4 text-dark"><i class="fas fa-chart-line"></i> Dashboard Thống Kê Kho Sách</h2>
        
        <!-- Tổng quan Thống kê (Sử dụng Card để làm nổi bật) -->
        <div class="row mb-5">
            
            <!-- Card 1: Tổng số Loại Sách (Category Count) -->
            <div class="col-md-4 mb-3">
                <div class="card card-stat card-stat-primary">
                    <div class="card-body">
                        <h5 class="card-title text-primary"><i class="fas fa-folder me-2"></i> Tổng số Loại Sách</h5>
                        <p class="card-text fs-3 fw-bold">${thongKeTongQuan.tongSoLoai}</p>
                    </div>
                </div>
            </div>
            
            <!-- Card 2: Tổng số Sách (Item Count) -->
            <div class="col-md-4 mb-3">
                <div class="card card-stat card-stat-info">
                    <div class="card-body">
                        <h5 class="card-title text-info"><i class="fas fa-book me-2"></i> Tổng số Đầu Sách</h5>
                        <p class="card-text fs-3 fw-bold">${thongKeTongQuan.tongSoSach}</p>
                    </div>
                </div>
            </div>
            
            <!-- Card 3: Tổng số Lượng Tồn (Total Quantity) -->
            <div class="col-md-4 mb-3">
                <div class="card card-stat card-stat-warning">
                    <div class="card-body">
                        <h5 class="card-title text-warning"><i class="fas fa-box-open me-2"></i> Tổng Số Lượng Tồn</h5>
                        <p class="card-text fs-3 fw-bold">${thongKeTongQuan.tongTonKho}</p>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Bảng Thống kê chi tiết (Responsive Table) -->
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
                    <c:forEach var="tkSach" items="${dsThongKeKhoSach}" varStatus="status">
                        <tr>
                            <th scope="row">${(currentPage - 1) * 10 + status.index + 1}</th>
                            <td><span class="fw-bold text-primary">${tkSach.tenLoai}</span></td>
                            <td>${tkSach.soSach}</td>
                            <td><span class="badge bg-secondary">${tkSach.tongSL}</span></td>
                            <td>
                                <span class="fw-bold text-success">
                                    <fmt:formatNumber value="${tkSach.tbcGia}" type="currency" currencySymbol="VND"/>
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty dsThongKeKhoSach}">
                        <tr>
                            <td colspan="5" class="text-center text-muted py-4">Không tìm thấy dữ liệu thống kê nào.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
        
        <!-- --- PHÂN TRANG (PAGINATION) --- -->
        
        <div class="d-flex justify-content-between align-items-center mt-4">
            <small class="text-muted">
                Trang hiện tại: <span class="fw-bold">${currentPage}</span> / Tổng số trang: <span class="fw-bold">${totalPages}</span>
            </small>
        
            <c:if test="${totalPages != 0}">
                <c:set var="pageRange" value="2"/> 
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
                        <%-- Nút Previous --%>
                        <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                            <a class="page-link" href="Dashboard?page=${currentPage - 1}" tabindex="-1" aria-disabled="${currentPage <= 1}">
                                <i class="fas fa-angle-left"></i> Trước
                            </a>
                        </li>

                        <%-- Các nút số trang trong phạm vi --%>
                        <c:forEach var="i" begin="${beginPage}" end="${endPage}">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="Dashboard?page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <%-- Nút Next --%>
                        <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                            <a class="page-link" href="Dashboard?page=${currentPage + 1}" aria-disabled="${currentPage >= totalPages}">
                                Sau <i class="fas fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>