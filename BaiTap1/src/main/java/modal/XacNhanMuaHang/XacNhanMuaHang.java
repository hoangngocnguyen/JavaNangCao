package modal.XacNhanMuaHang;

import java.util.Date;

public class XacNhanMuaHang {
	private int maHoaDon;
	private int makh;
	private String hoTen;
	private int tongSoLuong;
	private int tongGia;
	private int thanhTien;
	private boolean damua;
	private Date ngayMua;

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
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

	public int getTongSoLuong() {
		return tongSoLuong;
	}

	public void setTongSoLuong(int tongSoLuong) {
		this.tongSoLuong = tongSoLuong;
	}

	public int getTongGia() {
		return tongGia;
	}

	public void setTongGia(int tongGia) {
		this.tongGia = tongGia;
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

	public XacNhanMuaHang(int maHoaDon, int makh, String hoTen, int tongSoLuong, int tongGia, int thanhTien,
			boolean damua, Date ngayMua) {
		super();
		this.maHoaDon = maHoaDon;
		this.makh = makh;
		this.hoTen = hoTen;
		this.tongSoLuong = tongSoLuong;
		this.tongGia = tongGia;
		this.thanhTien = thanhTien;
		this.damua = damua;
		this.ngayMua = ngayMua;
	}

	public XacNhanMuaHang() {
		super();
		// TODO Auto-generated constructor stub
	}

}
