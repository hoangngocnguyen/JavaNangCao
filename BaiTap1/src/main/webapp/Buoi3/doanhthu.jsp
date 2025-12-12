<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Biểu Đồ Doanh Thu 3 Tháng Gần Nhất</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
<style>
body {
	background-color: #f4f7f6;
}

.chart-container {
	background: white;
	padding: 30px;
	border-radius: 1rem;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}
</style>
</head>

<body>
	<jsp:include page="sidebar-admin.jsp"></jsp:include>
	
	
	<div class="content-wrapper">
		<div class="container">
			<h2 class="mt-3 mb-4 text-dark fw-bold text-center">
				<i class="fas fa-chart-line me-2 text-primary"></i> Báo Cáo Doanh
				Thu 3 Tháng Gần Nhất
			</h2>
			<hr>

			<div class="row justify-content-center">
				<div class="col-lg-10 col-xl-8">
					<div class="chart-container">
						<h4 class="mb-4 text-center text-primary">Biểu Đồ Doanh Thu
							Theo Tháng</h4>
						<canvas id="doanhThuChart" style="max-height: 400px;"></canvas>
					</div>
				</div>
			</div>

			<hr class="my-5">

			<div class="row justify-content-center">
				<div class="col-lg-10">
					<h4 class="mb-3 text-center text-secondary">Chi Tiết Doanh Thu</h4>
					<table
						class="table table-bordered table-striped table-hover bg-white rounded-3 shadow-sm">
						<thead class="table-info">
							<tr>
								<th class="text-center">Tháng</th>
								<th class="text-center">Năm</th>
								<th class="text-end">Tổng Doanh Thu (VND)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dt" items="${dsDoanhThu}">
								<tr>
									<td class="text-center">${dt.thang}</td>
									<td class="text-center">${dt.nam}</td>
									<td class="text-end fw-bold text-success"><fmt:formatNumber
											value="${dt.tongDoanhThu}" pattern="#,###" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>

	<script>
        // 1. Chuẩn bị dữ liệu từ JSP/JSTL
        const doanhThuData = [];
        const labels = [];
        
        <c:forEach var="dt" items="${dsDoanhThu}">
            // Thêm giá trị doanh thu vào mảng data
            doanhThuData.push(${dt.tongDoanhThu});
            
            // Tạo nhãn cho trục X: Tháng/Năm
            labels.push('Tháng ${dt.thang}/${dt.nam}');
        </c:forEach>

        // 2. Khởi tạo Biểu đồ (Sử dụng Chart.js)
        const ctx = document.getElementById('doanhThuChart').getContext('2d');
        
        const doanhThuChart = new Chart(ctx, {
            type: 'bar', // Loại biểu đồ: Cột (bar), có thể đổi thành đường (line)
            data: {
                labels: labels, // Nhãn cho trục X (Tháng/Năm)
                datasets: [{
                    label: 'Doanh Thu (VND)',
                    data: doanhThuData,
                    // Thiết lập màu sắc và đường viền
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.7)', // Màu cho tháng 1
                        'rgba(54, 162, 235, 0.7)', // Màu cho tháng 2
                        'rgba(75, 192, 192, 0.7)'  // Màu cho tháng 3
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(75, 192, 192, 1)'
                    ],
                    borderWidth: 1,
                    borderRadius: 5 // Làm cột tròn hơn
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false, // Cho phép tùy chỉnh chiều cao
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Doanh Thu (VND)'
                        },
                        // Định dạng số tiền trên trục Y (tùy chọn)
                        ticks: {
                            callback: function(value, index, ticks) {
                                // Định dạng tiền tệ
                                return value.toLocaleString('vi-VN');
                            }
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: false // Ẩn legend nếu chỉ có 1 dataset
                    },
                    tooltip: {
                        // Định dạng tooltip hiển thị
                        callbacks: {
                            label: function(context) {
                                let label = context.dataset.label || '';
                                if (label) {
                                    label += ': ';
                                }
                                if (context.parsed.y !== null) {
                                    // Định dạng số tiền trong tooltip
                                    label += context.parsed.y.toLocaleString('vi-VN') + ' VND';
                                }
                                return label;
                            }
                        }
                    }
                }
            }
        });
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>