package modal;

public class ChiTietHoaDon {
	private int MaChiTietHD;
	private String MaSach;
	private int SoLuongMua;
	private int MaHoaDon;
	private boolean damua;

	public int getMaChiTietHD() {
		return MaChiTietHD;
	}

	public void setMaChiTietHD(int maChiTietHD) {
		MaChiTietHD = maChiTietHD;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public int getSoLuongMua() {
		return SoLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		SoLuongMua = soLuongMua;
	}

	public int getMaHoaDon() {
		return MaHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		MaHoaDon = maHoaDon;
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

	public ChiTietHoaDon(int maChiTietHD, String maSach, int soLuongMua, int maHoaDon, boolean damua) {
		super();
		MaChiTietHD = maChiTietHD;
		MaSach = maSach;
		SoLuongMua = soLuongMua;
		MaHoaDon = maHoaDon;
		this.damua = damua;
	}

}
