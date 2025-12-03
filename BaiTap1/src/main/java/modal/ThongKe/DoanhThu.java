package modal.ThongKe;

public class DoanhThu {
	private int thang;
	private int nam;
	private long tongDoanhThu;
	public DoanhThu(int thang, int nam, long tongDoanhThu) {
		super();
		this.thang = thang;
		this.nam = nam;
		this.tongDoanhThu = tongDoanhThu;
	}
	public DoanhThu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public long getTongDoanhThu() {
		return tongDoanhThu;
	}
	public void setTongDoanhThu(long tongDoanhThu) {
		this.tongDoanhThu = tongDoanhThu;
	}
	
	
}
