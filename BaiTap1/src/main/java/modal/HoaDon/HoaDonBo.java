package modal.HoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import modal.GioHang.GioHang;
import modal.KhachHang.KhachHang;
import utils.EmailSender;

public class HoaDonBo {
	HoaDonDao hoaDonDao = new HoaDonDao();

	public Integer taoHoaDonVaChiTiet(int makh, ArrayList<GioHang> dsGioHang) throws Exception {
		return hoaDonDao.taoHoaDonVaChiTiet(makh, dsGioHang);
	}

	// Hàm tạo nội dung email
	private String createEmailContent(ThongBaoDatHang tb) {

		// Định dạng ngày tháng
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String ngayMuaFormatted = sdf.format(tb.getNgayMua());

		// Lấy chuỗi HTML ở trên
		String htmlContent = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"vi\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Xác nhận Đơn hàng & Yêu cầu Thanh toán</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            margin: 0;\r\n"
				+ "            padding: 0;\r\n"
				+ "            background-color: #f4f4f4;\r\n"
				+ "            color: #333333;\r\n"
				+ "        }\r\n"
				+ "        .container {\r\n"
				+ "            width: 100%;\r\n"
				+ "            max-width: 600px;\r\n"
				+ "            margin: 0 auto;\r\n"
				+ "            background-color: #ffffff;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            border-radius: 8px;\r\n"
				+ "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);\r\n"
				+ "        }\r\n"
				+ "        .header {\r\n"
				+ "            background-color: #007bff;\r\n"
				+ "            color: #ffffff;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            text-align: center;\r\n"
				+ "            border-radius: 6px 6px 0 0;\r\n"
				+ "        }\r\n"
				+ "        .content {\r\n"
				+ "            padding: 20px 0;\r\n"
				+ "            line-height: 1.6;\r\n"
				+ "        }\r\n"
				+ "        .details-table {\r\n"
				+ "            width: 100%;\r\n"
				+ "            border-collapse: collapse;\r\n"
				+ "            margin: 20px 0;\r\n"
				+ "            border: 1px solid #dddddd;\r\n"
				+ "        }\r\n"
				+ "        .details-table th, .details-table td {\r\n"
				+ "            border: 1px solid #dddddd;\r\n"
				+ "            padding: 12px;\r\n"
				+ "            text-align: left;\r\n"
				+ "        }\r\n"
				+ "        .details-table th {\r\n"
				+ "            background-color: #f8f8f8;\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            width: 40%;\r\n"
				+ "        }\r\n"
				+ "        .payment-section {\r\n"
				+ "            text-align: center;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            background-color: #e9f7ef;\r\n"
				+ "            border: 1px solid #c8e6c9;\r\n"
				+ "            border-radius: 4px;\r\n"
				+ "        }\r\n"
				+ "        .button {\r\n"
				+ "            display: inline-block;\r\n"
				+ "            background-color: #28a745;\r\n"
				+ "            color: #ffffff;\r\n"
				+ "            padding: 10px 20px;\r\n"
				+ "            text-decoration: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            margin-top: 15px;\r\n"
				+ "        }\r\n"
				+ "        .footer {\r\n"
				+ "            text-align: center;\r\n"
				+ "            padding-top: 20px;\r\n"
				+ "            font-size: 12px;\r\n"
				+ "            color: #999999;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "        <div class=\"header\">\r\n"
				+ "            <h2>THÔNG BÁO THANH TOÁN ĐƠN HÀNG</h2>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"content\">\r\n"
				+ "            <p>Kính gửi ${hoten},</p>\r\n"
				+ "            <p>Chúng tôi xin trân trọng thông báo đơn hàng của quý khách đã được đặt thành công. Vui lòng hoàn tất thanh toán để chúng tôi tiến hành xử lý và giao hàng.</p>\r\n"
				+ "\r\n"
				+ "            <h3>Chi tiết Hóa đơn</h3>\r\n"
				+ "            <table class=\"details-table\">\r\n"
				+ "                <tr>\r\n"
				+ "                    <th>Mã Hóa đơn:</th>\r\n"
				+ "                    <td>${maHoaDon}</td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                    <th>Ngày Mua:</th>\r\n"
				+ "                    <td>${ngayMua}</td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                    <th>Tổng Tiền Cần Thanh toán:</th>\r\n"
				+ "                    <td>${tongTien} VND</td>\r\n"
				+ "                </tr>\r\n"
				+ "                </table>\r\n"
				+ "\r\n"
				+ "            <div class=\"payment-section\">\r\n"
				+ "                <h3>Yêu cầu Thanh toán</h3>\r\n"
				+ "                <p>Để hoàn tất giao dịch, quý khách vui lòng thanh toán số tiền ${tongTien} VND.</p>\r\n"
				+ "                <a href=\"#LINK_THANH_TOAN#\" class=\"button\">THANH TOÁN NGAY</a>\r\n"
				+ "                <p style=\"margin-top: 15px; font-size: 14px;\">(Liên kết này sẽ dẫn bạn đến cổng thanh toán an toàn của chúng tôi.)</p>\r\n"
				+ "            </div>\r\n"
				+ "\r\n"
				+ "            <p style=\"margin-top: 30px;\">Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với bộ phận hỗ trợ của chúng tôi.</p>\r\n"
				+ "            <p>Trân trọng,</p>\r\n"
				+ "            <p>Đội ngũ Hỗ trợ Khách hàng.</p>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"footer\">\r\n"
				+ "            Đây là email tự động, vui lòng không trả lời.\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>";// Dán toàn bộ nội dung HTML vào đây

		// Thực hiện thay thế các biến placeholders
		htmlContent = htmlContent.replace("${hoten}", tb.getHoten());
		htmlContent = htmlContent.replace("${maHoaDon}", String.valueOf(tb.getMaHoaDon()));
		htmlContent = htmlContent.replace("${ngayMua}", ngayMuaFormatted);
		htmlContent = htmlContent.replace("${tongTien}", String.format("%,d", tb.getTongTien())); // Định dạng tiền tệ
		// htmlContent = htmlContent.replace("#LINK_THANH_TOAN#",
		// "https://yourwebsite.com/pay?id=" + maHoaDon);

		return htmlContent;
	}

	public void guiEmailThongBaoDatHangThanhCong(KhachHang kh, Integer maHoaDon) throws Exception {
		// Lấy tên người dùng ra để chào hỏi

		// Lấy được danh sách chi tiết hóa đơn
		ThongBaoDatHang tb = hoaDonDao.getThongBaoDatHang(maHoaDon);
		
		String subject = "Xác nhận Đơn hàng #" + maHoaDon + " và Yêu cầu Thanh toán";
		String content = createEmailContent(tb);
		
		// Gửi email
		EmailSender.sendEmail(kh.getEmail(), subject, content);
	}
}
