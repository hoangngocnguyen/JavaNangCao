package modal;

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
}
