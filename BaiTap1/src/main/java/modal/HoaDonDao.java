package modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDonDao {
	// Tạo hóa đơn
	public boolean taoHoaDonVaChiTiet(int makh, ArrayList<GioHang> dsGioHang) throws Exception {

		// Kiểm tra đầu vào
		if (dsGioHang == null || dsGioHang.isEmpty()) {
			return false;
		}

		KetNoi kn = new KetNoi();
		kn.ketnoi();
		Connection conn = kn.cn;

		// --- 1. Bắt đầu Giao dịch ---
		conn.setAutoCommit(false);

		PreparedStatement psHoaDon = null;
		PreparedStatement psChiTiet = null;
		ResultSet rs = null;
		int maHoaDonMoi = -1;

		String sqlHoaDon = "INSERT INTO hoadon (makh, NgayMua, damua) VALUES (?, ?, ?)";
		String sqlChiTiet = "INSERT INTO ChiTietHoaDon (MaSach, SoLuongMua, MaHoaDon, damua) VALUES (?, ?, ?, ?)";

		try {
			// --- 2. Tạo Hóa đơn (hoadon) và Lấy ID ---
			psHoaDon = conn.prepareStatement(sqlHoaDon, PreparedStatement.RETURN_GENERATED_KEYS);

			psHoaDon.setInt(1, makh);
			psHoaDon.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			psHoaDon.setBoolean(3, false);

			int rowAffected = psHoaDon.executeUpdate();

			if (rowAffected > 0) {
				rs = psHoaDon.getGeneratedKeys();
				if (rs.next()) {
					maHoaDonMoi = rs.getInt(1);
				}
			} else {
				// Không tạo được Hóa đơn, rollback và thoát
				conn.rollback();
				return false;
			}

			// --- 3. Tạo Chi tiết Hóa đơn (chitiethoadon) dùng Batch ---
			psChiTiet = conn.prepareStatement(sqlChiTiet);

			for (GioHang gh : dsGioHang) {
				psChiTiet.setString(1, gh.getMaSach());
				psChiTiet.setInt(2, (int) gh.getSoLuong()); // Ép kiểu long sang int
				psChiTiet.setInt(3, maHoaDonMoi);
				psChiTiet.setBoolean(4, false);
				psChiTiet.addBatch();
			}

			// Thực thi lô (Batch)
			int[] result = psChiTiet.executeBatch();

			// Kiểm tra kết quả Batch
			for (int count : result) {
				if (count == PreparedStatement.EXECUTE_FAILED) {
					// Nếu bất kỳ chi tiết nào thất bại, rollback toàn bộ
					conn.rollback();
					return false;
				}
			}

			// --- 4. Xác nhận Giao dịch ---
			conn.commit();
			return true;

		} catch (Exception e) {
			// --- 5. Xử lý Lỗi và Hủy bỏ ---
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace();
			throw e;
		} finally {
			// --- 6. Đóng tài nguyên ---
			if (rs != null)
				rs.close();
			if (psHoaDon != null)
				psHoaDon.close();
			if (psChiTiet != null)
				psChiTiet.close();
			if (conn != null) {
				conn.setAutoCommit(true);
				conn.close();
			}
		}
	}
}
