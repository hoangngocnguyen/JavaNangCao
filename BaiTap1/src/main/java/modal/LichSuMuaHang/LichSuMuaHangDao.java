package modal.LichSuMuaHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modal.KetNoi;

public class LichSuMuaHangDao {
	public ArrayList<LichSuMuaHang> getLichSuMuaHang(int ma, int offset, int pageSize) throws SQLException, Exception {
		ArrayList<LichSuMuaHang> lst = new ArrayList<LichSuMuaHang>();

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from V_LichSuMuaHang\r\n"
				+ "where makh = ?\r\n"
				+ "order by NgayMua desc\r\n"
				+ "offset ? rows fetch next ? rows only";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, ma);
		preparedStatement.setInt(2, offset);
		preparedStatement.setInt(3, pageSize);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int makh = rs.getInt("makh");
			String tensach = rs.getString("tensach");
			int gia = rs.getInt("gia");
			int soLuongMua = rs.getInt("SoLuongMua");
			int thanhTien = rs.getInt("ThanhTien");
			boolean damua = rs.getBoolean("damua");
			Date ngayMua = rs.getTimestamp("NgayMua");
			int maHoaDon = rs.getInt("MaHoaDon");
			int maChiTietHoaDon = rs.getInt("MaChiTietHD");

			lst.add(new LichSuMuaHang(makh, tensach, gia, soLuongMua, thanhTien, damua, ngayMua, maHoaDon, maChiTietHoaDon));
		}
		
		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return lst;

	}
	
	public int getTotalPages(int ma, int pageSize) throws Exception {
		int totalPages = 0;
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select count(*) as sl from V_LichSuMuaHang\r\n"
				+ "where makh = ?\r\n";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, ma);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int totalRecords = rs.getInt("sl");
			totalPages =  (int) Math.ceil((double) totalRecords / pageSize);
		}
		
//		for (LichSuMuaHang ls: lst) {
//			System.out.println("sl" + ls.getSoLuongMua() + "tt"+ ls.getThanhTien());
//		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return totalPages;

	}
	
	
	/*
	 * Lấy thông tin cho trang thanh toán
	 */
	public LichSuMuaHang getChiTietHoaDon(int ma, int maChiTietHoaDon) throws SQLException, Exception {
		LichSuMuaHang chiTietHD = null;

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from V_LichSuMuaHang\r\n"
				+ "where makh = ? AND MaChiTietHD = ?";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, ma);
		preparedStatement.setInt(2, maChiTietHoaDon);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int makh = rs.getInt("makh");
			String tensach = rs.getString("tensach");
			int gia = rs.getInt("gia");
			int soLuongMua = rs.getInt("SoLuongMua");
			int thanhTien = rs.getInt("ThanhTien");
			boolean damua = rs.getBoolean("damua");
			Date ngayMua = rs.getTimestamp("NgayMua");
			int maHoaDon = rs.getInt("MaHoaDon");
			int maChiTietHD = rs.getInt("MaChiTietHD");

			chiTietHD = new LichSuMuaHang(makh, tensach, gia, soLuongMua, thanhTien, damua, ngayMua, maHoaDon, maChiTietHD);
		}
		
		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return chiTietHD;
	}
	
	
	/*
	 * ADMIN
	 * */
	// Lấy lịch sử mua hàng để admin xác nhận
	public ArrayList<LichSuMuaHang> getLichSuMuaHang_Admin(String searchMa, int offset, int pageSize) throws SQLException, Exception {
		ArrayList<LichSuMuaHang> lst = new ArrayList<LichSuMuaHang>();
		
		String finalSearch = (searchMa != null && !searchMa.trim().isEmpty()) ? searchMa.trim() : null;
		
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		String sql = "";
		PreparedStatement preparedStatement = null;
		// b2: tạo câu lệnh sql
		if (finalSearch != null) {
			sql = "select * from V_LichSuMuaHang\r\n"
					+ "where MaChiTietHD = ? \r\n"
					+ "order by damua, NgayMua desc\r\n"
					+ "offset ? rows fetch next ? rows only";
			preparedStatement = kn.cn.prepareStatement(sql);
			preparedStatement.setString(1, searchMa);
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, pageSize);
		} else {
			sql = "select * from V_LichSuMuaHang\r\n"
					+ "order by damua, NgayMua desc\r\n"
					+ "offset ? rows fetch next ? rows only";
			preparedStatement = kn.cn.prepareStatement(sql);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, pageSize);
		}
		// b3: truyền tham số vào sql (nếu có)

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int makh = rs.getInt("makh");
			String tensach = rs.getString("tensach");
			int gia = rs.getInt("gia");
			int soLuongMua = rs.getInt("SoLuongMua");
			int thanhTien = rs.getInt("ThanhTien");
			boolean damua = rs.getBoolean("damua");
			Date ngayMua = rs.getTimestamp("NgayMua");
			int maHoaDon = rs.getInt("MaHoaDon");
			int maChiTietHoaDon = rs.getInt("MaChiTietHD");

			lst.add(new LichSuMuaHang(makh, tensach, gia, soLuongMua, thanhTien, damua, ngayMua, maHoaDon, maChiTietHoaDon));
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return lst;

	}
	public int getTotalPages_Admin(String searchMa, int pageSize) throws Exception {
		int totalPages = 1;
		
		String finalSearch = (searchMa != null && !searchMa.trim().isEmpty()) ? searchMa.trim() : null;
		
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		String sql = "";
		PreparedStatement preparedStatement = null;
		// b2: tạo câu lệnh sql
		
		if (finalSearch != null) {
			sql = "select count(*) as sl from V_LichSuMuaHang\r\n"
					+ "where MaChiTietHD = ? \r\n";
			preparedStatement = kn.cn.prepareStatement(sql);
			
			// b3: truyền tham số vào sql (nếu có)
			preparedStatement.setString(1, searchMa);
		} else {
			sql = "select count(*) as sl from V_LichSuMuaHang\r\n";
			preparedStatement = kn.cn.prepareStatement(sql);
		}
		
		
		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int totalRecords = rs.getInt("sl");
			totalPages =  (int) Math.ceil((double) totalRecords / pageSize);
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return totalPages;

	}
	
	
	/*
	 * Lấy lịch sử đơn hàng theo tháng hiện tại phục vụ thống kê
	 */
	public ArrayList<LichSuMuaHang> getLichSuMuaHangThangHienTai(int makh) throws SQLException, Exception {
		ArrayList<LichSuMuaHang> lst = new ArrayList<LichSuMuaHang>();

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * \r\n"
				+ "from V_LichSuMuaHang\r\n"
				+ "where makh = ? and (month(NgayMua) = month(getdate())) and (year(NgayMua) = year(getdate())) "
				+ "and damua=1 "
				+ "order by NgayMua desc";
				
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, makh);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int ma = rs.getInt("makh");
			String tensach = rs.getString("tensach");
			int gia = rs.getInt("gia");
			int soLuongMua = rs.getInt("SoLuongMua");
			int thanhTien = rs.getInt("ThanhTien");
			boolean damua = rs.getBoolean("damua");
			Date ngayMua = rs.getTimestamp("NgayMua");
			int maHoaDon = rs.getInt("MaHoaDon");
			int maChiTietHoaDon = rs.getInt("MaChiTietHD");

			lst.add(new LichSuMuaHang(ma, tensach, gia, soLuongMua, thanhTien, damua, ngayMua, maHoaDon, maChiTietHoaDon));
		}
		
		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return lst;

	}
}
