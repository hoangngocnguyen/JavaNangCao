package HoangModal;

public class Hoang_ThongKeDTB {
	private String maLop;
	private String tenLop;
	private double tbc;
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getTenLop() {
		return tenLop;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	public double getTbc() {
		return tbc;
	}
	public void setTbc(double tbc) {
		this.tbc = tbc;
	}
	public Hoang_ThongKeDTB(String maLop, String tenLop, double tbc) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.tbc = tbc;
	}
	public Hoang_ThongKeDTB() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
