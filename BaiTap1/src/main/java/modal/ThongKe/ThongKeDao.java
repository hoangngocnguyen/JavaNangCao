package modal.ThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modal.KetNoi;

public class ThongKeDao {
	public ArrayList<ThongKeKhoSach> getThongKeKhoSach(String searchTenLoai, int offset, int pageSize) throws Exception {
		ArrayList<ThongKeKhoSach> lst = new ArrayList<ThongKeKhoSach>();

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		System.out.println("search:_" + searchTenLoai);
		// b2: tạo câu lệnh sql
		String sql = "select * from V_ThongKeKhoSach\r\n"
				+ "where tenloai like ? \r\n"
				+ "order by SoSach\r\n"
				+ "offset ? rows fetch next ? rows only";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, searchTenLoai);
		preparedStatement.setInt(2, offset);
		preparedStatement.setInt(3, pageSize);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			String tenLoai = rs.getString("tenloai");
			int soSach = rs.getInt("SoSach");
			int tongSL = rs.getInt("TongSL");
			double tbcGia = rs.getDouble("TbcGia");
			
			lst.add(new ThongKeKhoSach(tenLoai, soSach, tongSL, tbcGia));
		}
		
		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();
		System.out.println("Tổng số dòng thống kê đã load: " + lst.size());
		return lst;

	}
	
	public int getTotalPages_ThongKeKhoSach(String searchTenLoai, int pageSize) throws Exception {
		int totalPages = 0;
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select count(*) as sl from V_ThongKeKhoSach \r\n"
				+ "where tenloai like ? \r\n";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, searchTenLoai);
		
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
	
	public ThongKeTongQuan getThongKeTongQuan() throws Exception {
		ThongKeTongQuan tkTongQuan = null;
		
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from V_ThongKeTongQuanSach";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int tongSoLoai = rs.getInt("TongSoLoai");
			int tongSoSach = rs.getInt("TongSoSach");
			int tongTonKho = rs.getInt("TongTonKho");
			tkTongQuan = new ThongKeTongQuan(tongSoLoai, tongSoSach, tongTonKho);
		}
		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return tkTongQuan;
	}
	
	
	/*
	 * Lấy ra top 5 khách hàng chi tiêu nhiều nhất tháng nay
	 */
	public List<TopKhachHang> getTopKhachHang(int top) throws Exception {
		List<TopKhachHang> lst = new ArrayList<TopKhachHang>();
		
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "SELECT kh.*, isnull(SUM(cthd.SoLuongMua * s.gia), 0) AS TongChiTieu, MONTH(hd.NgayMua) AS Thang "
		        + "FROM     dbo.ChiTietHoaDon cthd INNER JOIN "
		        + "             hoadon hd ON cthd.MaHoaDon = hd.MaHoaDon INNER JOIN "
		        + "             dbo.sach s ON cthd.MaSach = s.masach "
		        + "			 JOIN KhachHang kh on kh.makh = hd.makh "
		        + "where  cthd.damua = 1 and (month(hd.NgayMua) = month(getdate())) and (year(hd.NgayMua) = year(getdate())) "
		        + "group by kh.makh, kh.pass, kh.hoten, kh.diachi, kh.sodt, kh.email, kh.tendn, MONTH(hd.NgayMua)\r\n"
		        
		        // THAY THẾ 'ORDER BY ...' bằng cú pháp OFFSET/FETCH
		        + "order by TongChiTieu desc " // Sắp xếp theo TongChiTieu (đã được định nghĩa)
		        + "OFFSET 0 ROWS "               // Bỏ qua 0 hàng (Bắt đầu từ đầu)
		        + "FETCH NEXT ? ROWS ONLY";      // Lấy N hàng tiếp theo (sử dụng tham số ?)
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, top);
		
		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Ánh xạ dữ liệu từ result set
		while (rs.next()) {
			int maKH = rs.getInt("makh");
			String hoTen = rs.getString("hoten");
			String diaChi = rs.getString("diachi");
			String sodt = rs.getString("sodt");
			String email = rs.getString("email");
			long tongChiTieu = rs.getLong("TongChiTieu");
			int thang = rs.getInt("Thang");
			
			lst.add(new TopKhachHang(maKH, hoTen, diaChi, sodt, email, tongChiTieu, thang));
		}
		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		return lst;
	}
}
