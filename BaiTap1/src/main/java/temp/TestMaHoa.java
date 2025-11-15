package temp;

import utils.MD5Hasher;

public class TestMaHoa {
	public static void main(String[] args) {
		String s = "admin";
		// Băm mật khẩu
		String inputHash = MD5Hasher.hashMD5(s);
		
		System.out.println(inputHash);
		
		if (inputHash.equals(MD5Hasher.hashMD5(s))) {
			System.out.println("Đúng");
		} else {
			System.out.println("sai");
		}
	}
}
