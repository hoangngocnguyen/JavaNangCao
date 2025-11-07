package modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KhachHangDao {
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
}
