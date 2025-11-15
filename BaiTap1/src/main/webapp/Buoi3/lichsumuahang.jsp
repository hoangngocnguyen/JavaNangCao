<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử mua hàng</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
    body { background-color: #f8f9fa; }
    .order-card { 
        border: 1px solid #dee2e6; 
        box-shadow: 0 4px 8px rgba(0,0,0,0.05); 
        transition: transform 0.2s;
    }
    .order-card:hover { transform: translateY(-3px); }
    .product-image { width: 60px; height: 60px; object-fit: cover; }
    /* Định dạng cho thông tin chi tiết */
    .detail-item {
        margin-bottom: 5px;
    }
</style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/">Hoàng Shop</a>
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
						href="/?q=cart">Giỏ hàng</a></li>
						
					<c:if test="${sessionScope.ss.tendn == 'admin'}">
						<li class="nav-item"><a class="nav-link"
						href="Dashboard">Dashboard</a></li>
					</c:if>
					

					<c:choose>
						<c:when test="${empty sessionScope.ss }">
							<li class="nav-item"><a class="nav-link"
								href="/DangNhap">Đăng nhập</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="/">Đăng xuất</a></li>
						</c:otherwise>
					</c:choose>
				</ul>

				<form action="/" method="get" id="filterForm"
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
			<h2 class="mb-4 text-primary"><i class="fas fa-history"></i> Lịch Sử Mua Hàng</h2>
        
        <div class="alert alert-info">Đơn hàng thành công. Vui lòng kiểm tra email để xem chi tiết.</div>
        
        <c:forEach var="ls" items="${dsLichSuMuaHang}" varStatus="loop">
            
            <div class="card order-card mb-4">
                
                <div class="card-header bg-white">
                    <div class="d-flex justify-content-between align-items-center">
                        <h5 class="m-0 text-secondary">Đơn hàng #${ls.maHoaDon}</h5>
                        <small class="text-muted">
                            <i class="fas fa-calendar-alt"></i> Ngày mua: 
                            <fmt:formatDate value="${ls.ngayMua}" pattern="dd/MM/yyyy HH:mm"/>
                        </small>
                    </div>
                </div>
                
                <div class="card-body p-3">
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            <h6 class="mb-1 text-primary">${ls.tensach}</h6>
                            <p class="mb-0 text-muted detail-item">
                                <i class="fas fa-cube"></i> Số lượng: 
                                <span class="fw-bold">${ls.soLuongMua}</span>
                            </p>
                            <p class="mb-0 text-muted detail-item">
                                <i class="fas fa-tag"></i> Giá gốc: 
                                <fmt:formatNumber value="${ls.gia}" type="currency" currencySymbol="VND"/>
                            </p>
                        </div>
                        
                        <div class="text-end">
                            <h5 class="fw-bold text-success mb-2">
                                <fmt:formatNumber value="${ls.thanhTien}" type="currency" currencySymbol="VND"/>
                            </h5>
                            
                            <c:choose>
                                <c:when test="${ls.damua}">
                                    <button class="btn btn-success btn-sm disabled">
                                        <i class="fas fa-check-circle"></i> Đã Thanh Toán
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <a href="ThanhToan?ma=${ls.maChiTietHoaDon}" class="btn btn-primary btn-sm">
                                        <i class="fas fa-credit-card"></i> Thanh Toán Ngay
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                
                <div class="card-footer text-end text-muted small">
                    Mã khách hàng: ${ls.makh} | Mã hóa đơn chi tiết: #${ls.maChiTietHoaDon}
                </div>
            </div>
            
        </c:forEach>

		<div class="d-flex justify-content-center mt-4">
			<c:if test="${totalPages != 0}">
				<c:set var="totalPages"
					value="${totalPages != null ? totalPages : 1}" />
				<c:set var="pageRange" value="2"/>	<!--số trang hiển thị mỗi bên-->

				<%-- Tính toán Phạm vi Hiển thị --%>
				<c:set var="beginPage" value="${currentPage - pageRange}" />
				<c:set var="endPage" value="${currentPage + pageRange}" />

				<%--Đảm bảo begin page >= 1--%>
				<c:if test="${beginPage < 1}">
					<c:set var="beginPage" value="1" />
					<c:set var="endPage" value="${currentPage + 2*pageRange}" />
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
						<li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
							<a class="page-link" href="/LichSuMuaHang?page=${currentPage - 1}"
							>Trước</a>
						</li>

						<%-- Hiển thị nút "..." nếu cần [1]...[n]--%>
						<c:if test="${beginPage > 1}">
							<li class="page-item"><a class="page-link" href="/LichSuMuaHang?page=1" >1</a></li>
							<c:if test="${beginPage > 2}">
								<li class="page-item disabled"><a class="page-link" href="#">...</a></li>
							</c:if>
						</c:if>

						<%-- Các nút số trang trong phạm vi --%>
						<c:forEach var="i" begin="${beginPage}" end="${endPage}">
							<li class="page-item ${currentPage == i ? 'active' : ''}">
								<a class="page-link" href="/LichSuMuaHang?page=${i}" >${i}</a>
							</li>
						</c:forEach>

						<%-- Hiển thị nút "..." nếu cần --%>
						<c:if test="${endPage < totalPages}">
							<c:if test="${endPage < totalPages - 1}">
								<li class="page-item disabled"><a class="page-link" href="#">...</a></li>
							</c:if>
							<li class="page-item"><a class="page-link" href="/LichSuMuaHang?page=${totalPages}">${totalPages}</a></li>
						</c:if>

						<%-- Nút Next --%>
						<li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
							<a class="page-link" href="/LichSuMuaHang?page=${currentPage + 1}"
							>Sau</a>
						</li>
					</ul>
				</nav>
			</c:if>
		</div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>