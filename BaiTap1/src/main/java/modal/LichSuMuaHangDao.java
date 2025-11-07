package modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

			lst.add(new LichSuMuaHang(makh, tensach, gia, soLuongMua, thanhTien, damua, ngayMua, maHoaDon));
		}
		
//		for (LichSuMuaHang ls: lst) {
//			System.out.println("sl" + ls.getSoLuongMua() + "tt"+ ls.getThanhTien());
//		}

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
}
