package modal;

import java.util.ArrayList;

public class ThongKeBo {
	ThongKeDao tkDao = new ThongKeDao();
	public static final int pageSize = 10;
	
	public ArrayList<ThongKeKhoSach> getThongKeKhoSach(int page) throws Exception {
		int offset = pageSize * (page - 1);
		return tkDao.getThongKeKhoSach(offset, pageSize);
	}

	public int getTotalPages_ThongKeKhoSach() throws Exception {
		return tkDao.getTotalPages_ThongKeKhoSach(pageSize);
	}
	
	public ThongKeTongQuan getThongKeTongQuan() throws Exception {
		return tkDao.getThongKeTongQuan();
	}
	

}
