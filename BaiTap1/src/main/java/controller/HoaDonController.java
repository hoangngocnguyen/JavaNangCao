package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.GioHang.GioHang;
import modal.GioHang.GioHangBo;
import modal.HoaDon.HoaDonBo;
import modal.KhachHang.KhachHang;
import utils.TaskExecutor;

/**
 * Servlet implementation class HoaDonConTroller
 */
@WebServlet("/HoaDon")
public class HoaDonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HoaDonController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// *** Khi người dùng bấm nút thanh toán trong trang giỏ hàng, chuyển hướng đến đây

		// Lấy danh sách giỏ hàng từ session
		HttpSession session = request.getSession();
		GioHangBo gh = (GioHangBo) session.getAttribute("gh");

		// Lấy khách hàng ra:
		KhachHang kh = (KhachHang) session.getAttribute("ss");

		// Kiểm tra khách hàng: nếu chưa đăng nhập -> chuyển qua trang đăng nhập

		if (kh == null) {
			response.sendRedirect("/DangNhap");
			
			// Lưu trang trước đó vào session
			session.setAttribute("page", "/GioHang");
			return;
		}

		// * Xử lý khi thỏa các điều kiện
		// Tạo đơn hàng
		HoaDonBo hoaDonBo = new HoaDonBo();

		// Lấy các sách được checkox (chứ không phải lấy toàn bộ giỏ hàng)
		String[] dsMaSach = (String[]) request.getAttribute("dsMaSach");
		
		// Kiểm tra nếu ds == 0 ~ là chưa mua gì
		if (dsMaSach == null) {
			response.sendRedirect("/GioHang");
			return;
		}

		// Lấy ra danh sách giỏ hàng từ mã
		ArrayList<GioHang> dsMua = new ArrayList<GioHang>();

		for (String maSach : dsMaSach) {
			GioHang item = gh.timGioHangTheoMa(maSach);
			dsMua.add(item);
		}

		try {
			Integer maHoaDon = hoaDonBo.taoHoaDonVaChiTiet(kh.getMakh(), dsMua);
			
			
			// 2. Định nghĩa tác vụ Gửi Email (Runnable)
			Runnable mailTask = () -> {
				try {
					// Gửi email cho người dùng thông báo đặt hàng thành công
					// Lấy thông tin từ hóa đơn + chi tiết hóa đơn
					
					hoaDonBo.guiEmailThongBaoDatHangThanhCong(kh, maHoaDon);
				} catch (Exception e) {
					System.err.println("Lỗi gửi email bất đồng bộ cho đơn hàng " + maHoaDon + ": " + e.getMessage());
	                // (Tùy chọn: Có thể thử gửi lại sau, hoặc lưu vào DB để xử lý sau)
				}
			};
			
			// 3. Gửi tác vụ vào Thread Pool và giải phóng thread chính
			TaskExecutor.submitMailTask(mailTask);
			
			
		} catch (Exception e) {
			response.sendRedirect("/GioHang");
			e.printStackTrace();
			return;
		}
		
		// CHuyển hướng người dùng (~ thread chính kết thúc)
		RequestDispatcher rd = request.getRequestDispatcher("/LichSuMuaHang");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
