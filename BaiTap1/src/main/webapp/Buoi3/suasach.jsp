<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập Nhật Sách</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body
	class="bg-gray-100 min-h-screen flex items-center justify-center p-4">

	<!-- Form gửi đến /SuaSach -->
	<form method="post" action="/SuaSach"
		enctype="multipart/form-data"
		class="bg-white p-8 rounded-xl shadow-2xl w-full max-w-lg space-y-6">

		<h2 class="text-3xl font-bold text-gray-900 text-center mb-6">Cập Nhật Thông Tin Sách</h2>
		
		<!-- 1. Mã Sách: Readonly (Không cho sửa khóa chính) -->
		<div class="space-y-2">
			<label for="txtmasach"
				class="block text-sm font-medium text-gray-700">Mã sách (Không thể sửa):</label> 
			<input type="text" name="txtmasach" id="txtmasach" 
				value="${sach.maSach}" readonly
				class="w-full px-4 py-2 border border-gray-300 rounded-lg bg-gray-200 text-gray-500 cursor-not-allowed focus:outline-none">
		</div>

		<!-- 2. Loại Sách: Tự động chọn loại hiện tại -->
		<div class="space-y-2">
			<label for="dsLoai"
				class="block text-sm font-medium text-gray-700">Loại Sách:</label> 
			<select name="maloai" id="dsLoai"
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150">
				<c:forEach var="loai" items="${dsLoai}">
					<!-- So sánh mã loại để set attribute 'selected' -->
					<option value="${loai.maLoai}" ${loai.maLoai == sach.maLoai ? 'selected' : ''}>
						${loai.tenLoai}
					</option>
				</c:forEach>
			</select>
		</div>

		<div class="space-y-2">
			<label for="txttensach"
				class="block text-sm font-medium text-gray-700">Tên sách:</label> 
			<input type="text" name="txttensach" id="txttensach" 
				value="${sach.tenSach}"
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập tên sách">
		</div>

		<div class="space-y-2">
			<label for="txttacgia"
				class="block text-sm font-medium text-gray-700">Tác giả:</label> 
			<input type="text" name="txttacgia" id="txttacgia" 
				value="${sach.tacGia}"
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập tên tác giả">
		</div>
        
        <!-- Giả định đối tượng sach có thuộc tính soTap, nếu không có thể xóa div này -->
        <div class="space-y-2">
			<label for="txtsotap"
				class="block text-sm font-medium text-gray-700">Số tập:</label> 
			<input type="number" name="txtsotap" id="txtsotap" 
				value="${sach.soTap}"
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập số tập" min="1">
		</div>

		<div class="space-y-2">
			<label for="txtsoluong"
				class="block text-sm font-medium text-gray-700">Số lượng (Tồn kho):</label> 
			<input type="number" name="txtsoluong" id="txtsoluong" 
				value="${sach.soLuong}"
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập số lượng" min="0">
		</div>

		<div class="space-y-2">
			<label for="txtgia" class="block text-sm font-medium text-gray-700">Giá:</label>
			<input type="number" name="txtgia" id="txtgia" 
				value="${sach.gia}"
				class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
				placeholder="Nhập giá bán" min="0">
		</div>

		<!-- 3. Ảnh: Hiển thị ảnh cũ + Input file mới -->
		<div class="space-y-2">
			<label class="block text-sm font-medium text-gray-700">Ảnh hiện tại:</label>
			<div class="mb-2">
				<c:if test="${not empty sach.anh}">
					<img src="${sach.anh}" alt="Ảnh sách" class="h-32 w-auto object-cover rounded-md border border-gray-200">
				</c:if>
				<c:if test="${empty sach.anh}">
					<span class="text-gray-400 text-sm italic">Chưa có ảnh</span>
				</c:if>
			</div>
			
			<label for="txtfile" class="block text-sm font-medium text-gray-700">Chọn ảnh mới (Nếu muốn đổi):</label>
			<input type="file" name="txtfile" id="txtfile"
				class="w-full text-sm text-gray-500 
						  file:mr-4 file:py-2 file:px-4
						  file:rounded-full file:border-0
						  file:text-sm file:font-semibold
						  file:bg-blue-50 file:text-blue-700
						  hover:file:bg-blue-100 transition duration-150" />
			<!-- Gửi kèm đường dẫn ảnh cũ nếu người dùng không chọn ảnh mới (Xử lý ở Controller) -->
			<input type="hidden" name="anhCu" value="${sach.anh}">
		</div>
		
		<div class="flex gap-4 mt-6">
			<a href="/QuanLySach" 
			   class="w-1/3 py-2.5 px-4 bg-gray-500 text-white font-semibold rounded-lg shadow-md hover:bg-gray-600 text-center transition duration-200">
				Hủy
			</a>
			<input type="submit" name="btnsua" value="Cập Nhật"
				class="w-2/3 py-2.5 px-4 
						  bg-blue-600 text-white font-semibold rounded-lg 
						  shadow-md hover:bg-blue-700 
						  focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 
						  cursor-pointer transition duration-200">
		</div>
	</form>

</body>
</html>