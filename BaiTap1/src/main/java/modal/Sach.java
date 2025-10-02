package modal;

public class Sach {
	private String maSach;
	private String tenSach;
	private String tacGia;
	private long soLuong;
	private long gia;
	private String anh;
	private String maLoai;
	
	
	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(long soLuong) {
		this.soLuong = soLuong;
	}

	public long getGia() {
		return gia;
	}

	public void setGia(long gia) {
		this.gia = gia;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public Sach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sach(String maSach, String tenSach, String tacGia, long soLuong, long gia, String anh, String maLoai) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.soLuong = soLuong;
		this.gia = gia;
		this.anh = anh;
		this.maLoai = maLoai;
	}
	
	
}
