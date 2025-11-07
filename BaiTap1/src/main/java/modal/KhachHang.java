package modal;

public class KhachHang {
	private int makh;
	private String pass;
	private String hoten;
	private String diachi;
	private String sodt;
	private String email;
	private String tenDn;
	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
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
	public String getTendn() {
		return tenDn;
	}
	public void setTendn(String tendn) {
		this.tenDn = tendn;
	}
	
	
	public KhachHang() {
		super();
	}
	public KhachHang(int makh, String pass, String hoten, String diachi, String sodt, String email, String tendn) {
		super();
		this.makh = makh;
		this.pass = pass;
		this.hoten = hoten;
		this.diachi = diachi;
		this.sodt = sodt;
		this.email = email;
		this.tenDn = tendn;
	}
	
	
}
