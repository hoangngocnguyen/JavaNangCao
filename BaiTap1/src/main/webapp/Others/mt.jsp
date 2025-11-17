<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
			// Để ý người dùng đi vào trang tính luôn mà không cần vào maytinh
			String temp_a = request.getParameter("txta");
			String temp_b = request.getParameter("txtb");
			double ketqua = 0.0;
			
			if (temp_a == null || temp_b == null) {
				temp_a = "0";
				temp_b = "0";
			}

			int a = Integer.parseInt(temp_a);
			int b = Integer.parseInt(temp_b);
			
			String loi = "";
			
			if (request.getParameter("butc") != null) {
				ketqua = a + b;
			}
			if (request.getParameter("butt") != null) {
				ketqua = a - b;
			}
			if (request.getParameter("butn") != null) {
				ketqua = a * b;
			}
			if (request.getParameter("butchia") != null) {
				
				try {
					ketqua = (double) a / b;
				} catch (Exception e) {
					loi = "Không thể chia với số 0";
				}
			}
		%>

	<form action="mt.jsp">
		a = <input type="number" name="txta" value=<%=a%>> <hr>
		b = <input type="number" name="txtb" value=<%=b%>> <hr>
		Kq = <input type="text" name="txtkq" value=<%=ketqua %>> <hr>
       <input type="submit" name="butc" value="+">
       <input type="submit" name="butt" value="-">
       <input type="submit" name="butn" value="*">
       <input type="submit" name="butchia" value="/">
	</form>
</body>
</html>