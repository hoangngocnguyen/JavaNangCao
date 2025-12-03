package modal.Sach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modal.KetNoi;

public class SachDao {
	public ArrayList<Sach> getSach() throws Exception {
		ArrayList<Sach> ds = new ArrayList<Sach>();

		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from sach";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Duyệt result set, đưa vào List
		while (rs.next()) {
			String maSach = rs.getString("masach");
			String tenSach = rs.getString("tensach");
			String tacGia = rs.getString("tacgia");
			long soLuong = rs.getLong("soluong");
			long gia = rs.getLong("gia");
			String anh = rs.getString("anh");
			String maLoai = rs.getString("maloai");
			int soTap = rs.getInt("sotap");

			ds.add(new Sach(maSach, tenSach, tacGia, soLuong, gia, anh, maLoai, soTap));
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();
		return ds;
	}

	public Sach timSachTuMa(String masach) throws Exception {
		Sach sach = new Sach();
		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from sach where masach = ?";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, masach);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Duyệt result set, đưa vào List
		while (rs.next()) {
			String maSach = rs.getString("masach");
			String tenSach = rs.getString("tensach");
			String tacGia = rs.getString("tacgia");
			long soLuong = rs.getLong("soluong");
			long gia = rs.getLong("gia");
			String anh = rs.getString("anh");
			String maLoai = rs.getString("maloai");

			int soTap = rs.getInt("sotap");
			sach = new Sach(maSach, tenSach, tacGia, soLuong, gia, anh, maLoai, soTap);
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();
		return sach;
	}

	public ArrayList<Sach> locSachPhanTrang(String maLoaiParam, String search, int offset, int pageSize)
			throws Exception {
		ArrayList<Sach> ds = new ArrayList<Sach>();

		// --- Chuẩn bị Tham số (Làm sạch và kiểm tra NULL/Rỗng) ---
		// Nếu là null hoặc rỗng, gán lại là null
		String finalSearch = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
		String finalMaLoai = (maLoaiParam != null && !maLoaiParam.trim().isEmpty()) ? maLoaiParam.trim() : null;

		// --- Kết nối CSDL ---
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		Connection conn = kn.cn;

		// --- Xây dựng SQL Động và List Tham số ---
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();

		// Bắt đầu truy vấn với WHERE 1=1
		sqlBuilder.append("SELECT * FROM sach s JOIN loai l ON s.maloai = l.maloai WHERE 1=1");

		// 1. Lọc theo Tên sách (Search)
		if (finalSearch != null) {
			sqlBuilder.append(" AND tensach LIKE ?");
			params.add("%" + finalSearch + "%"); // Giá trị LIKE
		}

		// 2. Lọc theo Mã loại
		if (finalMaLoai != null) {
			sqlBuilder.append(" AND s.maloai = ?");
			params.add(finalMaLoai);
		}

		// 3. Phân trang và Sắp xếp

		sqlBuilder.append(" ORDER BY NgayNhap DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

		// Thêm tham số OFFSET và FETCH vào cuối List
		params.add(offset);
		params.add(pageSize);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			preparedStatement = conn.prepareStatement(sqlBuilder.toString());

			// Thiết lập tham số theo thứ tự
			for (int i = 0; i < params.size(); i++) {
				// Sử dụng setString cho các tham số chuỗi/LIKE (1 và 2) và setInt cho phân
				// trang (3 và 4)
				Object param = params.get(i);
				if (param instanceof String) {
					preparedStatement.setString(i + 1, (String) param);
				} else if (param instanceof Integer) {
					preparedStatement.setInt(i + 1, (Integer) param);
				} else {
					preparedStatement.setObject(i + 1, param);
				}
			}

			// --- Chạy SQL ---
			rs = preparedStatement.executeQuery();

			// --- Duyệt result set ---
			while (rs.next()) {
				// ... (Phần code lấy dữ liệu Sach giữ nguyên) ...
				String maSach = rs.getString("masach");
				String tenSach = rs.getString("tensach");
				String tacGia = rs.getString("tacgia");
				long soLuong = rs.getLong("soluong");
				long gia = rs.getLong("gia");
				String anh = rs.getString("anh");
				String maLoai = rs.getString("maloai");
				int soTap = rs.getInt("sotap");

				ds.add(new Sach(maSach, tenSach, tacGia, soLuong, gia, anh, maLoai, soTap));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// --- Đóng kết nối ---
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conn != null)
				conn.close();
		}

		return ds;
	}

	public int getTotalPages(String maLoaiParam, String search) throws Exception {

		// Đảm bảo pageSize là 8 theo yêu cầu
		final int pageSize = 8;

		// --- Chuẩn bị Tham số (Làm sạch và kiểm tra NULL/Rỗng) ---
		String finalSearch = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
		String finalMaLoai = (maLoaiParam != null && !maLoaiParam.trim().isEmpty()) ? maLoaiParam.trim() : null;

		// --- Kết nối CSDL ---
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		Connection conn = kn.cn;

		// --- Xây dựng SQL Động và List Tham số ---
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();

		// Truy vấn đếm (COUNT(*))
		sqlBuilder.append("SELECT count(*) FROM sach s JOIN loai l ON s.maloai = l.maloai WHERE 1=1");

		// 1. Lọc theo Tên sách (Search)
		if (finalSearch != null) {
			sqlBuilder.append(" AND tensach LIKE ?");
			params.add("%" + finalSearch + "%");
		}

		// 2. Lọc theo Mã loại
		if (finalMaLoai != null) {
			sqlBuilder.append(" AND s.maloai = ?");
			params.add(finalMaLoai);
		}

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int totalPages = 1;

		try {
			preparedStatement = conn.prepareStatement(sqlBuilder.toString());

			// Thiết lập tham số theo thứ tự
			for (int i = 0; i < params.size(); i++) {
				preparedStatement.setString(i + 1, params.get(i).toString());
			}

			// --- Chạy SQL ---
			rs = preparedStatement.executeQuery();

			// --- Lấy tổng số bản ghi và tính tổng số trang ---
			if (rs.next()) {
				int totalRecords = rs.getInt(1);

				// Tính tổng số trang (chia làm tròn lên)
				if (totalRecords > 0) {
					// Sử dụng Math.ceil để làm tròn lên
					totalPages = (int) Math.ceil((double) totalRecords / pageSize);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// --- Đóng kết nối ---
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conn != null)
				conn.close();
		}

		return totalPages;
	}

	// Hàm thêm sách mới vào CSDL
	/// String maLoai, maSach, tenSach, tacGia; int soLuong, soTap;
	public int themSach(String maSach, String tenSach, int soLuong, long gia, String maLoai, int soTap, String anh,
			String tacGia) throws Exception {

		// Giá trị trả về: 1 nếu thành công, 0 nếu thất bại
		int result = 0;

		// b1: kết nối cơ sở dữ liệu
		// Giả định KetNoi là một lớp bạn tự định nghĩa
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql với tham số (?)
		// Cần liệt kê đúng thứ tự các cột như trong CSDL: masach, tensach, soluong,
		// gia, maloai, sotap, anh, NgayNhap, tacgia
		// Sử dụng GETDATE() cho trường NgayNhap (nếu dùng MS SQL Server)
		String sql = "INSERT INTO sach " + "(masach, tensach, soluong, gia, maloai, sotap, anh, NgayNhap, tacgia) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (Set tham số theo thứ tự dấu ?)
		// Sử dụng phương thức setXxx() phù hợp với kiểu dữ liệu

		// 1. masach (String)
		preparedStatement.setString(1, maSach);

		// 2. tensach (String)
		preparedStatement.setString(2, tenSach);

		// 3. soluong (int)
		preparedStatement.setInt(3, soLuong);

		// 4. gia (long/int) - Sử dụng setLong hoặc setInt tùy thuộc vào cấu trúc CSDL
		preparedStatement.setLong(4, gia);

		// 5. maloai (String)
		preparedStatement.setString(5, maLoai);

		// 6. sotap (int)
		preparedStatement.setInt(6, soTap);

		// 7. anh (String - đường dẫn)
		preparedStatement.setString(7, anh);

		// 8. NgayNhap (GETDATE() - không cần set tham số)

		// 9. tacgia (String)
		preparedStatement.setString(8, tacGia);

		// b4: chạy sql. executeUpdate() trả về số lượng bản ghi bị ảnh hưởng (1 nếu
		// thành công)
		result = preparedStatement.executeUpdate();

		// b5: đóng (các đối tượng đang mở)
		// Đóng PreparedStatement trước
		preparedStatement.close();
		kn.cn.close(); // Đóng kết nối

		return result;
	}

	// Hàm cập nhật thông tin sách
	public int capNhatSach(String maSach, String tenSach, int soLuong, long gia, String maLoai, int soTap, String anh, // Đường
			String tacGia) throws Exception {

		// Giá trị trả về: Số lượng bản ghi được cập nhật (1 nếu thành công, 0 nếu thất
		// bại)
		int result = 0;

		// b1: kết nối cơ sở dữ liệu
		// Giả định KetNoi là một lớp bạn tự định nghĩa
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql UPDATE với tham số (?)
		// Cập nhật tất cả các trường NGOẠI TRỪ masach (khóa chính) và NgayNhap (thường
		// không đổi)
		// Lưu ý: NgayNhap thường không được cập nhật trong thao tác sửa
		String sql = "UPDATE sach SET " + "tensach = ?, " + // 1
				"soluong = ?, " + // 2
				"gia = ?, " + // 3
				"maloai = ?, " + // 4
				"sotap = ?, " + // 5
				"anh = ?, " + // 6
				"tacgia = ? " + // 7
				"WHERE masach = ?"; // 8

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (Set tham số theo thứ tự dấu ?)

		// Các trường cần UPDATE:
		// 1. tensach (String)
		preparedStatement.setString(1, tenSach);

		// 2. soluong (int)
		preparedStatement.setInt(2, soLuong);

		// 3. gia (long/int)
		preparedStatement.setLong(3, gia);

		// 4. maloai (String)
		preparedStatement.setString(4, maLoai);

		// 5. sotap (int)
		preparedStatement.setInt(5, soTap);

		// 6. anh (String - đường dẫn)
		preparedStatement.setString(6, anh);

		// 7. tacgia (String)
		preparedStatement.setString(7, tacGia);

		// Điều kiện WHERE:
		// 8. masach (String) - Dùng để xác định bản ghi cần sửa
		preparedStatement.setString(8, maSach);

		// b4: chạy sql. executeUpdate() trả về số lượng bản ghi bị ảnh hưởng (1 nếu
		// thành công)
		result = preparedStatement.executeUpdate();

		// b5: đóng (các đối tượng đang mở)
		preparedStatement.close();
		kn.cn.close(); // Đóng kết nối

		return result;
	}

	// Hàm xóa sách theo mã sách (maSach)
	public int xoaSach(String maSach) throws Exception {

		// Giá trị trả về: Số lượng bản ghi bị xóa (1 nếu thành công, 0 nếu thất bại)
		int result = 0;

		// b1: kết nối cơ sở dữ liệu
		// Giả định KetNoi là một lớp bạn tự định nghĩa
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql DELETE với tham số (?)
		String sql = "DELETE FROM sach WHERE masach = ?";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql

		// 1. masach (String) - Dùng làm điều kiện xóa
		preparedStatement.setString(1, maSach);

		// b4: chạy sql. executeUpdate() trả về số lượng bản ghi bị ảnh hưởng
		result = preparedStatement.executeUpdate();

		// b5: đóng (các đối tượng đang mở)
		preparedStatement.close();
		kn.cn.close(); // Đóng kết nối

		return result;
	}
}
