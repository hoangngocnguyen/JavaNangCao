package utils;

import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import modal.HoaDon.ThongBaoDatHang;

public class EmailSender {
	// Thay thế bằng email và MẬT KHẨU ỨNG DỤNG của bạn
	private static final String SENDER_EMAIL = "23T1080015@husc.edu.vn";
	private static final String APP_PASSWORD = "fhlcplfgsqmplhqj";

	public static void sendEmail(String recipientEmail, String subject, String content) {
		// Thiết lập các thuộc tính SMTP (Simple mail transfer protocol - giao thức duy
		// nhất chỉ gửi mail)
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // Máy chủ Gmail
		props.put("mail.smtp.port", "587"); // Cổng TLS
		props.put("mail.smtp.auth", "true"); // Bật xác thực
		props.put("mail.smtp.starttls.enable", "true"); // Bật TLS: giao thức bảo mật giao tiếp hiện đại qua mạng

		// 2. Tạo session mail với xác thực
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER_EMAIL, APP_PASSWORD);
			}
		});

		try {
			// 3. Tạo thông điệp
			Message message = new MimeMessage(session);

			// người gửi
			message.setFrom(new InternetAddress(SENDER_EMAIL));

			// người nhận
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

			// tiêu đề
			message.setSubject(subject);

			// Đặt nội dung email (HTML hoặc plain text)
			message.setContent(content, "text/html; charset=UTF-8");

			// 4. Gửi thông điệp
			Transport.send(message);

			System.out.println("Gửi email thành công tới: " + recipientEmail);
		} catch (MessagingException e) {
			throw new RuntimeException("Lỗi gửi email: " + e.getMessage(), e);
		}

	}
	
	// Test
	public static void main(String[] args) {
		String recipient = "nguyenhoang19112005@gmail.com";
		String tieuDe = "Thông báo đặt sách thành công";
		String noiDung = "Vào ngày 19/11/2025, quý khách đã đặt đơn sách mã #abc gồm: a, b, c. Tiếp đến, quý khách vui lòng click vào link sau để tiến"
				+ " hành thanh toán cho đơn hàng \n Tổng cộng: 3.200.000 triệu";
		sendEmail(recipient, tieuDe, noiDung);
	}
}
