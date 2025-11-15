package modal.ThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modal.KetNoi;

public class ThongKeDao {
	public ArrayList<ThongKeKhoSach> getThongKeKhoSach(int offset, int pageSize) throws Exception {
		ArrayList<ThongKeKhoSach> lst = new ArrayList<ThongKeKhoSach>();

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from V_ThongKeKhoSach\r\n"
				+ "order by tenloai\r\n"
				+ "offset ? rows fetch next ? rows only";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, offset);
		preparedStatement.setInt(2, pageSize);

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
	
	public int getTotalPages_ThongKeKhoSach(int pageSize) throws Exception {
		int totalPages = 0;
		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select count(*) as sl from V_ThongKeKhoSach";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)

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
}
