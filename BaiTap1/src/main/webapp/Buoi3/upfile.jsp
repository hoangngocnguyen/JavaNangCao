<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test upload file</title>
</head>
<body>
	<form method="post" action="/test" enctype= "multipart/form-data">
  Ho Ten: <input type="text" name="txthoten" value=""> <br>
  Đia chi: <input type="text" name="txtdiachi" value=""> <br>
  file: <input type="file" name="txtfile"><br>
  <input type="submit"> 
  </form> 
	
</body>
</html>