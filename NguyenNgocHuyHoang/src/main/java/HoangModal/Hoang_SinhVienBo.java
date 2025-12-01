package HoangModal;

import java.util.ArrayList;

public class Hoang_SinhVienBo {
	Hoang_SinhVienDao svDao = new Hoang_SinhVienDao();
	public ArrayList<Hoang_SinhVien> getAllSinhVien() throws Exception {
		return svDao.getAllSinhVien();
	}
	public ArrayList<Hoang_SinhVien> findSinhVienBySearch(String search) throws Exception {
		return svDao.findSinhVienBySearch(search);
	}
	
	public ArrayList<Hoang_ThongKeDTB> thongKeDTBTheoLop() throws Exception {
		return svDao.thongKeDTBTheoLop();
	}
}
