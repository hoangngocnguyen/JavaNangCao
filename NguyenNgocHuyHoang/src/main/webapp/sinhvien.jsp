<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản Lý Sinh Viên</title>
<script src="https://cdn.tailwindcss.com"></script>
<script>
	// Cấu hình Tailwind CSS (Tùy chọn, để giữ nguyên màu sắc mặc định)
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
					<input
						type="text" name="search" value="${search}" 
						placeholder="Tìm kiếm theo Tên, Địa chỉ..."
						class="flex-grow p-2 border border-gray-300 rounded-lg focus:ring-primary focus:border-primary transition duration-150">
					<button type="submit"
						class="bg-primary hover:bg-primary-dark text-white font-semibold py-2 px-4 rounded-lg shadow-md transition duration-300">
						Tìm Kiếm</button>
				</form>

				<a href="Hoang_ThongKeController"
					class="w-full md:w-auto text-center bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-4 rounded-lg shadow-md transition duration-300">
					Xem Thống Kê </a>
			</div>
		</header>

		<h2 class="text-2xl font-bold text-gray-800 mb-6 border-b pb-2">Danh
			Sách Sinh Viên</h2>

		<div class="shadow-lg rounded-lg overflow-x-auto bg-white">

			<table class="w-full min-w-full divide-y divide-gray-200">
				<thead class="bg-gray-100">
					<tr>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Mã
							SV</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Họ
							Tên</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider hidden sm:table-cell">Địa
							Chỉ</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider hidden lg:table-cell">Email</th>
						<th
							class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">ĐTB</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider hidden sm:table-cell">Mã
							Lớp</th>
					</tr>
				</thead>
				<tbody class="bg-white divide-y divide-gray-200">
					<c:forEach var="sv" items="${ds}">
						<tr>
							<td
								class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${sv.maSV}</td>
							<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800">${sv.hoTen}</td>
							<td
								class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 hidden sm:table-cell">${sv.diaChi}</td>
							<td
								class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 hidden lg:table-cell">${sv.email}</td>
							<td
								class="px-6 py-4 whitespace-nowrap text-sm font-bold text-right">
								<span
								class="<c:choose>
                                    <c:when test="${sv.DTB >= 8.0}">text-blue-600</c:when>
                                    <c:when test="${sv.DTB >= 6.5}">text-green-600</c:when>
                                    <c:otherwise>text-red-600</c:otherwise>
                                </c:choose>">
									<fmt:formatNumber value="${sv.DTB}" pattern="#0.0" />
							</span>
							</td>
							<td
								class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 hidden sm:table-cell">${sv.maLop}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:if test="${empty ds}">
				<div class="p-6 text-center text-gray-500">Không tìm thấy sinh
					viên nào trong danh sách.</div>
			</c:if>

		</div>

		<footer class="mt-8 text-center text-gray-400 text-sm"> ©
			2025 Nguyễn Ngọc Huy Hoàng </footer>
	</div>

</body>
</html>