<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Captcha</title>
</head>
<body>
    <h2>Đăng nhập</h2>
    <form action="check" method="post">
        Tên: <input type="text" name="username"><br>
        Mật khẩu: <input type="password" name="password"><br>

        <img src="captcha" alt="Captcha"><br>
        Nhập mã: <input type="text" name="captchaInput"><br>

        <input type="submit" value="Đăng nhập">
    </form>
</body>
</html>
