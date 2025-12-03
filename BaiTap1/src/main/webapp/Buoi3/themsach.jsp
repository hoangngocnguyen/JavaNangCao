<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Nhập Sách</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body
	class="bg-gray-100 min-h-screen flex items-center justify-center p-4">

	<form method="post" action="/ThemSach"
		enctype="multipart/form-data"
		class="bg-white p-8 rounded-xl shadow-2xl w-full max-w-lg space-y-6">

		<h2 class="text-3xl font-bold text-gray-900 text-center mb-6">Thêm
			Sách Mới</h2>
		
		<div class="space-y-2">
			<label for="dsLoai"
				class="block text-sm font-medium text-gray-700">Loại Sách:</label> 
			<select name="maloai" id="dsLoai"
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150">
				<option value="">-- Chọn Loại Sách --</option>
				<c:forEach var="loai" items="${dsLoai}">
					<option value="${loai.maLoai}">${loai.tenLoai}</option>
				</c:forEach>
			</select>
		</div>

		<div class="space-y-2">
			<label for="txtmasach"
				class="block text-sm font-medium text-gray-700">Mã sách:</label> <input
				type="text" name="txtmasach" id="txtmasach" value=""
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập mã sách">
		</div>

		<div class="space-y-2">
			<label for="txttensach"
				class="block text-sm font-medium text-gray-700">Tên sách:</label> <input
				type="text" name="txttensach" id="txttensach" value=""
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập tên sách">
		</div>

		<div class="space-y-2">
			<label for="txttacgia"
				class="block text-sm font-medium text-gray-700">Tác giả:</label> <input
				type="text" name="txttacgia" id="txttacgia" value=""
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập tên tác giả">
		</div>
        
        <div class="space-y-2">
			<label for="txtsotap"
				class="block text-sm font-medium text-gray-700">Số tập:</label> <input
				type="number" name="txtsotap" id="txtsotap" value=""
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập số tập" min="1">
		</div>

		<div class="space-y-2">
			<label for="txtsoluong"
				class="block text-sm font-medium text-gray-700">Số lượng (Tồn kho):</label> <input
				type="number" name="txtsoluong" id="txtsoluong" value=""
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập số lượng" min="0">
		</div>

		<div class="space-y-2">
			<label for="txtgia" class="block text-sm font-medium text-gray-700">Giá:</label>
			<input type="number" name="txtgia" id="txtgia" value=""
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập giá bán" min="0">
		</div>

		<div class="space-y-2">
			<label for="txtfile" class="block text-sm font-medium text-gray-700">Ảnh:</label>
			<input type="file" name="txtfile" id="txtfile"
				class="w-full text-sm text-gray-500 
						  file:mr-4 file:py-2 file:px-4
						  file:rounded-full file:border-0
						  file:text-sm file:font-semibold
						  file:bg-blue-50 file:text-blue-700
						  hover:file:bg-blue-100 transition duration-150" />
		</div>
		
		<input type="submit" name="btnthem" value="Lưu Sách"
			class="w-full py-2.5 px-4 mt-6 
					  bg-blue-600 text-white font-semibold rounded-lg 
					  shadow-md hover:bg-blue-700 
					  focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 
					  cursor-pointer transition duration-200">
	</form>

</body>
</html>