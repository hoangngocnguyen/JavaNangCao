package utils;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
	private static final String CHARACTERS = 
	        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +   // Chữ hoa
	        "abcdefghijklmnopqrstuvwxyz" +   // Chữ thường
	        "0123456789";
	private static final int PASSWORD_LENGTH = 6;
	
	/**
	 * Tạo mật khẩu ngẫu nhiên với độ dài là 6
	 */
	
	public static String generateRandomPassword() {
		Random random = new SecureRandom();
		
		StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
		
		for (int i = 0; i< PASSWORD_LENGTH; i++) {
			// Lấy chỉ mục ngẫu nhiên trong CHARACTERS
			int randomIndex = random.nextInt(CHARACTERS.length());
			
			sb.append(CHARACTERS.charAt(randomIndex));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(generateRandomPassword());
	}
}
