<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký Tài khoản</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Trang
				chủ</a>
		</div>
	</nav>
	<div class="container py-5">
		<div class="row justify-content-center">
			<div class="col-md-6 col-lg-5">
				<div class="card shadow-lg">
					<div class="card-body p-4 p-md-5">
						<h2 class="card-title text-center mb-4 fw-bold">Đăng Ký Tài
							Khoản</h2>

						<c:if test="${not empty errorMessage}">
							<div class="alert alert-danger" role="alert">
								<c:out value="${errorMessage}" />
							</div>
						</c:if>

						<form action="/DangKy" method="POST">

							<div class="mb-3">
								<label for="username" class="form-label">Tên đăng nhập</label> <input
									type="text"
									class="form-control <c:if test="${not empty errorUsername}">is-invalid</c:if>"
									id="username" name="username" value="${param.username}"
									required>
								<c:if test="${not empty errorUsername}">
									<div class="invalid-feedback">
										<c:out value="${errorUsername}" />
									</div>
								</c:if>
							</div>

							<div class="mb-3">
								<label for="nameInput" class="form-label">Họ và Tên</label> <input
									type="text" class="form-control" id="nameInput"
									name="nameInput" value="${param.nameInput}" required>
							</div>

							<div class="mb-3">
								<label for="email" class="form-label">Email</label> <input
									type="email"
									class="form-control <c:if test="${not empty errorEmail}">is-invalid</c:if>"
									id="email" name="email" value="${param.email}" required>
								<c:if test="${not empty errorEmail}">
									<div class="invalid-feedback">
										<c:out value="${errorEmail}" />
									</div>
								</c:if>
							</div>

							<div class="mb-3">
								<label for="password" class="form-label">Mật khẩu</label> <input
									type="password"
									class="form-control <c:if test="${not empty errorPassword}">is-invalid</c:if>"
									id="password" name="password" required>
								<c:if test="${not empty errorPassword}">
									<div class="invalid-feedback">
										<c:out value="${errorPassword}" />
									</div>
								</c:if>
							</div>

							<div class="mb-4">
								<label for="confirmPassword" class="form-label">Xác nhận
									Mật khẩu</label> <input type="password"
									class="form-control <c:if test="${not empty errorPassword}">is-invalid</c:if>"
									id="confirmPassword" name="confirmPassword" required>
							</div>

							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary btn-lg">Đăng
									Ký</button>
							</div>

						</form>

						<div class="text-center mt-3">
							<p class="text-muted">
								Đã có tài khoản? <a href="/DangNhap"
									class="text-decoration-none">Đăng nhập ngay</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>