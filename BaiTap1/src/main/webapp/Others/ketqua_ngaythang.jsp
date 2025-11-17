<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <% 
        String day = request.getParameter("DaySelect");
        String month = request.getParameter("MonthSelect");
        String year = request.getParameter("YearSelect");
        
        if (day == null || month == null || year == null) {
        	response.sendRedirect("ngaythang.jsp");
        	return;
        }
    %>
    <%
        int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);
        try {
            LocalDate date = LocalDate.of(y, m, d);
         %>
         <h1 class = "alert alert-success">Kết quả: <%=d%> / <%=m%> / <%=y %></h1>
        
         
        <%} catch (Exception e) { %>
        	<h1 class="alert alert-warning">Ngày không hợp lệ <h1>
        <%}%>
	
</body>
</html>