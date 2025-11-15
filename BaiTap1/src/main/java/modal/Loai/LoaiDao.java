package modal.Loai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modal.KetNoi;

public class LoaiDao {
	public ArrayList<Loai> getLoai() throws Exception {
		ArrayList<Loai> ds = new ArrayList<Loai>();
		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		
		// b2: tạo câu lệnh sql
		String sql = "select * from loai";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);
		
		// b3: truyền tham số vào sql (nếu có)
		
		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();
		
		//b4.2 Duyệt result set, đưa vào List
		while(rs.next()) {
			String maLoai = rs.getString("maloai");
			String tenLoai = rs.getString("tenloai");
			
			ds.add(new Loai(maLoai, tenLoai));
		}
		
		//b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();
		
		return ds;
	}
}
