package modal.ThongKe;

public class ThongKeTongQuan {
	private int tongSoLoai; 
    private int tongSoSach;
    private int tongTonKho;
	public int getTongSoLoai() {
		return tongSoLoai;
	}
	public void setTongSoLoai(int tongSoLoai) {
		this.tongSoLoai = tongSoLoai;
	}
	public int getTongSoSach() {
		return tongSoSach;
	}
	public void setTongSoSach(int tongSoSach) {
		this.tongSoSach = tongSoSach;
	}
	public int getTongTonKho() {
		return tongTonKho;
	}
	public void setTongTonKho(int tongTonKho) {
		this.tongTonKho = tongTonKho;
	}
	public ThongKeTongQuan(int tongSoLoai, int tongSoSach, int tongTonKho) {
		super();
		this.tongSoLoai = tongSoLoai;
		this.tongSoSach = tongSoSach;
		this.tongTonKho = tongTonKho;
	}
	public ThongKeTongQuan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
    

}
