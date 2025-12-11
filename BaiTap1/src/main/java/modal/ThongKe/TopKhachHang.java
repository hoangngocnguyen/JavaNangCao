package modal.ThongKe;

public class TopKhachHang {
	private int maKH;
	private String hoTen;
	private String diaChi;
	private String sodt;
	private String email;
	private long tongChiTieu;
	private int thang;
	
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSodt() {
		return sodt;
	}
	public void setSodt(String sodt) {
		this.sodt = sodt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTongChiTieu() {
		return tongChiTieu;
	}
	public void setTongChiTieu(long tongChiTieu) {
		this.tongChiTieu = tongChiTieu;
	}
	
	
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public TopKhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TopKhachHang(int maKH, String hoTen, String diaChi, String sodt, String email, long tongChiTieu, int thang) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.sodt = sodt;
		this.email = email;
		this.tongChiTieu = tongChiTieu;
		this.thang = thang;
	}
	
	
	
	
}
