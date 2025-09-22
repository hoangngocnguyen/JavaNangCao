<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

    </head>

    <body>
        <% String temp_bcc=request.getParameter("bcc"); int bcc=1; if (temp_bcc !=null) {
            bcc=Integer.parseInt(temp_bcc); } %>

            <div class="container">
                <div class="row mt-5">
                    <div class="col-3 border-end border-4 border-primary" style="max-height: 600px; overflow: scroll;">

                        <% for (int i=1; i< 100; i++) {%>
                            <div style="margin-bottom: 10px;">
                                <a style="width: 100%;" class="btn <% if (i == bcc) {out.print(" btn-primary");} %>"
                                    href="bcc.jsp?bcc=<%=i %>">BCC <%=i%></a>
                            </div>
                            <%} %>

                    </div>
                    <div class="col-9">
                        <h1>Bảng cửu chương mới <%=bcc %>
                        </h1>

                        <div class="container" style="margin-top: 24px;">
                            <div class="row">
                                <% for (int i=1; i<=10; i++) { %>

                                    <div class="col-4 border py-2 text-center">
                                        <%=bcc%> x <%=i %> = <%= bcc*i %>
                                    </div>


                                    <%} %>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
    </body>

    </html>