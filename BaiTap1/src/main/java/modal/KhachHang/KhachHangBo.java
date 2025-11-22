package modal.KhachHang;

import utils.EmailSender;
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
	
	public void dangky(String username, String password, String hoten, String email) throws Exception {
		// Mã hóa mật khẩu
		password = MD5Hasher.hashMD5(password);
		khDao.dangky(username, password, hoten, email);
	}
	
	/**
     * Tạo nội dung email HTML để thông báo mật khẩu mới cho người dùng.
     *
     * @param newPassword Mật khẩu mới được tạo ngẫu nhiên.
     * @return Nội dung email dưới dạng chuỗi HTML.
     */
    public static String taoNoiDungEmailMatKhau(String newPassword) {
        
        // Sử dụng HTML cơ bản và CSS nội tuyến để đảm bảo khả năng tương thích cao
        String emailContent = "<!DOCTYPE html>"
                + "<html lang='vi'>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "    <title>Khôi phục Mật khẩu</title>"
                + "</head>"
                + "<body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333; background-color: #f4f4f4; padding: 20px;'>"
                
                + "    <div style='max-width: 600px; margin: 0 auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); border-top: 4px solid #007bff;'>"
                
                + "        <h2 style='color: #007bff; border-bottom: 1px solid #eeeeee; padding-bottom: 10px;'>🔑 Khôi phục Mật khẩu Thành công</h2>"
                
                + "        <p>Xin chào,</p>"
                
                + "        <p>Chúng tôi đã nhận được yêu cầu khôi phục mật khẩu cho tài khoản của bạn. Mật khẩu mới của bạn là:</p>"
                
                // Vùng hiển thị mật khẩu mới (Màu cam để làm nổi bật)
                + "        <div style='text-align: center; margin: 20px 0;'>"
                + "            <span style='display: inline-block; background-color: #ff9800; color: #ffffff; font-size: 24px; font-weight: bold; padding: 10px 20px; border-radius: 5px; letter-spacing: 1.5px;'>"
                + newPassword
                + "            </span>"
                + "        </div>"
                
                + "        <p style='color: #d9534f; font-weight: bold;'>⚠️ Quan trọng:</p>"
                + "        <p>Vì lý do bảo mật, chúng tôi khuyến nghị bạn nên **thay đổi mật khẩu** này ngay sau khi đăng nhập thành công. Vui lòng truy cập trang Cài đặt Tài khoản để đổi mật khẩu mới.</p>"
                
                // Nút Đăng nhập (Màu xanh dương)
                + "        <div style='text-align: center; margin-top: 30px;'>"
                + "            <a href='[ĐƯỜNG DẪN ĐĂNG NHẬP CỦA BẠN]' target='_blank' style='display: inline-block; padding: 10px 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px; font-weight: bold;'>"
                + "Đăng nhập ngay"
                + "            </a>"
                + "        </div>"

                + "        <p style='margin-top: 20px; font-size: 12px; color: #777;'>Đây là email tự động. Vui lòng không trả lời email này.</p>"
                
                + "    </div>"
                + "</body>"
                + "</html>";

        return emailContent;
    }
    
    public void guiEmailMatKhauMoi(KhachHang kh, String password) {
    	// 
    	String subject = "Mật khẩu mới dành cho tài khoản của bạn";
		String content = taoNoiDungEmailMatKhau(password);
		
		EmailSender.sendEmail(kh.getEmail(), subject, content);
    }
}
