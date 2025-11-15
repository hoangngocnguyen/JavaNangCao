package modal.LichSuMuaHang;

import java.sql.SQLException;
import java.util.ArrayList;

public class LichSuMuaHangBo {
	LichSuMuaHangDao lsDao = new LichSuMuaHangDao();
	public static final int pageSize = 6;
	public ArrayList<LichSuMuaHang> getLichSuMuaHang(int ma, int page) throws SQLException, Exception {
		int offset = pageSize * (page - 1);
		return lsDao.getLichSuMuaHang(ma, offset, pageSize);
	}
	
	public int getTotalPages(int ma) throws Exception {
		return lsDao.getTotalPages(ma, pageSize);
	}
	
	
	public LichSuMuaHang getChiTietHoaDon(int ma, int maChiTietHoaDon) throws SQLException, Exception {
		return lsDao.getChiTietHoaDon(ma, maChiTietHoaDon);
	}
	
	
	// ADMIN:
	public ArrayList<LichSuMuaHang> getLichSuMuaHang_Admin(String searchMa, int page) throws SQLException, Exception {
		int offset = pageSize * (page - 1);
		return lsDao.getLichSuMuaHang_Admin(searchMa, offset, pageSize);
	}
	public int getTotalPages_Admin(String searchMa) throws Exception {
		return lsDao.getTotalPages_Admin(searchMa, pageSize);
	}
	
	public boolean xacNhanDaMua(int maChiTietHD) throws Exception {
		return lsDao.xacNhanDaMua(maChiTietHD);
	}
}
