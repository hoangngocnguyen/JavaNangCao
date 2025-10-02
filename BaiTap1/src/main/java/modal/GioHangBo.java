package modal;

import java.util.ArrayList;

public class GioHangBo {
	public ArrayList<GioHang> ds = new ArrayList<GioHang>();
	
	public void them(String maSach, String tenSach, String tacGia, long sl, long gia, String anh) {
		for (GioHang gio : ds) {
			if (gio.getMaSach().equals(maSach)) {
				gio.setSoLuong(sl);
				return;
			}
		}
		GioHang gio = new GioHang(maSach, tenSach, tacGia, sl, gia, anh);
		ds.add(gio);
	}
	public long tongTien() {
		long sum = 0;
		for (GioHang gio : ds) {
			sum += gio.getThanhTien();
		}
		
		return sum;
	}
	
	public void capNhat(String maSach, long sl) {
		for (GioHang gio : ds) {
			if (gio.getMaSach().equals(maSach)) {
				gio.setSoLuong(sl);
				gio.setThanhTien(sl * gio.getGia());
				return;
			}
		}
	}
	public void xoa(String maSach) {
		ds.removeIf(gio -> gio.getMaSach().equals(maSach));
	}
}
