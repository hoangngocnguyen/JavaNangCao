<%@page import="java.util.Locale"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modal.GioHangBo"%>
<%@page import="modal.GioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


Giỏ hàng của bạn
<hr>

<c:if test="${not empty sessionScope.gh.ds }">
	<form id="form-checkbox" method="post" action="GioHangController"
		class="mb-3">
		<button type="submit" name="action" value="remove" class="btn btn-outline-danger btn-sm">
			Xóa sản phẩm đã chọn</button>
	</form>
</c:if>

<c:if test="${empty sessionScope.gh.ds }">
	<h5>Giỏ hàng của bạn đang trống!</h5>
</c:if>

<c:forEach var="gio" items="${ sessionScope.gh.ds}">
	<div class="d-flex mb-3 p-2 border-bottom">
		<div style="width: 40px" class="d-flex align-items-center">
			<input type="checkbox" name="checkboxDelete" value="${gio.maSach }"
				form="form-checkbox" />
		</div>
		<!-- Ảnh sách -->
		<div class="" style="width: 60px;">
			<img src="${ gio.anh}" alt="${ gio.tenSach}"
				class="img-fluid rounded">
		</div>

		<!-- Thông tin sách -->
		<div class="ms-2">
			<div class=" mb-1">
				<span class="fw-bold text-uppercase">${ gio.tenSach}</span><span
					style=""> (Tác giả: ${ gio.tacGia}) </span>
			</div>

			<div class="">
				<span class="me-2">Giá:</span> <span style="color: #6d2524;"
					class="fw-bold me-2"> <fmt:formatNumber type="number"
						value="${gio.gia }" /> đ
				</span> <span class="me-2">x</span>

				<!-- Form cập nhật -->
				<form action="GioHangController?update=true&ms=${gio.maSach }"
					method="post" class="d-inline-block">
					<input type="text" maxlength="4" pattern="\d{1,4}" name="quantity"
						value="${gio.soLuong }" min="1" class="text-center"
						style="width: 44px;">
					<button type="submit" class="btn btn-link cart-action p-0 ms-2">Cập
						nhật</button>
				</form>

				<!-- Form trả lại -->
				<form action="GioHangController?remove=true&ms=${gio.maSach }"
					method="post" class="d-inline ms-2">
					<button  type="submit"  class="btn btn-link cart-action p-0">Trả
						lại</button>
				</form>
			</div>
		</div>
	</div>
	<hr>
</c:forEach>






<!-- Tổng cộng -->
<c:if test="${not empty sessionScope.gh.ds }">
	<div class="fw-bold fs-5 mt-3">
		Tổng cộng:
		<fmt:formatNumber type="number" value="${sessionScope.gh.tongTien}" />
		VNĐ
	</div>
</c:if>

<hr>

<div class="d-flex justify-content-center gap-3">
	<div class="rounded border border-secondary p-2">
		<a class="text-decoration-none text-secondary-emphasis"
			href="GioHangController?deleted=all">Trả lại toàn bộ</a>
	</div>
	<div class="rounded border border-secondary p-2">
		<a class="text-decoration-none text-secondary-emphasis"
			href="TrangChuController">Tiếp tục mua hàng</a>
	</div>
	<button class="rounded border border-secondary p-2 text-white"
		style="background-color: #6d2524;"
		type="submit" form="form-checkbox" name="action" value="checkout">Đặt hàng
	</button>
</div>

<style>
.cart-action {
	color: #000;
	text-decoration: none;
	border-radius: 4px;
	transition: all 0.2s;
}

.cart-action:hover {
	background-color: #6d2524;
	color: #fff !important;
	text-decoration: underline;
}
</style>