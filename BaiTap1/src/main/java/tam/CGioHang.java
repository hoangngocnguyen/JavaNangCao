package tam;

import java.util.ArrayList;
import java.util.List;

public class CGioHang {
	//Tao ra 1 mang de luu Hang
	public List<Hang> ds = new ArrayList<Hang>();
	
	// Sản phẩm đã có trong giỏ thì tăng số lượng, thành tiền, chưa có trong giỏ thì thêm mới.
	// Note: giá đưa vào để thêm khi chưa có sản phẩm đó, nếu có rồi thì lấy giá cũ.
	// Note: cần tránh suy nghĩ đến thêm sản phẩm, đây là thêm vào giỏ hàng từ sản phẩm có sẵn.
	public void them(String th, int gia, int sl) {
		for (Hang hang : ds) {
			if (hang.getTenhang().toLowerCase().trim().equals(th.toLowerCase().trim())) {
				int soLuongMoi = hang.getSoluong() + sl;
				hang.setSoluong(soLuongMoi);
				
				int thanhTien = hang.getGia()*soLuongMoi;
				hang.setThanhtien(thanhTien);
				
				return;
			}
		}
		// Nếu giỏ hàng chưa có sản phẩm đó thì thêm mới
		Hang hang = new Hang(th, gia, sl);
		ds.add(hang);
	}
	
	// Hàm xóa sản phẩm trong giỏ hàng
	public void xoa(String th) {
		ds.removeIf(hang -> hang.getTenhang().trim().equalsIgnoreCase(th.trim()));
	}
	
	// Hàm xóa tất cả sản phẩm
	public void xoaTatCa() {
		ds.removeAll(ds);
	}
	
	// Tính tổng tiền tất cả sản phẩm của giỏ hàng.
	public long tongTien() {
		long total = 0;
		for (Hang hang : ds) {
			total += hang.getThanhtien();
		}
		return total;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for (int i = 0; i< 10; i++) {
			lst.add(i);
		}
		lst.removeAll(lst);
		for (int i = 0; i< lst.size(); i++) {
			System.out.println(lst.get(i));
		}
		System.out.println("hi");
	}
}
