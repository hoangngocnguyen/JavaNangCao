package modal.Loai;

import java.util.ArrayList;

public class LoaiBo {
	// Gọi Dao để sai bảo nó.
	
	LoaiDao lDao = new LoaiDao();
	public ArrayList<Loai> getLoai() throws Exception {
		return lDao.getLoai();
	}
}
