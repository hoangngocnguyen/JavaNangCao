package modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SachDao {
	public ArrayList<Sach> getSach() throws Exception {
		ArrayList<Sach> ds = new ArrayList<Sach>();

		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from sach";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Duyệt result set, đưa vào List
		while (rs.next()) {
			String maSach = rs.getString("masach");
			String tenSach = rs.getString("tensach");
			String tacGia = rs.getString("tacgia");
			long soLuong = rs.getLong("soluong");
			long gia = rs.getLong("gia");
			String anh = rs.getString("anh");
			String maLoai = rs.getString("maloai");

			ds.add(new Sach(maSach, tenSach, tacGia, soLuong, gia, anh, maLoai));
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();
		return ds;
	}
	
	
	public ArrayList<Sach> locSachPhanTrang(String maLoaiParam, String search, int offset, int pageSize) throws Exception {
	    ArrayList<Sach> ds = new ArrayList<Sach>();
	    
	    // --- Chuẩn bị Tham số (Làm sạch và kiểm tra NULL/Rỗng) ---
	    // Nếu là null hoặc rỗng, gán lại là null
	    String finalSearch = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
	    String finalMaLoai = (maLoaiParam != null && !maLoaiParam.trim().isEmpty()) ? maLoaiParam.trim() : null;

	    // --- Kết nối CSDL ---
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    Connection conn = kn.cn;
	    
	    // --- Xây dựng SQL Động và List Tham số ---
	    StringBuilder sqlBuilder = new StringBuilder();
	    List<Object> params = new ArrayList<>();

	    // Bắt đầu truy vấn với WHERE 1=1
	    sqlBuilder.append("SELECT * FROM sach s JOIN loai l ON s.maloai = l.maloai WHERE 1=1");

	    // 1. Lọc theo Tên sách (Search)
	    if (finalSearch != null) {
	        sqlBuilder.append(" AND tensach LIKE ?");
	        params.add("%" + finalSearch + "%"); // Giá trị LIKE
	    }

	    // 2. Lọc theo Mã loại
	    if (finalMaLoai != null) {
	        sqlBuilder.append(" AND s.maloai = ?");
	        params.add(finalMaLoai);
	    }
	    
	    // 3. Phân trang và Sắp xếp
	    
	    sqlBuilder.append(" ORDER BY masach OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
	    
	    // Thêm tham số OFFSET và FETCH vào cuối List
	    params.add(offset);
	    params.add(pageSize); 

	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        preparedStatement = conn.prepareStatement(sqlBuilder.toString());

	        // Thiết lập tham số theo thứ tự
	        for (int i = 0; i < params.size(); i++) {
	            // Sử dụng setString cho các tham số chuỗi/LIKE (1 và 2) và setInt cho phân trang (3 và 4)
	            Object param = params.get(i);
	            if (param instanceof String) {
	                preparedStatement.setString(i + 1, (String) param);
	            } else if (param instanceof Integer) {
	                preparedStatement.setInt(i + 1, (Integer) param);
	            } else {
	                 preparedStatement.setObject(i + 1, param);
	            }
	        }
	        
	        // --- Chạy SQL ---
	        rs = preparedStatement.executeQuery();

	        // --- Duyệt result set ---
	        while (rs.next()) {
	            // ... (Phần code lấy dữ liệu Sach giữ nguyên) ...
	            String maSach = rs.getString("masach");
	            String tenSach = rs.getString("tensach");
	            String tacGia = rs.getString("tacgia");
	            long soLuong = rs.getLong("soluong");
	            long gia = rs.getLong("gia");
	            String anh = rs.getString("anh");
	            String maLoai = rs.getString("maloai");
	            ds.add(new Sach(maSach, tenSach, tacGia, soLuong, gia, anh, maLoai));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // --- Đóng kết nối ---
	        if (rs != null) rs.close();
	        if (preparedStatement != null) preparedStatement.close();
	        if (conn != null) conn.close();
	    }
	    
	    return ds;
	}
	
	public int getTotalPages(String maLoaiParam, String search) throws Exception {
	    
	    // Đảm bảo pageSize là 8 theo yêu cầu
	    final int pageSize = 8;
	    
	    // --- Chuẩn bị Tham số (Làm sạch và kiểm tra NULL/Rỗng) ---
	    String finalSearch = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
	    String finalMaLoai = (maLoaiParam != null && !maLoaiParam.trim().isEmpty()) ? maLoaiParam.trim() : null;

	    // --- Kết nối CSDL ---
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    Connection conn = kn.cn;
	    
	    // --- Xây dựng SQL Động và List Tham số ---
	    StringBuilder sqlBuilder = new StringBuilder();
	    List<Object> params = new ArrayList<>();

	    // Truy vấn đếm (COUNT(*))
	    sqlBuilder.append("SELECT count(*) FROM sach s JOIN loai l ON s.maloai = l.maloai WHERE 1=1");

	    // 1. Lọc theo Tên sách (Search)
	    if (finalSearch != null) {
	        sqlBuilder.append(" AND tensach LIKE ?");
	        params.add("%" + finalSearch + "%"); 
	    }

	    // 2. Lọc theo Mã loại
	    if (finalMaLoai != null) {
	        sqlBuilder.append(" AND s.maloai = ?");
	        params.add(finalMaLoai);
	    }

	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    int totalPages = 1;

	    try {
	        preparedStatement = conn.prepareStatement(sqlBuilder.toString());

	        // Thiết lập tham số theo thứ tự
	        for (int i = 0; i < params.size(); i++) {
	            preparedStatement.setString(i + 1, params.get(i).toString());
	        }

	        // --- Chạy SQL ---
	        rs = preparedStatement.executeQuery();
	        
	        // --- Lấy tổng số bản ghi và tính tổng số trang ---
	        if (rs.next()) {
	            int totalRecords = rs.getInt(1); 
	            
	            // Tính tổng số trang (chia làm tròn lên)
	            if (totalRecords > 0) {
	                // Sử dụng Math.ceil để làm tròn lên
	                totalPages = (int) Math.ceil((double) totalRecords / pageSize);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // --- Đóng kết nối ---
	        if (rs != null) rs.close();
	        if (preparedStatement != null) preparedStatement.close();
	        if (conn != null) conn.close();
	    }
	    
	    return totalPages;
	}
}
