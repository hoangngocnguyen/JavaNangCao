<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử đơn hàng của khách</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
/* CSS Tùy chỉnh nhẹ nhàng */
body {
	background-color: #f8f9fa; /* Nền sáng */
}

/* Bảng hiển thị */
.flat-table {
	border-radius: 0.75rem;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	overflow: hidden;
	background-color: white;
}

.flat-table thead th {
    background-color: #0d6efd; /* Màu xanh Primary */
    color: white;
    font-size: 0.9rem;
    vertical-align: middle;
}

/* Badge Trạng thái */
.status-badge-paid {
	background-color: #d1e7dd; /* Light green */
	color: #0f5132; /* Dark green text */
	font-weight: 600;
}

.status-badge-unpaid {
	background-color: #f8d7da; /* Light red */
	color: #842029; /* Dark red text */
	font-weight: 600;
}

/* Card tổng chi tiêu */
.summary-card {
    background: linear-gradient(45deg, #0d6efd, #00b4d8);
    color: white;
    border-radius: 1rem;
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
}
</style>
</head>
<body>
	<jsp:include page="sidebar-admin.jsp"></jsp:include>

	<div class="content-wrapper p-4">
		<div class="container-fluid">
			<h2 class="mb-4 text-primary fw-bold">
				<i class="fas fa-list-alt me-2"></i> Chi Tiết Đơn Hàng Trong Tháng
			</h2>
            
            <c:set var="tongChiTieuThang" value="0" />
			
			<c:choose>
				<c:when test="${not empty dsLichSu}">
					
                    <%-- 1. Tính tổng chi tiêu tháng để hiển thị tóm tắt --%>
                    <c:forEach var="item" items="${dsLichSu}">
                        <c:set var="tongChiTieuThang" value="${tongChiTieuThang + item.thanhTien}" />
                    </c:forEach>

                    <div class="row mb-5">
                        <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2">
                            <div class="summary-card p-4">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <p class="mb-1 fs-6 fw-light">Tổng Chi Tiêu Trong Tháng</p>
                                        <h3 class="mb-0 fw-bolder fs-2">
                                            <fmt:formatNumber value="${tongChiTieuThang}" type="number" pattern="#,###"/> đ
                                        </h3>
                                    </div>
                                    <i class="fas fa-money-bill-wave fa-3x opacity-75"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="table-responsive flat-table">
                        <table class="table table-hover align-middle mb-0">
                            <thead>
                                <tr>
                                    <th scope="col" class="text-center" style="width: 5%;">#</th>
                                    <th scope="col" style="width: 10%;">Mã HĐ</th>
                                    <th scope="col" style="width: 30%;">Tên Sách</th>
                                    <th scope="col" class="text-center" style="width: 15%;">Ngày Mua</th>
                                    <th scope="col" class="text-end" style="width: 10%;">Giá</th>
                                    <th scope="col" class="text-center" style="width: 10%;">SL</th>
                                    <th scope="col" class="text-end" style="width: 10%;">Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${dsLichSu}" varStatus="status">
                                    <tr class="${status.index % 2 == 0 ? '' : 'table-light'}">
                                        <td class="text-center text-muted">${status.index + 1}</td>
                                        
                                        <td class="fw-bold text-primary">${item.maHoaDon}</td>
                                        
                                        <td class="fw-medium">${item.tensach}</td>
                                        
                                        <td class="text-center small">
                                            <fmt:formatDate value="${item.ngayMua}" pattern="dd/MM HH:mm"/>
                                        </td>
                                        
                                        <td class="text-end">
                                            <fmt:formatNumber value="${item.gia}" type="number" pattern="#,###"/>
                                        </td>
                                        
                                        <td class="text-center fw-bold">${item.soLuongMua}</td>
                                        
                                        <td class="text-end fw-bolder text-danger">
                                            <fmt:formatNumber value="${item.thanhTien}" type="number" pattern="#,###"/> đ
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

				</c:when>
				<c:otherwise>
					<div class="alert alert-info text-center mt-5" role="alert">
						<i class="fas fa-info-circle me-2"></i> Khách hàng này chưa có chi tiết đơn hàng nào trong tháng hiện tại.
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>