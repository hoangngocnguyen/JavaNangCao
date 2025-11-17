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
	// Kiểm tra có session?
	if (session.getAttribute("ss") == null) {
		session.setAttribute("ss", (int) 0);	// tạo session
	}


	String temp = request.getParameter("txtn");
	
	int n = 0;
	if (temp != null) {
		n  = Integer.parseInt(temp);
		int s = (int) session.getAttribute("ss");
		
		s += n;
		
		session.setAttribute("ss", s);
		out.print(s);
		
	}

%>
 <form action="tinhtong.jsp">
        <input type="number" id="inputText" name="txtn" required placeholder="Nhập gì đó...">
        <button type="submit">Tính tổng</button>
    </form>
</body>
</html>