<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Khôi phục Mật khẩu</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<style>
.form-signin {
	max-width: 400px;
	padding: 15px;
}

.form-signin .form-floating:focus-within {
	z-index: 2;
}
</style>
</head>
<body
	class="text-center d-flex align-items-center py-4 bg-body-tertiary">

	<main class="form-signin w-100 m-auto">
		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				<p>${error}</p>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<c:if test="${not empty success}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<p>${success}</p>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<form action="/QuenMatKhau" method="POST">
			<h1 class="h3 mb-3 fw-normal">Khôi phục Mật khẩu</h1>

			<p class="mb-4 text-muted">Vui lòng nhập Tên đăng nhập của bạn.
				Chúng tôi sẽ gửi mật khẩu mới về địa chỉ email đã đăng ký.</p>

			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="username"
					name="username" placeholder="Tên đăng nhập của bạn" required>
				<label for="username">Tên đăng nhập (Username)</label>
			</div>

			<button class="btn btn-primary w-100 py-2" type="submit">
				Xác nhận và Gửi mật khẩu mới</button>

			<div class="mt-3">
				<a href="/DangNhap" class="text-primary text-decoration-none">
					Đã nhớ mật khẩu? Đăng nhập </a>
			</div>
		</form>
	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

</body>
</html>