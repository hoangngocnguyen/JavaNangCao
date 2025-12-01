package modal.XacNhanMuaHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modal.KetNoi;

public class XacNhanMuaHangDao {
	public ArrayList<XacNhanMuaHang> getXacNhanMuaHang(String searchMa, int offset, int pageSize)
			throws SQLException, Exception {
		ArrayList<XacNhanMuaHang> lst = new ArrayList<XacNhanMuaHang>();

		String finalSearch = (searchMa != null && !searchMa.trim().isEmpty()) ? searchMa.trim() : null;

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "";
		PreparedStatement preparedStatement = null;
		int index = 1;

		// b2: tạo câu lệnh sql
		if (finalSearch != null) {
			// Tìm kiếm theo MaHoaDon hoặc HoTen
			sql = "select * from V_XacNhanMuaHang\r\n" + "where MaHoaDon = ? or hoten like ? \r\n"
					+ "order by NgayMua desc\r\n" + "offset ? rows fetch next ? rows only";
			preparedStatement = kn.cn.prepareStatement(sql);

			// b3: truyền tham số vào sql (nếu có)
			preparedStatement.setString(index++, finalSearch);
			preparedStatement.setString(index++, "%" + finalSearch + "%");
			preparedStatement.setInt(index++, offset);
			preparedStatement.setInt(index++, pageSize);

		} else {
			sql = "select * from V_XacNhanMuaHang\r\n" + "order by NgayMua desc\r\n"
					+ "offset ? rows fetch next ? rows only";
			preparedStatement = kn.cn.prepareStatement(sql);

			// b3: truyền tham số vào sql (nếu có)
			preparedStatement.setInt(index++, offset);
			preparedStatement.setInt(index++, pageSize);
		}

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int maHoaDon = rs.getInt("MaHoaDon");
			int makh = rs.getInt("makh");
			String hoTen = rs.getString("hoten");
			int tongSL = rs.getInt("TongSoLuong");
			int tongGia = rs.getInt("TongGia");
			int thanhTien = rs.getInt("ThanhTien");
			boolean damua = rs.getBoolean("damua");
			Date ngayMua = rs.getTimestamp("NgayMua");

			lst.add(new XacNhanMuaHang(maHoaDon, makh, hoTen, tongSL, tongGia, thanhTien, damua, ngayMua));
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return lst;

	}

	public int getTotalPages(String searchMa, int pageSize) throws Exception {
		int totalPages = 1;

		String finalSearch = (searchMa != null && !searchMa.trim().isEmpty()) ? searchMa.trim() : null;

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "";
		PreparedStatement preparedStatement = null;
		int index = 1;

		// b2: tạo câu lệnh sql
		if (finalSearch != null) {
			// Tìm kiếm theo MaHoaDon hoặc HoTen
			sql = "select count(*) as sl from V_XacNhanMuaHang\r\n" + "where MaHoaDon = ? or hoten like ? \r\n";
			preparedStatement = kn.cn.prepareStatement(sql);

			// b3: truyền tham số vào sql (nếu có)
			preparedStatement.setString(index++, finalSearch);
			preparedStatement.setString(index++, "%" + finalSearch + "%");
		} else {
			sql = "select count(*) as sl from V_XacNhanMuaHang\r\n";
			preparedStatement = kn.cn.prepareStatement(sql);
		}

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		if (rs.next()) {
			int totalRecords = rs.getInt("sl");
			totalPages = (int) Math.ceil((double) totalRecords / pageSize);
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return totalPages;
	}

	/*
	 * Xác nhận đã mua cả đơn hàng
	 */
	public boolean xacNhanDaMua(int maHD) throws Exception {
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "update hoadon\r\n" + "set damua = 1\r\n" + "where MaHoaDon = ?";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, maHD);

		// b4: chạy sql. rs là con trỏ vào bảng
		int rowAffect = preparedStatement.executeUpdate();

		// b4.2 Ánh xạ dữ liệu từ result set
		if (rowAffect == 0) {
			// Cập nhật thất bại hay gì đó
			return false;
		}

		// b5: đóng (các đối tượng đang mở)

		kn.cn.close();

		return true;
	}

	/*
	 * Xác nhận đã mua chi tiết đơn hàng
	 */
	public boolean xacNhanDaMuaChiTiet(int maChiTietHD) throws Exception {
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "update V_ChiTietHoaDon\r\n" + "set damua = 1\r\n" + "where MaChiTietHD = ?";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, maChiTietHD);

		// b4: chạy sql. rs là con trỏ vào bảng
		int rowAffect = preparedStatement.executeUpdate();

		// b4.2 Ánh xạ dữ liệu từ result set
		if (rowAffect == 0) {
			// Cập nhật thất bại hay gì đó
			return false;
		}

		// b5: đóng (các đối tượng đang mở)

		kn.cn.close();

		return true;
	}
}
