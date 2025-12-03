<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Loại Sách Mới</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body
	class="bg-gray-100 min-h-screen flex flex-col items-center justify-center p-4">

	<div class="w-full max-w-lg">

		<div class="mb-4">
			<a href="/QuanLyLoai"
				class="inline-flex items-center text-sm font-medium text-blue-600 hover:text-blue-800 transition duration-150">
				<svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor"
					viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
					<path stroke-linecap="round" stroke-linejoin="round"
						stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18"></path></svg> Quay lại
				Quản lý Loại Sách
			</a>
		</div>

		<form method="post" action="/ThemLoai"
			class="bg-white p-8 rounded-xl shadow-2xl space-y-6">

			<h2 class="text-3xl font-bold text-gray-900 text-center mb-6">Thêm
				Loại Sách Mới</h2>

			<c:if test="${not empty errorMessage}">
				<div
					class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative"
					role="alert">
					<strong class="font-bold">Lỗi:</strong> <span
						class="block sm:inline">${errorMessage}</span>
				</div>
			</c:if>

			<div class="space-y-2">
				<label for="maLoai" class="block text-sm font-medium text-gray-700">Mã
					Loại (Ví dụ: KHTN, VH):</label> <input type="text" name="maLoai"
					id="maLoai" value="${param.maLoai != null ? param.maLoai : ''}"
					class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
					placeholder="Nhập mã loại" required>
			</div>

			<div class="space-y-2">
				<label for="tenLoai" class="block text-sm font-medium text-gray-700">Tên
					Loại Sách:</label> <input type="text" name="tenLoai" id="tenLoai"
					value="${param.tenLoai != null ? param.tenLoai : ''}"
					class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
					placeholder="Nhập tên loại" required>
			</div>

			<input type="submit" name="btnthem" value="Thêm Loại Sách"
				class="w-full py-2.5 px-4 mt-6 
						  bg-blue-600 text-white font-semibold rounded-lg 
						  shadow-md hover:bg-blue-700 
						  focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 
						  cursor-pointer transition duration-200">
		</form>
	</div>

</body>
</html>