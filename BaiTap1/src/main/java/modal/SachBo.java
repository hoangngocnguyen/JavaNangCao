package modal;

import java.util.ArrayList;

public class SachBo {
	SachDao sachDao = new SachDao();
	ArrayList<Sach> ds;
	public ArrayList<Sach> getSach() throws Exception {
		// Sử dụng mảng để lưu sản phẩm từ DB. (tính toán trong RAM)
		ds = sachDao.getSach();
		return ds;
	}
	
	public ArrayList<Sach> locSachPhanTrang(String maLoaiParam, String search, int page) throws Exception {
		final int pageSize = 8;
		int offset = pageSize * (page - 1);
		return sachDao.locSachPhanTrang(maLoaiParam, search, offset, pageSize);
	}
	
	public int getTotalPages(String maLoaiParam, String search) throws Exception {
		return sachDao.getTotalPages(maLoaiParam, search);
	}
	
	public ArrayList<Sach> getSach(String maLoai) throws Exception {
		// Dùng list mới
		ArrayList<Sach> lst = new ArrayList<Sach>();
		for (Sach sach : sachDao.getSach()) {
			if(sach.getMaLoai().trim().equalsIgnoreCase(maLoai)) {
				lst.add(sach);
			}
		}
		return lst;
	}
	
	public Sach timSachTheoMa(String maSach) throws Exception {
		Sach sach = sachDao.getSach().stream()
				.filter(s -> s.getMaSach().equals(maSach))
				.findFirst().orElse(null);
		
		return sach;
	}
	
	public ArrayList<Sach> timSach(String key) throws Exception{
		ArrayList<Sach> lst = new ArrayList<Sach>();
		
		for (Sach sach : sachDao.getSach()) {
			if(sach.getTenSach().trim().toLowerCase().contains(key.trim().toLowerCase())) {
				lst.add(sach);
			}
		}
		
		return lst;
	}
	
}
