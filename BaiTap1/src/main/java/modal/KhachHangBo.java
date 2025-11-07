package modal;

public class KhachHangBo {
	KhachHangDao khDao = new KhachHangDao();
	public KhachHang ktDangNhap(String username, String password) throws Exception {
		return khDao.ktDangNhap(username, password);
	}
}
