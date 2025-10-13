package modal;

import java.util.ArrayList;

public class SachDao {
	public ArrayList<Sach> getSach() throws Exception{
		ArrayList<Sach> ds = new ArrayList<Sach>();
		
		ds.add(new Sach("Ly1", "Lý ánh ánh", "Cindy", 4, 123000, "images/b1.jpg", "ly"));
		ds.add(new Sach("Hoa1", "Hóa đại cương hữu cơ", "Thanh", 3, 50000, "images/b2.jpg", "hoa"));
		ds.add(new Sach("Tin1", "Tin học đại cương", "Lý Tiểu Long", 4, 43000, "images/b3.jpg", "tin"));
		ds.add(new Sach("Toan1", "Giải tích", "Tuấn", 1, 63000, "images/b4.jpg", "toan"));
		ds.add(new Sach("Sinh1", "Giải mã bộ gen", "John Smith", 4, 65000, "images/b5.jpg", "sinh"));
		ds.add(new Sach("Tin2", "Tin học văn phòng", "Lona", 43, 150000, "images/b6.jpg", "tin"));
		ds.add(new Sach("Tin3", "Cấu trúc dữ liệu và giải thuật", "Đạt", 17, 99000, "images/b7.jpg", "tin"));
		ds.add(new Sach("Tin4", "C++ nhập môn", "Hoàng", 21, 43000, "images/b8.jpg", "tin"));
		return ds;
	}
}
