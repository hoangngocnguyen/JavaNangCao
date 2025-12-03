package modal.ThongKe;

import java.sql.SQLException;
import java.util.ArrayList;

import modal.HoaDon.HoaDonDao;
import modal.XacNhanMuaHang.XacNhanMuaHang;

public class ThongKeBo {
	ThongKeDao tkDao = new ThongKeDao();
	HomNayDao hnDao = new HomNayDao();
	public static final int pageSize = 10;
	
	public ArrayList<ThongKeKhoSach> getThongKeKhoSach(String searchTenLoai, int page) throws Exception {
		int offset = pageSize * (page - 1);
		
		String pattern = "";
		if (searchTenLoai != null) {
			pattern = "%" + searchTenLoai + "%";
		} else {
			pattern = "%%";
		}
		
		return tkDao.getThongKeKhoSach(pattern, offset, pageSize);
	}

	public int getTotalPages_ThongKeKhoSach(String searchTenLoai) throws Exception {
		String pattern = "";
		if (searchTenLoai != null) {
			pattern = "%" + searchTenLoai + "%";
		} else {
			pattern = "%%";
		}
		return tkDao.getTotalPages_ThongKeKhoSach(pattern, pageSize);
	}
	
	public ThongKeTongQuan getThongKeTongQuan() throws Exception {
		return tkDao.getThongKeTongQuan();
	}
	
	
//	Thống kê ngày hôm nay: hóa đơn
	public ArrayList<XacNhanMuaHang> getHoaDonHomNay(int page) throws SQLException, Exception {
		int offset = pageSize * (page - 1);
		return hnDao.getHoaDonHomNay(offset, pageSize);
	}
	
	public int getTotalPages() throws Exception {
		return hnDao.getTotalPages(pageSize);
	}
	
	public int getTotalElements() throws Exception {
		return hnDao.getTotalElements();
	}
	
	public int getTongThuNhapHomNay() throws Exception {
		return hnDao.getTongThuNhapHomNay();
	}
}
