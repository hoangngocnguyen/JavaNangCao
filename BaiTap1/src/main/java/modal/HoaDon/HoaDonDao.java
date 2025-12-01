package modal.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import modal.KetNoi;
import modal.GioHang.GioHang;
import modal.KhachHang.KhachHang;

public class HoaDonDao {
	// Tạo hóa đơn
	public Integer taoHoaDonVaChiTiet(int makh, ArrayList<GioHang> dsGioHang) throws Exception {

		// Kiểm tra đầu vào
		if (dsGioHang == null || dsGioHang.isEmpty()) {
			return null;
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
					return null;
				}
			}

			// --- 4. Xác nhận Giao dịch ---
			conn.commit();
			return maHoaDonMoi;

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

	// Lấy thông báo đặt hàng từ mã hóa đơn
	public ThongBaoDatHang getThongBaoDatHang(Integer maHoaDon) throws Exception {
		ThongBaoDatHang tb = null;

		// b1: kết nối cơ sở dữ liệu
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// b2: tạo câu lệnh sql
		String sql = "select * from V_ThongBaoDatHang\r\n" + "where MaHoaDon = ?";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// b3: truyền tham số vào sql (nếu có)
		preparedStatement.setInt(1, maHoaDon);

		// b4: chạy sql. rs là con trỏ vào bảng
		ResultSet rs = preparedStatement.executeQuery();

		// b4.2 Kiểm tra result set

		if (rs.next()) {
			int mahd = rs.getInt("MaHoaDon");
			String hoten = rs.getString("hoten");
			Date ngaymua = rs.getTimestamp("NgayMua");
			int tongtien = rs.getInt("TongTien");

			tb = new ThongBaoDatHang(mahd, hoten, ngaymua, tongtien);
		}

		// b5: đóng (các đối tượng đang mở)
		rs.close();
		kn.cn.close();

		// Trả về khách hàng
		return tb;
	}

	/**
	 * Trả về Mã Hóa Đơn (MaHoaDon) từ Mã Chi Tiết Hóa Đơn (MaChiTietHD).
	 *
	 * @param maChiTietHD Mã chi tiết hóa đơn cần tìm.
	 * @return MaHoaDon (int) hoặc -1 nếu không tìm thấy.
	 * @throws SQLException, Exception
	 */
	public int getMaHoaDonByMaChiTietHD(int maChiTietHD) throws SQLException, Exception {
		int maHoaDon = -1;

		// 1. Mở kết nối
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// 2. Định nghĩa và Chuẩn bị câu lệnh SQL
		// Sử dụng view V_ChiTietHoaDon để lấy MaHoaDon
		String sql = "SELECT MaHoaDon FROM V_ChiTietHoaDon WHERE MaChiTietHD = ?";
		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);

		// 3. Truyền tham số
		preparedStatement.setInt(1, maChiTietHD);

		// 4. Thực thi câu lệnh
		ResultSet rs = preparedStatement.executeQuery();

		// 5. Lấy kết quả
		if (rs.next()) {
			maHoaDon = rs.getInt("MaHoaDon");
		}

		// 6. Đóng các đối tượng
		rs.close();
		preparedStatement.close();
		kn.cn.close();

		return maHoaDon;
	}
}
