package modal;

import java.util.ArrayList;

public class LoaiDao {
	public ArrayList<Loai> getLoai() throws Exception {
		ArrayList<Loai> ds = new ArrayList<Loai>();
		ds.add(new Loai("tin", "Công nghệ thông tin"));
		ds.add(new Loai("toan", "Toán ứng dụng"));
		ds.add(new Loai("ly", "Lý đại cương"));
		ds.add(new Loai("hoa", "Hóa hữu cơ"));
		ds.add(new Loai("sinh", "Sinh học phân tử"));
		
		return ds;
	}
}
