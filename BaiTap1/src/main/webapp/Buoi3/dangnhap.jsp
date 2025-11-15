<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Trang chủ</a>
		</div>
	</nav>

	<!-- Login form -->
	<div class="container d-flex justify-content-center align-items-center"
		style="min-height: 80vh;">
		<div class="card shadow p-4" style="width: 100%; max-width: 400px;">
			<h3 class="text-center mb-4">Đăng nhập</h3>

			<form action="/DangNhap" method="post">
				<div class="mb-3">
					<label class="form-label">Tên đăng nhập</label> <input type="text"
						class="form-control" name="username" required value="${requestScope.username }">
				</div>
				<div class="mb-3">
					<label class="form-label">Mật khẩu</label> <input type="password"
						class="form-control" name="password" required value="${requestScope.password}">
				</div>

				<c:if test="${ not empty requestScope.err}">
					<p class="text-danger text-center">❌ Sai tên đăng nhập hoặc mật
						khẩu</p>
				</c:if>
				

				<c:if test="${sessionScope.loginAttempts >=3 }">
					<div class="g-recaptcha"
						data-sitekey="6Lchr-grAAAAACOdXi-U8wRVKUuhjyjWf4KmYoPZ"></div>
					<br>
				</c:if>
				
				<c:if test="${ not empty requestScope.errCaptcha}">
					<p class="text-danger text-center">❌ Vui lòng tick chọn captcha</p>
				</c:if>


				<button type="submit" class="btn btn-primary w-100 mt-2">Đăng
					nhập</button>
				<div class="mt-3 text-center">
				Chưa có tài khoản? <a href="/DangKy">Đăng ký ngay</a>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
