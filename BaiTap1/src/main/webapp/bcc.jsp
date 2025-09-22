<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Bảng Cửu Chương</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <style>
        body {
            background-color: #e6f0fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .sidebar {
            max-height: 600px;
            overflow-y: auto;
            border-right: 4px solid #0d6efd;
            padding-right: 10px;
        }

        .sidebar a {
            display: block;
            margin-bottom: 8px;
            text-align: center;
            transition: all 0.3s;
        }

        .sidebar a:hover {
            background-color: #0d6efd;
            color: white !important;
            transform: scale(1.05);
        }

        .table-cell {
            background-color: white;
            border-radius: 8px;
            margin-bottom: 10px;
            box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
            transition: all 0.3s;
        }

        .table-cell:hover {
            background-color: #cfe2ff;
            transform: translateY(-2px);
        }

        h1 {
            color: #0d6efd;
            margin-bottom: 24px;
        }
    </style>
</head>

<body>
    <% 
        String temp_bcc = request.getParameter("bcc"); 
        int bcc = 1; 
        if (temp_bcc != null) {
            bcc = Integer.parseInt(temp_bcc); 
        } 
    %>

    <div class="container mt-5">
        <div class="row gx-5">
            <!-- Sidebar -->
            <div class="col-3 sidebar">
                <% for (int i = 1; i < 100; i++) { %>
                    <a class="btn w-100 <% if (i == bcc) { out.print(" btn-primary"); } else { out.print(" btn-outline-primary"); } %>" 
                       href="bcc.jsp?bcc=<%= i %>">BCC <%= i %></a>
                <% } %>
            </div>

            <!-- Main Content -->
            <div class="col-9">
                <h1>Bảng cửu chương <%= bcc %></h1>

                <div class="row gx-3">
                    <% for (int i = 1; i <= 10; i++) { %>
                        <div class="col-md-4">
                            <div class=" table-cell py-3 text-center">
                                <strong><%= bcc %> x <%= i %> = <%= bcc * i %></strong>
                            </div>
                        </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>

</body>

</html>
