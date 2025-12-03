package modal.Loai;

import java.util.ArrayList;

public class LoaiBo {
	// Gọi Dao để sai bảo nó.
	
	LoaiDao lDao = new LoaiDao();
	private final int pageSize = 8;
	public ArrayList<Loai> getLoai() throws Exception {
		return lDao.getLoai();
	}
	
	public ArrayList<Loai> getLoaiPhanTrang(String search, int page) throws Exception {
		String searchFinal = "";
		if (search != null) {
			searchFinal = search;
		}
		int offset = pageSize * (page - 1);
		return lDao.getLoaiPhanTrang(searchFinal, offset, pageSize);
	}
	
	public int themLoai(String maLoai, String tenLoai) throws Exception {
		// Kiểm tra maLoai đã tồn tại chưa
		Loai loai = lDao.timLoaiTuMa(maLoai);
		
		if (loai != null) {
			throw new RuntimeException("Mã loại đã tồn tại");
		}
		
		return lDao.themLoai(maLoai, tenLoai);
	}
	
	public int getTotalPages(String search) throws Exception {
		String searchFinal = "";
		if (search != null) {
			searchFinal = search;
		}
		return lDao.getTotalPages(searchFinal, pageSize);
	}
}
