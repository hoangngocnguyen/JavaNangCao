package modal.KhachHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modal.KetNoi;

public class KhachHangDao {
	public KhachHang findKhachHang(String username) throws Exception {
		// b1. kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// 2. tạo lệnh sql
		String sql = "select * from KhachHang k where k.tendn = ? ";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		
		// 3. truyền tham số
		ps.setString(1, username);
		
		// 4. chạy sql
		ResultSet rs = ps.executeQuery();
		
		// 5. Thực hiện lấy dữ liệu ra
		
		if (rs.next() == false) return null;
		
			int makh = rs.getInt("makh");
			String pass = rs.getString("pass");
			String hoten = rs.getString("hoten");
			String diachi = rs.getString("diachi");
			String sodt = rs.getString("sodt");
			String email = rs.getString("email");
			String tendn = rs.getString("tendn");
		
		rs.close();
		kn.cn.close();
		
		return new KhachHang(makh, pass, hoten, diachi, sodt, email, tendn);
	}
	
	public KhachHang ktDangNhap(String username, String password) throws Exception {
		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * \r\n" + "from KhachHang k\r\n" + "where k.tendn = ? and k.pass = ?";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Kiểm tra result set
		// Nếu người dùng không tồn tại
		if (rs.next() == false) {
			return null;
		}

		// Nếu người dùng tồn tại -> Lấy ra
		int makh = rs.getInt("makh");
		String pass = rs.getString("pass");
		String hoten = rs.getString("hoten");
		String diachi = rs.getString("diachi");
		String sodt = rs.getString("sodt");
		String email = rs.getString("email");
		String tendn = rs.getString("tendn");

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		// Trả về khách hàng
		return new KhachHang(makh, pass, hoten, diachi, sodt, email, tendn);
	}

	public boolean ktTenDangNhap(String username) throws Exception {
		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select 1 from KhachHang k where k.tendn = ?";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, username);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Kiểm tra result set
		// Nếu người dùng không tồn tại
		if (rs.next() == false) {
			return false;
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		
		return true;
	}
	public boolean ktEmail(String email) throws Exception {
		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: tạo câu lệnh sql
		String sql = "select 1 from KhachHang k where k.email = ?";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);
		
		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, email);
		
		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();
		
		// b4.2 Kiểm tra result set
		// Nếu email không tồn tại
		if (rs.next() == false) {
			return false;
		}
		
		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();
		
		
		return true;
	}

	public void dangky(String username, String password, String hoten, String email) throws Exception {
		// b1: kết nối cơ sở dữ liệu.
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql.
		String sql = "insert into KhachHang(hoten, tendn, pass, email)\r\n"
				+ "values (?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, hoten);
		preparedStatement.setString(2, username);
		preparedStatement.setString(3, password);
		preparedStatement.setString(4, email);
		

		// b4: chạy sql. rs là con trỏ vào bảng
		int rowAffects = preparedStatement.executeUpdate();

		// b4.2 Kiểm tra result set
		// Nếu người dùng không tồn tại
		if (rowAffects == 0) {
			throw new RuntimeException("Đăng ký không thành công");
		}

		// b5: đóng (các đối tượng đang mở)
		kn.cn.close();

	}
	public void doiMatKhau(int makh, String newPass) throws Exception {
		// b1: kết nối cơ sở dữ liệu.
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: tạo câu lệnh sql.
		String sql = "update KhachHang \r\n"
				+ "set pass = ? \r\n"
				+ "where makh = ? \r\n";
		
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);
		
		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setString(1, newPass);
		preparedStatement.setInt(2, makh);
		
		
		
		// b4: chạy sql. rs là con trỏ vào bảng
		int rowAffects = preparedStatement.executeUpdate();
		
		// b4.2 Kiểm tra result set
		// Nếu người dùng không tồn tại
		if (rowAffects == 0) {
			throw new RuntimeException("Đổi mật khẩu không thành công");
		}
		
		// b5: đóng (các đối tượng đang mở)
		kn.cn.close();
		
	}
}
