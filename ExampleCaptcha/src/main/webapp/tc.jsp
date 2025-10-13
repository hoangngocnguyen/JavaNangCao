<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Đăng nhập có Captcha</title>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

	<h2>Form đăng nhập</h2>
    <form action="testServlet" method="post">
        <label>Tên đăng nhập: </label>
        <input type="text" name="username"><br><br>
        
        <label>Mật khẩu: </label>
        <input type="password" name="password"><br><br>
        
        <div class="g-recaptcha" data-sitekey="6Lchr-grAAAAACOdXi-U8wRVKUuhjyjWf4KmYoPZ"></div>
        <br>
        <input type="submit" value="Đăng nhập">
    </form>
	
</body>

</html>
