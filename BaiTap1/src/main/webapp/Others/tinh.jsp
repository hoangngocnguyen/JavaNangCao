<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
	KQ: <% 
			// Để ý người dùng đi vào trang tính luôn mà không cần vào maytinh
			String temp_a = request.getParameter("txta");
			String temp_b = request.getParameter("txtb");
			double ketqua = 0.0;
			
			if (temp_a == null || temp_b == null) {
				response.sendRedirect("maytinh.jsp");
				return;
			}

			int a = Integer.parseInt(temp_a);
			int b = Integer.parseInt(temp_b);
			
			String loi = "";
			
			
			// out.print(request.getParameter("butc"));
			
			if (request.getParameter("butc") != null) {
				ketqua = a + b;
				
				// out.print(a + b);
			}
			if (request.getParameter("butt") != null) {
				ketqua = a - b;
				
				//out.print(a - b);
			}
			if (request.getParameter("butn") != null) {
				ketqua = a * b;
				
				//out.print(a * b);
			}
			if (request.getParameter("butchia") != null) {
				
				try {
					ketqua = (double) a / b;
					
					//out.print(a / b);
				} catch (Exception e) {
					loi = "Không thể chia với số 0";
				}
			}
			response.sendRedirect("maytinh.jsp?a=" + a + "&b=" + b + "&kq=" + ketqua + "&loi=" + loi);
		%>
</body>
</html>