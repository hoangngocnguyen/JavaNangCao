package HoangModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Hoang_SinhVienDao {
	// Lấy tất cả sinh viên
	public ArrayList<Hoang_SinhVien> getAllSinhVien() throws Exception {
		ArrayList<Hoang_SinhVien> lst  = new ArrayList<Hoang_SinhVien>();
		
		// b1. kết nối
		Hoang_KetNoi kn = new Hoang_KetNoi();
		kn.ketnoi();
		
		// 2. tạo lệnh sql
		String sql = "select * from SinhVien";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		
		// 3. truyền tham số
		
		// 4. chạy sql
		ResultSet rs = ps.executeQuery();
		
		// 5. Thực hiện lấy dữ liệu ra
		
		while (rs.next()) {
			String maSV = rs.getString("MaSV");
			String hoTen = rs.getString("HoTen");
			String diaChi = rs.getString("DiaChi");
			String email = rs.getString("Email");
			double dTB = rs.getDouble("DTB");
			String maLop = rs.getString("MaLop");
			
			lst.add(new Hoang_SinhVien(maSV, hoTen, diaChi, email, dTB, maLop));
		}
		
		
		
		rs.close();
		kn.cn.close();
		
		return lst;
	}
	
	// Tìm kiếm tương đối theo họ tên hoặc địa chỉ:
	public ArrayList<Hoang_SinhVien> findSinhVienBySearch(String search) throws Exception {
		ArrayList<Hoang_SinhVien> lst  = new ArrayList<Hoang_SinhVien>();
		
		// b1. kết nối
		Hoang_KetNoi kn = new Hoang_KetNoi();
		kn.ketnoi();
		
		// 2. tạo lệnh sql
		String sql = "select * from SinhVien\r\n"
				+ "where HoTen like ? or DiaChi like ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		
		// 3. truyền tham số
		String pattern = "%" + search + "%";
		ps.setString(1, pattern);
		ps.setString(2, pattern);
		
		// 4. chạy sql
		ResultSet rs = ps.executeQuery();
		
		// 5. Thực hiện lấy dữ liệu ra
		
		while (rs.next()) {
			String maSV = rs.getString("MaSV");
			String hoTen = rs.getString("HoTen");
			String diaChi = rs.getString("DiaChi");
			String email = rs.getString("Email");
			double dTB = rs.getDouble("DTB");
			String maLop = rs.getString("MaLop");
			
			lst.add(new Hoang_SinhVien(maSV, hoTen, diaChi, email, dTB, maLop));
		}
		
		
		
		rs.close();
		kn.cn.close();
		
		return lst;
	}
	
	// 3. Thống kê trung bình cộng của ĐTB theo mỗi lớp
	public ArrayList<Hoang_ThongKeDTB> thongKeDTBTheoLop() throws Exception {
		ArrayList<Hoang_ThongKeDTB> lst = new ArrayList<Hoang_ThongKeDTB>();
		
		// b1. kết nối
		Hoang_KetNoi kn = new Hoang_KetNoi();
		kn.ketnoi();
		
		// 2. tạo lệnh sql
		String sql = "select l.MaLop, l.TenLop , ISNULL(avg(DTB), 0) as TBC "
				+ "from Lop l left join SinhVien s on l.MaLop = s.MaLop "
				+ "group by l.MaLop, TenLop";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		
		// 3. truyền tham số
		
		// 4. chạy sql
		ResultSet rs = ps.executeQuery();
		
		// 5. Thực hiện lấy dữ liệu ra
		
		while (rs.next()) {
			String maLop = rs.getString("MaLop");
			String tenLop = rs.getString("TenLop");
			double tbc = rs.getDouble("TBC");
			
			lst.add(new Hoang_ThongKeDTB(maLop, tenLop, tbc));
		}
		
		
		rs.close();
		kn.cn.close();
		
		System.out.print("Ten" + lst.get(0).getTenLop());
		return lst;
	}
}
