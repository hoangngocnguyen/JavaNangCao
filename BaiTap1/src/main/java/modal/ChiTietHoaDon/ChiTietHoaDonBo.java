package modal.ChiTietHoaDon;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietHoaDonBo {
	ChiTietHoaDonDao lsDao = new ChiTietHoaDonDao();
	public static final int pageSize = 6;
	public ArrayList<ChiTietHoaDon> getChiTietHoaDon(int ma, int page) throws SQLException, Exception {
		int offset = pageSize * (page - 1);
		return lsDao.getChiTietHoaDon(ma, offset, pageSize);
	}
	
	public int getTotalPages(int ma) throws Exception {
		return lsDao.getTotalPages(ma, pageSize);
	}
}
