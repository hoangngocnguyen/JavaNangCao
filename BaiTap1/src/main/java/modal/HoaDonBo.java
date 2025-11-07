package modal;

import java.util.ArrayList;

public class HoaDonBo {
	HoaDonDao hoaDonDao = new HoaDonDao();
	public boolean taoHoaDonVaChiTiet(int makh, ArrayList<GioHang> dsGioHang) throws Exception {
		return hoaDonDao.taoHoaDonVaChiTiet(makh, dsGioHang);
	}
}
