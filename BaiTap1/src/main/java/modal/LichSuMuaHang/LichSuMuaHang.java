package modal.LichSuMuaHang;

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
	private int maChiTietHoaDon;
	private String anh;
	
	
	
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
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
	
	
	
	public int getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}
	public void setMaChiTietHoaDon(int maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	
	
	public LichSuMuaHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LichSuMuaHang(int makh, String tensach, int gia, int soLuongMua, int thanhTien, boolean damua, Date ngayMua,
			int maHoaDon, int maChiTietHoaDon, String anh) {
		super();
		this.makh = makh;
		this.tensach = tensach;
		this.gia = gia;
		this.soLuongMua = soLuongMua;
		this.thanhTien = thanhTien;
		this.damua = damua;
		this.ngayMua = ngayMua;
		this.maHoaDon = maHoaDon;
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.anh = anh;
	}
	
	
	
}
