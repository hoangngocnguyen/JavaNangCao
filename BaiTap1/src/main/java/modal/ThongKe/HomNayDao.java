package modal.ThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modal.KetNoi;
import modal.XacNhanMuaHang.XacNhanMuaHang;

public class HomNayDao {
	public ArrayList<XacNhanMuaHang> getHoaDonHomNay(int offset, int pageSize) throws SQLException, Exception {
		ArrayList<XacNhanMuaHang> lst = new ArrayList<XacNhanMuaHang>();
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from V_HoaDonHomNay\r\n" + "order by NgayMua desc\r\n" + "offset ? rows fetch next ? rows only";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, offset);
		preparedStatement.setInt(2, pageSize);

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

	public int getTotalPages(int pageSize) throws Exception {
		int totalPages = 1;

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "";
		PreparedStatement preparedStatement = null;
		int index = 1;

		// b2: tạo câu lệnh sql
		sql = "select count(*) as sl from V_HoaDonHomNay\r\n";
		preparedStatement = kn.cn.prepareStatement(sql);

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
	
	// Đếm tổng số hóa đơn:
	public int getTotalElements() throws Exception {
		int totalRecords = 0;

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "";
		PreparedStatement preparedStatement = null;
		int index = 1;

		// b2: tạo câu lệnh sql
		sql = "select count(*) as sl from V_HoaDonHomNay\r\n";
		preparedStatement = kn.cn.prepareStatement(sql);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		if (rs.next()) {
			totalRecords = rs.getInt("sl");
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return totalRecords;
	}
	
	// Tính tổng tiền thu nhập được trong ngày hôm nay
	public int getTongThuNhapHomNay() throws Exception {
		int totalAmount = 0;
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "";
		PreparedStatement preparedStatement = null;
		int index = 1;

		// b2: tạo câu lệnh sql
		sql = "SELECT   isnull(SUM(dbo.ChiTietHoaDon.SoLuongMua * dbo.sach.gia), 0) AS TongThuNhap\r\n"
				+ "FROM     dbo.ChiTietHoaDon INNER JOIN\r\n"
				+ "             dbo.hoadon ON dbo.ChiTietHoaDon.MaHoaDon = dbo.hoadon.MaHoaDon INNER JOIN\r\n"
				+ "             dbo.sach ON dbo.ChiTietHoaDon.MaSach = dbo.sach.masach\r\n"
				+ "where cast(NgayMua as date) = cast(getdate() as date) and (dbo.ChiTietHoaDon.damua = 1)";
		preparedStatement = kn.cn.prepareStatement(sql);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		if (rs.next()) {
			totalAmount = rs.getInt("TongThuNhap");
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return totalAmount;
	}
}
