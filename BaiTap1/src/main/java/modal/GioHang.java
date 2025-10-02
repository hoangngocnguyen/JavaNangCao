package modal;

public class GioHang {
	private String maSach;
	private String tenSach;
	private String tacGia;
	private long soLuong;
	private long gia;
	private String anh;
	private long thanhTien;
	
	
	
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
	
	// Sửa lại
	public long getThanhTien() {
		return soLuong*gia;
	}
	public void setThanhTien(long thanhTien) {
		this.thanhTien = thanhTien;
	}
	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Thành tiền không đưa vào, nhưng vẫn tính.
	public GioHang(String maSach, String tenSach, String tacGia, long soLuong, long gia, String anh) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.soLuong = soLuong;
		this.gia = gia;
		this.anh = anh;
		this.thanhTien = soLuong*gia;
	}
	
	
}
