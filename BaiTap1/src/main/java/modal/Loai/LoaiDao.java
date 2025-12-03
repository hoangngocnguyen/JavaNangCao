package modal.Loai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modal.KetNoi;

public class LoaiDao {
	public ArrayList<Loai> getLoai() throws Exception {
		ArrayList<Loai> ds = new ArrayList<Loai>();
		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from loai";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Duyệt result set, đưa vào List
		while (rs.next()) {
			String maLoai = rs.getString("maloai");
			String tenLoai = rs.getString("tenloai");

			ds.add(new Loai(maLoai, tenLoai));
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return ds;
	}

	// Hàm lấy danh sách loại sách có phân trang và tìm kiếm
	public ArrayList<Loai> getLoaiPhanTrang(String search, int offset, int pageSize) throws Exception {

		ArrayList<Loai> ds = new ArrayList<>();

		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		// Sử dụng OFFSET FETCH để phân trang.
		// Điều kiện tìm kiếm (WHERE) áp dụng cho cả maloai và tenloai.
		String sql = "SELECT * FROM loai " + "WHERE maloai LIKE ? OR tenloai LIKE ? " + "ORDER BY maloai " + // Bắt buộc
				"OFFSET ? ROWS " + "FETCH NEXT ? ROWS ONLY";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql
		String searchParam = "%" + search + "%";

		// 1. maloai LIKE ?
		preparedStatement.setString(1, searchParam);

		// 2. tenloai LIKE ?
		preparedStatement.setString(2, searchParam);

		// 3. OFFSET ? ROWS
		preparedStatement.setInt(3, offset);

		// 4. FETCH NEXT ? ROWS ONLY
		preparedStatement.setInt(4, pageSize);

		// b4: chạy sql và duyệt kết quả
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			String maLoai = rs.getString("maloai");
			String tenLoai = rs.getString("tenloai");

			// Khởi tạo đối tượng Loai và thêm vào danh sách
			ds.add(new Loai(maLoai, tenLoai));
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		preparedStatement.close();
		kn.cn.close();

		return ds;
	}

	// Hàm tính tổng số trang
	public int getTotalPages(String search, int pageSize) throws Exception {

		int totalRecords = 0;
		int totalPages = 0;

		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		// Đếm tổng số bản ghi khớp với điều kiện tìm kiếm
		String sql = "SELECT COUNT(*) AS TotalRecords FROM loai " + "WHERE maloai LIKE ? OR tenloai LIKE ?";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql
		String searchParam = "%" + search + "%";

		// 1. maloai LIKE ?
		preparedStatement.setString(1, searchParam);

		// 2. tenloai LIKE ?
		preparedStatement.setString(2, searchParam);

		// b4: chạy sql và lấy kết quả
		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			totalRecords = rs.getInt("TotalRecords");
		}

		// Tính tổng số trang: (tổng số bản ghi + pageSize - 1) / pageSize
		// Cách này đảm bảo làm tròn lên (ceil)
		totalPages = (int) Math.ceil((double) totalRecords / pageSize);

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		preparedStatement.close();
		kn.cn.close();

		return totalPages;
	}

	// Hàm thêm loại sách mới vào CSDL
	public int themLoai(String maLoai, String tenLoai) throws Exception {

		int result = 0;
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// Câu lệnh INSERT
		String sql = "INSERT INTO loai (maloai, tenloai) VALUES (?, ?)";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// Truyền tham số
		preparedStatement.setString(1, maLoai);
		preparedStatement.setString(2, tenLoai);

		// Chạy SQL
		result = preparedStatement.executeUpdate();

		// Đóng kết nối
		preparedStatement.close();
		kn.cn.close();

		return result;
	}

	// Hàm cập nhật thông tin loại sách
	public int suaLoai(String maLoai, String tenLoai) throws Exception {

		int result = 0;
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// Câu lệnh UPDATE: Cập nhật tenloai dựa trên maloai
		String sql = "UPDATE loai SET tenloai = ? WHERE maloai = ?";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// Truyền tham số
		// 1. tenloai (giá trị mới)
		preparedStatement.setString(1, tenLoai);

		// 2. maloai (điều kiện WHERE)
		preparedStatement.setString(2, maLoai);

		// Chạy SQL
		result = preparedStatement.executeUpdate();

		// Đóng kết nối
		preparedStatement.close();
		kn.cn.close();

		return result;
	}

	// Hàm xóa loại sách theo mã loại
	public int xoaLoai(String maLoai) throws Exception {

		int result = 0;
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// Câu lệnh DELETE
		String sql = "DELETE FROM loai WHERE maloai = ?";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// Truyền tham số
		preparedStatement.setString(1, maLoai);

		// Chạy SQL
		result = preparedStatement.executeUpdate();

		// Đóng kết nối
		preparedStatement.close();
		kn.cn.close();

		return result;
	}

	// Hàm tìm kiếm loại sách theo mã loại
	public Loai timLoaiTuMa(String maLoaiCanTim) throws Exception {

		Loai loai = null;
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// Câu lệnh SELECT
		String sql = "SELECT * FROM loai WHERE maloai = ?";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// Truyền tham số
		preparedStatement.setString(1, maLoaiCanTim);

		// Chạy SQL
		ResultSet rs = preparedStatement.executeQuery();

		// Nếu tìm thấy một bản ghi
		if (rs.next()) {
			String maLoai = rs.getString("maloai");
			String tenLoai = rs.getString("tenloai");

			// Tạo đối tượng Loai
			loai = new Loai(maLoai, tenLoai);
		}

		// Đóng kết nối
		rs.close();
		preparedStatement.close();
		kn.cn.close();

		return loai;
	}
}
