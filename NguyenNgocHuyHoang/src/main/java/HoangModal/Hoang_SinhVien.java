package HoangModal;

public class Hoang_SinhVien {
	private String maSV;
	private String hoTen;
	private String diaChi;
	private String email;
	private double DTB;
	private String maLop;
	
	public String getMaSV() {
		return maSV;
	}


	public void setMaSV(String MaSV) {
		this.maSV = MaSV;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public double getDTB() {
		return DTB;
	}



	public void setDTB(double dTB) {
		DTB = dTB;
	}



	public String getMaLop() {
		return maLop;
	}



	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}



	public Hoang_SinhVien(String maSV, String hoTen, String diaChi, String email, double dTB, String maLop) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.email = email;
		DTB = dTB;
		this.maLop = maLop;
	}



	public Hoang_SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
