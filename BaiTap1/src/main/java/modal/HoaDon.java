package modal;

import java.time.LocalDateTime;

public class HoaDon {
	private int MaHoaDon;
	private int makh;
	private LocalDateTime NgayMua;
	private boolean damua;
	public int getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		MaHoaDon = maHoaDon;
	}
	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	public LocalDateTime getNgayMua() {
		return NgayMua;
	}
	public void setNgayMua(LocalDateTime ngayMua) {
		NgayMua = ngayMua;
	}
	public boolean isDamua() {
		return damua;
	}
	public void setDamua(boolean damua) {
		this.damua = damua;
	}
	
	
	public HoaDon() {
		super();
	}
	public HoaDon(int maHoaDon, int makh, LocalDateTime ngayMua, boolean damua) {
		super();
		MaHoaDon = maHoaDon;
		this.makh = makh;
		NgayMua = ngayMua;
		this.damua = damua;
	}
	
	
	
}
