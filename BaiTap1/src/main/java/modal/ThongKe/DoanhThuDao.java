package modal.ThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modal.KetNoi;

public class DoanhThuDao {
	/**
	 * Lấy thống kê doanh thu trong 3 tháng gần nhất, bao gồm cả các tháng không có
	 * giao dịch (DoanhThu = 0). Sử dụng kỹ thuật LEFT JOIN với chuỗi tháng
	 * (MonthSeries CTE) và tính toán tổng tiền dựa trên sach.gia (không phải best
	 * practice). * @return ArrayList<DoanhThu> danh sách 3 tháng gần nhất.
	 * 
	 * @throws Exception nếu có lỗi kết nối hoặc SQL.
	 */
	public ArrayList<DoanhThu> getDoanhThuBaThang() throws Exception {

		ArrayList<DoanhThu> dsDoanhThu = new ArrayList<>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		// Chuỗi truy vấn SQL sử dụng CTE (được chia nhỏ để dễ đọc)
		String sql = "WITH MonthSeries AS ( " + "    SELECT EOMONTH(GETDATE(), -2) AS NgayKetThucThang "
				+ "    UNION ALL " + "    SELECT EOMONTH(GETDATE(), -1) " + "    UNION ALL "
				+ "    SELECT EOMONTH(GETDATE(), 0) " + "), " + "RevenueCTE AS ( "
				+ "    SELECT EOMONTH(dbo.hoadon.NgayMua) AS NgayKetThucThang, "
				+ "           SUM(dbo.ChiTietHoaDon.SoLuongMua * dbo.sach.gia) AS DoanhThuThucTe "
				+ "    FROM dbo.ChiTietHoaDon "
				+ "    INNER JOIN dbo.hoadon ON dbo.ChiTietHoaDon.MaHoaDon = dbo.hoadon.MaHoaDon "
				+ "    INNER JOIN dbo.sach ON dbo.ChiTietHoaDon.MaSach = dbo.sach.masach "
				+ "    WHERE dbo.hoadon.NgayMua >= DATEADD(month, -3, EOMONTH(GETDATE(), 0)) "
				+ "      AND dbo.hoadon.NgayMua <= EOMONTH(GETDATE(), 0) " + "      AND dbo.ChiTietHoaDon.damua = 0 "
				+ "    GROUP BY EOMONTH(dbo.hoadon.NgayMua) " + ") " + "SELECT "
				+ "    YEAR(m.NgayKetThucThang) AS Nam, " + "    MONTH(m.NgayKetThucThang) AS Thang, "
				+ "    COALESCE(r.DoanhThuThucTe, 0) AS TongDoanhThu " + "FROM MonthSeries m "
				+ "LEFT JOIN RevenueCTE r ON m.NgayKetThucThang = r.NgayKetThucThang " + "ORDER BY Nam, Thang;";

		PreparedStatement preparedStatement = kn.cn.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			int thang = rs.getInt("Thang");
			int nam = rs.getInt("Nam");
			// Sử dụng getLong vì tổng doanh thu có thể rất lớn
			long tongDoanhThu = rs.getLong("TongDoanhThu");

			// Khởi tạo đối tượng DoanhThu và thêm vào danh sách
			dsDoanhThu.add(new DoanhThu(thang, nam, tongDoanhThu));
		}

		// Đóng kết nối
		rs.close();
		preparedStatement.close();
		kn.cn.close();

		return dsDoanhThu;
	}
}
