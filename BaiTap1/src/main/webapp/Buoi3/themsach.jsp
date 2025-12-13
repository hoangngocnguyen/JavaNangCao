<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Nhập Sách</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>

<body
	class="bg-gray-50 min-h-screen flex items-center justify-center p-4">

	<c:if test="${not empty errorMessage }">
		<div
			class="absolute top-4 right-4 p-4 rounded-lg bg-red-100 text-red-700 shadow-lg border border-red-300 transition duration-300 z-50"
			role="alert">
			<i class="fas fa-exclamation-triangle mr-2"></i> ${errorMessage }
		</div>
	</c:if>

	<form method="post" action="/ThemSach" enctype="multipart/form-data"
		class="bg-white p-6 sm:p-8 rounded-2xl shadow-2xl w-full max-w-2xl">
		
		<div class="border-b border-gray-200 pb-4 mb-6">
			<h2 class="text-3xl font-extrabold text-gray-900 text-center flex items-center justify-center">
				<span class="text-blue-600 mr-3"><i class="fas fa-plus-circle"></i></span>
				Thêm Sách Mới
			</h2>
			<p class="text-center text-gray-500 mt-1 text-sm">Điền đầy đủ thông tin chi tiết cho sản phẩm mới.</p>
		</div>

		<div class="space-y-6">
			
			<div class="grid grid-cols-1 md:grid-cols-2 gap-6">
				
				<div class="space-y-2">
					<label for="dsLoai" class="block text-sm font-medium text-gray-700">Loại
						Sách:</label>
					<div class="relative">
						<select name="maloai" id="dsLoai" required
							class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150 appearance-none">
							<option value="">-- Chọn Loại Sách --</option>
							<c:forEach var="loai" items="${dsLoai}">
								<option value="${loai.maLoai}">${loai.tenLoai}</option>
							</c:forEach>
						</select>
						<i class="fas fa-tag absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
					</div>
				</div>

				<div class="space-y-2">
					<label for="txtmasach"
						class="block text-sm font-medium text-gray-700">Mã sách:</label>
					<div class="relative">
						<input type="text" name="txtmasach" id="txtmasach" value="" required
							class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
							placeholder="VD: LT001">
						<i class="fas fa-barcode absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
					</div>
				</div>
			</div>

			<div class="space-y-2">
				<label for="txttensach"
					class="block text-sm font-medium text-gray-700">Tên sách:</label>
				<div class="relative">
					<input type="text" name="txttensach" id="txttensach" value="" required
						class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
						placeholder="Nhập tên sách">
					<i class="fas fa-book absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
				</div>
			</div>

			<div class="space-y-2">
				<label for="txttacgia"
					class="block text-sm font-medium text-gray-700">Tác giả:</label>
				<div class="relative">
					<input type="text" name="txttacgia" id="txttacgia" value="" required
						class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
						placeholder="Nhập tên tác giả">
					<i class="fas fa-user-edit absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
				</div>
			</div>

			<div class="grid grid-cols-1 md:grid-cols-2 gap-6">
				<div class="space-y-2">
					<label for="txtsotap" class="block text-sm font-medium text-gray-700">Số
						tập:</label>
					<div class="relative">
						<input type="number" name="txtsotap" id="txtsotap" value="" required
							class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
							placeholder="Số tập (min: 1)" min="1">
						<i class="fas fa-layer-group absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
					</div>
				</div>

				<div class="space-y-2">
					<label for="txtsoluong"
						class="block text-sm font-medium text-gray-700">Số lượng
						(Tồn kho):</label>
					<div class="relative">
						<input type="number" name="txtsoluong" id="txtsoluong"
							value="" required
							class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
							placeholder="Tồn kho (min: 0)" min="0">
						<i class="fas fa-cubes absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
					</div>
				</div>
			</div>
			
			<div class="space-y-2">
				<label for="txtgia" class="block text-sm font-medium text-gray-700">Giá Bán (VNĐ):</label>
				<div class="relative">
					<input type="number" name="txtgia" id="txtgia" value="" required
						class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
						placeholder="Nhập giá bán (min: 0)" min="0">
					<i class="fas fa-money-bill-wave absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
				</div>
			</div>

			<div class="space-y-2 pt-2">
				<label for="txtfile" class="block text-sm font-medium text-gray-700">Ảnh Bìa Sách:</label>
				<input type="file" name="txtfile" id="txtfile" required
					class="w-full text-sm text-gray-500 border border-gray-300 rounded-lg p-2 bg-gray-50
							file:mr-4 file:py-2 file:px-4
							file:rounded-full file:border-0
							file:text-sm file:font-semibold
							file:bg-blue-50 file:text-blue-700
							hover:file:bg-blue-100 transition duration-150 cursor-pointer" />
			</div>

		</div>
		
		<div class="pt-6 border-t border-gray-200 mt-6">
			<button type="submit" name="btnthem"
				class="w-full py-3 px-4 
						bg-blue-600 text-white text-lg font-bold rounded-lg 
						shadow-xl hover:bg-blue-700 
						focus:outline-none focus:ring-4 focus:ring-blue-300 focus:ring-offset-2 
						cursor-pointer transition duration-200 transform hover:scale-[1.01]">
				<i class="fas fa-save mr-2"></i> LƯU SÁCH MỚI
			</button>
		</div>
		
	</form>

</body>
</html>