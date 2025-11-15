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
		String temp_kq = request.getParameter("kq");
		String temp_a = request.getParameter("a");
		String temp_b = request.getParameter("b");
		String loi = request.getParameter("loi");
		
		if (temp_a == null || temp_b == null || temp_kq == null || loi == null) {
			temp_a = "0";
			temp_b = "0";
			temp_kq = "0";
		}
		
		int a = 0, b = 0;
		double kq = 0;
		
		a = Integer.parseInt(temp_a);
		b = Integer.parseInt(temp_b);
		kq = Double.parseDouble(temp_kq);
		
		// out.print(temp_kq);
		
	%>

	<form action="/">
		a = <input type="number" name="txta" value=<%=request.getAttribute("aa") %>> <hr>
		b = <input type="number" name="txtb" value=<%=request.getAttribute("bb")%>> <hr>
		Kq = <input type="text" name="txtkq" value=<%=request.getAttribute("kq")%>> <hr>
       <input type="submit" name="butc" value="+">
       <input type="submit" name="butt" value="-">
       <input type="submit" name="butn" value="*">
       <input type="submit" name="butchia" value="/">
	</form>
</body>
</html>