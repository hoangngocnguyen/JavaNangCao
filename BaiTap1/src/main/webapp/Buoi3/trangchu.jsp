<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
  </head>

  <body>
    <% String name="" ; if (session.getAttribute("ss") !=null) { name=session.getAttribute("ss").toString(); } %>
      <div class="container">

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="#">Hoàng Shop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
              aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNavDropdown">
              <ul class="navbar-nav mb-2 mb-lg-0 me-2">
                <li class="nav-item">
                  <a class="nav-link active" href="#">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">Features</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="../GioHang/DatHang.jsp">Trang đặt hàng</a>
                </li>
                <% if (name.isEmpty()) { %>
                  <li class="nav-item"><a class="nav-link" href="dangnhap.jsp">Đăng nhập</a></li>
                  <% } else { %>
                    <li class="nav-item"><a class="nav-link" href="dangxuat.jsp">Đăng xuất</a></li>
                    <% } %>
              </ul>

              <!-- Search form -->
              <form class="d-flex me-auto" role="search">
                <input class="form-control me-2" type="search" placeholder="Tìm kiếm..." aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
              </form>

              <!-- User greeting -->
              <% if (!name.isEmpty()) { %>
                <span class="navbar-text text-white">
                  👋 Xin chào, <b>
                    <%= name %>
                  </b>
                </span>
                <% } %>
            </div>
          </div>
        </nav>
      </div>

      <!-- Main content -->
      <div class="container mt-4">
        <div class="row">
          <div class="col-sm-3">
            <div class="p-3 bg-light border rounded">Menu trái</div>
          </div>
          <div class="col-sm-9">
            <form action="hienthi.jsp" method="post">
              <div class="row g-2">
                <% for (int i=1; i <=50; i++) { %>
                  <div class="col-sm-4">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" name="mail" value="<%=i%>">
                      <label class="form-check-label">Mail thứ <%=i%></label>
                    </div>
                  </div>
                  <% } %>
              </div>
              <button type="submit" class="btn btn-primary mt-3">Send Mail</button>
            </form>
          </div>
        </div>
      </div>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
  </body>

  </html>