package modal;

import java.util.Date;

public class LichSuMuaHang {
	private int makh;
	private String tensach;
	private int gia;
	private int soLuongMua;
	private int thanhTien;
	private boolean damua;
	private Date ngayMua;
	private int maHoaDon;
	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	public String getTensach() {
		return tensach;
	}
	public void setTensach(String tensach) {
		this.tensach = tensach;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public int getSoLuongMua() {
		return soLuongMua;
	}
	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}
	public int getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}
	public boolean isDamua() {
		return damua;
	}
	public void setDamua(boolean damua) {
		this.damua = damua;
	}
	public Date getNgayMua() {
		return ngayMua;
	}
	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	
	public LichSuMuaHang(int makh, String tensach, int gia, int soLuongMua, int thanhTien, boolean damua,
			Date ngayMua, int maHoaDon) {
		super();
		this.makh = makh;
		this.tensach = tensach;
		this.gia = gia;
		this.soLuongMua = soLuongMua;
		this.thanhTien = thanhTien;
		this.damua = damua;
		this.ngayMua = ngayMua;
		this.maHoaDon = maHoaDon;
	}
	public LichSuMuaHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
