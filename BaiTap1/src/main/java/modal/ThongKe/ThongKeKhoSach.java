package modal.ThongKe;

public class ThongKeKhoSach {
	private String tenLoai;
	private int soSach;
	private int tongSL;
	private double tbcGia;
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public int getSoSach() {
		return soSach;
	}
	public void setSoSach(int soSach) {
		this.soSach = soSach;
	}
	public int getTongSL() {
		return tongSL;
	}
	public void setTongSL(int tongSL) {
		this.tongSL = tongSL;
	}
	public double getTbcGia() {
		return tbcGia;
	}
	public void setTbcGia(double tbcGia) {
		this.tbcGia = tbcGia;
	}
	public ThongKeKhoSach(String tenLoai, int soSach, int tongSL, double tbcGia) {
		super();
		this.tenLoai = tenLoai;
		this.soSach = soSach;
		this.tongSL = tongSL;
		this.tbcGia = tbcGia;
	}
	public ThongKeKhoSach() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}	
