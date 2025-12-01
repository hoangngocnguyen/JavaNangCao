<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thống Kê Điểm Trung Bình</title>
<script src="https://cdn.tailwindcss.com"></script>
<script>
	// Cấu hình Tailwind CSS (Sử dụng cấu hình màu đã dùng ở trang trước)
	tailwind.config = {
		theme : {
			extend : {
				colors : {
					'primary' : '#4f46e5', // Màu xanh tím đậm
					'primary-dark' : '#4338ca',
				}
			}
		}
	}
</script>
</head>
<body class="bg-gray-50 min-h-screen">

	<div class="container mx-auto p-4 sm:p-6 lg:p-8">
		<header class="bg-white shadow-md rounded-lg p-4 mb-8">
			<div
				class="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">

				<form action="Hoang_SinhVienController" method="get"
					class="w-full md:w-2/3 flex space-x-2">
					<input type="text"
						name="search" value="${search}"
						placeholder="Tìm kiếm theo Tên, Địa chỉ..."
						class="flex-grow p-2 border border-gray-300 rounded-lg focus:ring-primary focus:border-primary transition duration-150">
					<button type="submit"
						class="bg-primary hover:bg-primary-dark text-white font-semibold py-2 px-4 rounded-lg shadow-md transition duration-300">
						Tìm Kiếm</button>
				</form>

				<c:if test="${dangthongke != '1' }">
					<a href="Hoang_ThongKeController"
						class="w-full md:w-auto text-center bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-4 rounded-lg shadow-md transition duration-300">
						Xem Thống Kê </a>
				</c:if>

				<c:if test="${dangthongke == '1' }">
					<button disabled
						class="w-full md:w-auto text-center bg-gray-400 text-white font-semibold py-2 px-4 rounded-lg shadow-md cursor-not-allowed">
						Đang xem Thống Kê</button>
				</c:if>

			</div>
		</header>

		<h2
			class="text-3xl font-extrabold text-gray-900 mb-6 border-b-2 border-primary pb-3">
			📊 Báo Cáo Thống Kê Điểm Trung Bình Theo Lớp</h2>

		<div class="shadow-lg rounded-lg overflow-x-auto bg-white">

			<table class="w-full min-w-full divide-y divide-gray-200">
				<thead class="bg-gray-100">
					<tr>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/6">Mã
							Lớp</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/2">Tên
							Lớp</th>
						<th
							class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider w-1/6">DTB
							Lớp</th>
					</tr>
				</thead>
				<tbody class="bg-white divide-y divide-gray-200">

					<c:forEach var="thongke" items="${ds}">
						<tr class="hover:bg-gray-50">
							<td
								class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${thongke.maLop}</td>
							<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800">${thongke.tenLop}</td>

							<td
								class="px-6 py-4 whitespace-nowrap text-right text-lg font-bold">
								<span
								class="<c:choose>
                                    <c:when test="${thongke.tbc >= 8.0}">text-blue-600</c:when>
                                    <c:when test="${thongke.tbc >= 6.5}">text-green-600</c:when>
                                    <c:otherwise>text-yellow-600</c:otherwise>
                                </c:choose>">
									<fmt:formatNumber value="${thongke.tbc}" pattern="#0.00" />
							</span>
							</td>
						</tr>
					</c:forEach>

					<c:if test="${empty ds}">
						<tr>
							<td colspan="4" class="p-6 text-center text-gray-500 bg-gray-50">
								Không có dữ liệu thống kê nào được tìm thấy.</td>
						</tr>
					</c:if>

				</tbody>
			</table>
		</div>

		<footer class="mt-12 text-center text-gray-400 text-sm"> ©
			2025 Nguyễn Ngọc Huy Hoàng </footer>
	</div>

</body>
</html>