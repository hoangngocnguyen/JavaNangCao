package modal.XacNhanMuaHang;

import java.sql.SQLException;
import java.util.ArrayList;

import modal.LichSuMuaHang.LichSuMuaHang;

public class XacNhanMuaHangBo {
	XacNhanMuaHangDao xnDao = new XacNhanMuaHangDao();
	public static final int pageSize = 6;

	public ArrayList<XacNhanMuaHang> getXacNhanMuaHang(String searchMaHD, int page) throws SQLException, Exception {
		int offset = pageSize * (page - 1);
		return xnDao.getXacNhanMuaHang(searchMaHD, offset, pageSize);
	}

	public int getTotalPages(String searchMaHD) throws Exception {
		return xnDao.getTotalPages(searchMaHD, pageSize);
	}

	public boolean xacNhanDaMua(int maHD) throws Exception {
		return xnDao.xacNhanDaMua(maHD);
	}

	public boolean xacNhanDaMuaChiTiet(int maChiTietHD) throws Exception {
		return xnDao.xacNhanDaMuaChiTiet(maChiTietHD);
	}
}
