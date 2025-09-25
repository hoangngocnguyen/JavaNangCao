<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>
<body>
<%
    String err = request.getParameter("err");
%>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="trangchu.jsp">Trang chủ</a>
  </div>
</nav>

<!-- Login form -->
<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
  <div class="card shadow p-4" style="width: 100%; max-width: 400px;">
    <h3 class="text-center mb-4">Đăng nhập</h3>
    <form action="ktDangnhap.jsp" method="post">
      <div class="mb-3">
        <label class="form-label">Tên đăng nhập</label>
        <input type="text" class="form-control" name="username" required>
      </div>
      <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <input type="password" class="form-control" name="password" required>
      </div>
      <% if (err != null && err.equals("1")) { %>
        <p class="text-danger text-center">❌ Sai tên đăng nhập hoặc mật khẩu</p>
      <% } %>
      <button type="submit" class="btn btn-primary w-100 mt-2">Đăng nhập</button>
    </form>
    <!-- <div class="text-center mt-3">
      <a href="register.jsp">Chưa có tài khoản? Đăng ký</a>
    </div> -->
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
