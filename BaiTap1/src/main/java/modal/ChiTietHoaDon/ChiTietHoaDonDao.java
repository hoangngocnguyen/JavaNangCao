package modal.ChiTietHoaDon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modal.KetNoi;

public class ChiTietHoaDonDao {
	public ArrayList<ChiTietHoaDon> getChiTietHoaDon(int maHD, int offset, int pageSize)
			throws SQLException, Exception {
		ArrayList<ChiTietHoaDon> lst = new ArrayList<ChiTietHoaDon>();

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from V_ChiTietHoaDon\r\n" + "where MaHoaDon = ?\r\n" + "order by MaChiTietHD desc\r\n"
				+ "offset ? rows fetch next ? rows only";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, maHD);
		preparedStatement.setInt(2, offset);
		preparedStatement.setInt(3, pageSize);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int maHoaDon = rs.getInt("MaHoaDon");
			int maChiTietHD = rs.getInt("MaChiTietHD");
			int makh = rs.getInt("makh");
			String hoTen = rs.getString("hoten");
			String tenSach = rs.getString("tensach");
			int soLuongMua = rs.getInt("SoLuongMua");
			int gia = rs.getInt("gia");
			int thanhTien = rs.getInt("ThanhTien");
			boolean damua = rs.getBoolean("damua");
			
			lst.add(new ChiTietHoaDon(maHoaDon, maChiTietHD, makh, hoTen, tenSach, soLuongMua, gia, thanhTien, damua));
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return lst;

	}

	public int getTotalPages(int maHD, int pageSize) throws Exception {
		int totalPages = 0;
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select count(*) as sl from V_ChiTietHoaDon\r\n" + "where MaHD = ?\r\n";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, maHD);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int totalRecords = rs.getInt("sl");
			totalPages = (int) Math.ceil((double) totalRecords / pageSize);
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return totalPages;

	}

}
