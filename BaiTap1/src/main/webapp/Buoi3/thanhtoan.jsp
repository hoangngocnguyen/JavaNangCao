<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thanh Toán Thành Công</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<style>
    /* CSS tùy chỉnh cho giao diện hiện đại */
    .payment-success-card {
        max-width: 800px;
        margin: 50px auto;
        padding: 30px;
    }
    .qr-code-section {
        border: 1px dashed #ced4da;
        padding: 20px;
        border-radius: 8px;
        text-align: center;
    }
    .qr-code-section img {
        max-width: 180px;
        height: auto;
    }
</style>
</head>
<body class="bg-light">

    <div class="container">
        <div class="card payment-success-card shadow-lg">
            <div class="card-body">
                
                <div class="text-center mb-4">
                    <i class="fas fa-check-circle text-success" style="font-size: 3rem;"></i>
                    <h1 class="card-title text-success mt-3">Đơn Hàng Đã Đặt Thành Công!</h1>
                    <p class="text-muted">Vui lòng sử dụng thông tin dưới đây để hoàn tất thanh toán.</p>
                </div>
                
                <h4 class="mb-3 text-primary">Chi Tiết Hóa Đơn</h4>
                <div class="table-responsive mb-4">
                    <table class="table table-bordered table-striped">
                        <tbody>
                            <tr>
                                <th scope="row">Mã Chi Tiết Hóa Đơn</th>
                                <td><span class="fw-bold text-danger">${chiTietHoaDon.maChiTietHoaDon}</span></td>
                            </tr>
                            <tr>
                                <th scope="row">Mã Khách Hàng (Mã Đơn)</th>
                                <td>${chiTietHoaDon.makh}</td>
                            </tr>
                            <tr>
                                <th scope="row">Tên Sách</th>
                                <td>${chiTietHoaDon.tensach}</td>
                            </tr>
                            <tr>
                                <th scope="row">Giá (Đơn vị)</th>
                                <td><fmt:formatNumber value="${chiTietHoaDon.gia}" pattern="#,###" /> VND</td>
                            </tr>
                            <tr>
                                <th scope="row">Số Lượng Mua</th>
                                <td>${chiTietHoaDon.soLuongMua}</td>
                            </tr>
                            <tr>
                                <th scope="row">Thời Gian Đặt</th>
                                <td>
                                    <fmt:formatDate value="${chiTietHoaDon.ngayMua}" pattern="dd/MM/yyyy HH:mm"/>
                                </td>
                            </tr>
                            <tr class="table-success">
                                <th scope="row">Tổng Thanh Toán</th>
                                <td class="fw-bold fs-5 text-danger">
                                    <fmt:formatNumber value="${chiTietHoaDon.thanhTien}" pattern="#,###" /> VND
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="qr-code-section mb-5">
                    <h5 class="text-secondary mb-3"><i class="fas fa-qrcode"></i> Quét Mã QR để Thanh Toán Nhanh</h5>
                    <img src="images\ma_qr.jpg" alt="Mã QR Thanh toán" class="img-fluid rounded shadow-sm" style="width: 200px;">
                    <p class="mt-3">Nội dung chuyển khoản: <span class="fw-bold text-primary">HD${chiTietHoaDon.maChiTietHoaDon}</span></p>
                </div>
                
                <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                    <a href="/LichSuMuaHang" class="btn btn-outline-primary btn-lg">
                        <i class="fas fa-history me-2"></i> Lịch Sử Mua Hàng
                    </a>
                    
                    <a href="/TrangChu" class="btn btn-success btn-lg">
                        <i class="fas fa-home me-2"></i> Về Trang Chủ
                    </a>
                </div>
                
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>