package modal.ChiTietHoaDon;

import java.util.Date;

public class ChiTietHoaDon {
	private int maHoaDon;
	private int maChiTietHD;
	private int makh;
	private String hoTen;
	private String tenSach;
	private int soLuongMua;
	private int gia;
	private int thanhTien;
	private boolean damua;

	public ChiTietHoaDon(int maHoaDon, int maChiTietHD, int makh, String hoTen, String tenSach, int soLuongMua, int gia,
			int thanhTien, boolean damua) {
		super();
		this.maHoaDon = maHoaDon;
		this.maChiTietHD = maChiTietHD;
		this.makh = makh;
		this.hoTen = hoTen;
		this.tenSach = tenSach;
		this.soLuongMua = soLuongMua;
		this.gia = gia;
		this.thanhTien = thanhTien;
		this.damua = damua;
	}

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public int getMaChiTietHD() {
		return maChiTietHD;
	}

	public void setMaChiTietHD(int maChiTietHD) {
		this.maChiTietHD = maChiTietHD;
	}

	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
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

	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

}
