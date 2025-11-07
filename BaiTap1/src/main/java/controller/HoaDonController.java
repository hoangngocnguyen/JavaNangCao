package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.GioHang;
import modal.GioHangBo;
import modal.HoaDonBo;
import modal.KhachHang;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class HoaDonConTroller
 */
@WebServlet("/HoaDonController")
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
		// *** Khi người dùng bấm nút thanh toán trong trang giỏ hàng

		// Lấy danh sách giỏ hàng từ session
		HttpSession session = request.getSession();
		GioHangBo gh = (GioHangBo) session.getAttribute("gh");

		// Lấy khách hàng ra:
		KhachHang kh = (KhachHang) session.getAttribute("ss");

		// Kiểm tra khách hàng: nếu chưa đăng nhập -> chuyển qua trang đăng nhập

		if (kh == null) {
			response.sendRedirect("/DangNhapController");
			return;
		}

		// * Xử lý khi thỏa các điều kiện
		// Tạo đơn hàng
		HoaDonBo hoaDonBo = new HoaDonBo();

		// Lấy các sách được checkox (chứ không phải lấy toàn bộ giỏ hàng)
		String[] dsMaSach = (String[]) request.getAttribute("dsMaSach");
		
		// Kiểm tra nếu ds == 0 ~ là chưa mua gì
		if (dsMaSach == null) {
			response.sendRedirect("/GioHangController");
			return;
		}

		// Lấy ra danh sách giỏ hàng từ mã
		ArrayList<GioHang> dsMua = new ArrayList<GioHang>();

		for (String maSach : dsMaSach) {
			GioHang item = gh.timGioHangTheoMa(maSach);
			dsMua.add(item);
		}

		try {
			hoaDonBo.taoHoaDonVaChiTiet(kh.getMakh(), dsMua);
		} catch (Exception e) {
			response.sendRedirect("/GioHangController");
			e.printStackTrace();
			return;
		}

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
