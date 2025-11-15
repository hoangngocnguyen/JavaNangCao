package modal.HoaDon;

import java.time.LocalDateTime;
import java.util.Date;

public class ThongBaoDatHang {
	private int maHoaDon;
	private String hoten;
	private Date ngayMua;
	private int tongTien;
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public Date getNgayMua() {
		return ngayMua;
	}
	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	public ThongBaoDatHang(int maHoaDon, String hoten, Date ngayMua, int tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.hoten = hoten;
		this.ngayMua = ngayMua;
		this.tongTien = tongTien;
	}
	public ThongBaoDatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
