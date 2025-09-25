<%@page import="java.util.List"%>
<%@page import="tam.Hang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tam.CGioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-4">

    <!-- Form thêm sản phẩm -->
    <div class="card shadow-sm mb-4">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Thêm sản phẩm</h5>
        </div>
        <div class="card-body">
            <form method="post" action="Controller_DatHang.jsp" class="row g-3">
                <div class="col-md-4">
                    <label class="form-label">Tên hàng</label>
                    <input type="text" name="txtth" class="form-control" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Giá</label>
                    <input type="number" name="txtgia" class="form-control" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Số lượng</label>
                    <input type="number" name="txtsl" class="form-control" required>
                </div>
                <div class="col-12 text-end">
                    <button type="submit" name="un1" value="Dat Hang" class="btn btn-success">
                        <i class="bi bi-cart-plus"></i> Đặt hàng
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- Giỏ hàng -->
    <div class="card shadow-sm">
        <div class="card-header bg-secondary text-white d-flex justify-content-between align-items-center">
            <h5 class="mb-0">Giỏ hàng</h5>
            <form method="post" action="Xoa.jsp?delete=true" class="mb-0">
                <button type="submit" class="btn btn-sm btn-danger">Xóa tất cả</button>
            </form>
        </div>
        <div class="card-body">
            <%
                if (session.getAttribute("gh") != null) {
                    CGioHang gio = (CGioHang) session.getAttribute("gh");
                    List<Hang> ds = gio.ds;
            %>

            <form id="form-checkbox" method="post" action="Xoa.jsp" class="mb-3">
                <button type="submit" class="btn btn-outline-danger btn-sm">
                    Xóa sản phẩm đã chọn
                </button>
            </form>

            <div class="table-responsive">
                <table class="table table-bordered align-middle text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>Chọn</th>
                            <th>Tên sản phẩm</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th style="min-width: 120px;">Cập nhật</th>
                            <th>Xóa</th>
                            <th>Thành tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Hang hang : ds) { %>
                        <tr>
                            <td>
                                <input type="checkbox" name="checkbox" value="<%=hang.getTenhang()%>" form="form-checkbox">
                            </td>
                            <td><%= hang.getTenhang() %></td>
                            <td><%= hang.getGia() %></td>
                            <td><%= hang.getSoluong() %></td>
                            <td style="min-width: 120px;">
                                <form method="post" action="Sua.jsp?th=<%=hang.getTenhang()%>" class="d-flex justify-content-center">
                                    <input type="number" name="txtsuaSL" class="form-control form-control-sm w-50 me-2" min="1">
                                    <button type="submit" name="tt" value="Sua" class="btn btn-sm btn-warning">Sửa</button>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="Xoa.jsp?th=<%=hang.getTenhang()%>">
                                    <button type="submit" name="delete" value="Xóa" class="btn btn-sm btn-danger">Xóa</button>
                                </form>
                            </td>
                            <td><%= hang.getThanhtien() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <div class="text-end mt-3">
                <h5 class="fw-bold">Tổng tiền: <span class="text-success"><%= gio.tongTien() %></span></h5>
            </div>

            <% } else { %>
                <p class="text-muted">Giỏ hàng hiện đang trống.</p>
            <% } %>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap Icon CDN -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

</body>
</html>
