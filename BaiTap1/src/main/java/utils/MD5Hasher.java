package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher {

	/**
	 * Chuyển mật khẩu thành chuỗi băm MD5.
	 * 
	 * @param password Mật khẩu gốc.
	 * @return Chuỗi băm MD5 (32 ký tự thập lục phân).
	 */
	public static String hashMD5(String password) {
		try {
			// Khởi tạo đối tượng MessageDigest với thuật toán MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Cập nhật dữ liệu cần băm (chuỗi mật khẩu)
			byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			// Chuyển mảng byte thành chuỗi Hex (thập lục phân)
			StringBuilder sb = new StringBuilder();
			for (byte b : hashInBytes) {
				// Định dạng mỗi byte thành 2 ký tự Hex, sử dụng & 0xff để đảm bảo giá trị dương
				sb.append(String.format("%02x", b));
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// Xử lý nếu thuật toán MD5 không khả dụng
			throw new RuntimeException("Thuật toán MD5 không tồn tại!", e);
		}
	}
}