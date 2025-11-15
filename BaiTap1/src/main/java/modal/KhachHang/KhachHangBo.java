package modal.KhachHang;

import utils.MD5Hasher;

public class KhachHangBo {
	KhachHangDao khDao = new KhachHangDao();

	public KhachHang ktDangNhap(String username, String password) throws Exception {
		// Mã hóa mật khẩu để tìm
		password = MD5Hasher.hashMD5(password);
		return khDao.ktDangNhap(username, password);
	}

	public boolean ktTenDangNhap(String username) throws Exception {
		return khDao.ktTenDangNhap(username);
	}
	
	public boolean ktEmail(String email) throws Exception {
		return khDao.ktEmail(email);
	}
	
//	public boolean ktDangKy(String username, String password, String confirmPassword, String email) throws Exception {
//		if (khDao.ktTenDangNhap(username)) {
//			throw new RuntimeException("Tên đăng nhập đã tồn tại");
//		}
//		if (khDao.ktEmail(email)) {
//			throw new RuntimeException("Email đã tồn tại");
//		}
//		
//		if (!password.equals(confirmPassword)) {
//			throw new RuntimeException("Nhập lại mật khẩu không đúng");
//		}
//		
//		return true;
//	}

	public void dangky(String username, String password, String hoten, String email) throws Exception {
		// Mã hóa mật khẩu
		password = MD5Hasher.hashMD5(password);
		khDao.dangky(username, password, hoten, email);
	}
}
