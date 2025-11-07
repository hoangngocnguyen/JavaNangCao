package modal;

import java.util.ArrayList;

public class GioHangBo {
	private ArrayList<GioHang> ds = new ArrayList<GioHang>();
	
	public ArrayList<GioHang> getDs() {
		return ds;
	}
	
	public GioHang timGioHangTheoMa(String maSach) {
	    for (GioHang item : ds) {
	        if (item.getMaSach().equalsIgnoreCase(maSach)) {
	            return item;
	        }
	    }
	    return null;
	}
	public void them(String maSach, String tenSach, String tacGia, long sl, long gia, String anh) {
		for (GioHang gio : ds) {
			if (gio.getMaSach().trim().toLowerCase().equals(maSach.trim().toLowerCase())) {
				gio.setSoLuong(gio.getSoLuong()+1);
				return;
			}
		}
		GioHang gio = new GioHang(maSach, tenSach, tacGia, sl, gia, anh);
		ds.add(gio);
	}
	public long getTongTien() {
		long sum = 0;
		for (GioHang gio : ds) {
			sum += gio.getThanhTien();
		}
		
		return sum;
	}
	
	public void capNhat(String maSach, long sl) {
		if (sl == 0) {
			xoa(maSach);
			return;
		}
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
	
	public void xoaTatCa() {
		ds.removeAll(ds);
	}
}
