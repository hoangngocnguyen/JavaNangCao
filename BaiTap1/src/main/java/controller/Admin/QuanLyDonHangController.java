package controller.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.ChiTietHoaDon.ChiTietHoaDonBo;
import modal.KhachHang.KhachHang;
import modal.LichSuMuaHang.LichSuMuaHangBo;
import modal.XacNhanMuaHang.XacNhanMuaHang;
import modal.XacNhanMuaHang.XacNhanMuaHangBo;
import modal.XacNhanMuaHang.XacNhanMuaHangDao;

import java.io.IOException;

/**
 * Servlet implementation class QuanLyDonHangController
 */
@WebServlet("/QuanLyDonHang")
public class QuanLyDonHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuanLyDonHangController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Trang này chỉ admin vào được
		KhachHang kh = (KhachHang) session.getAttribute("ss");
		if (kh == null) {
			session.setAttribute("pagePrev", "/QuanLyDonHang");
			response.sendRedirect("/DangNhap");

			return;
		}

		if (!"admin".equals(kh.getTendn())) {
			// Nếu là user thì về trang chủ
			response.sendRedirect("/TrangChu");

			return;
		}

		// Lấy trang
		String pageParam = request.getParameter("page");
		int page = 1;
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}
		
		// Lấy search mã chi tiết 
		String searchParam = request.getParameter("search");
		

		// Lấy tất cả lich sử mua hàng ra
		XacNhanMuaHangBo xnBo = new XacNhanMuaHangBo();
		

		// Chuyển view về trang quản lý đơn hàng
		try {
			request.setAttribute("dsXacNhanMuaHang", xnBo.getXacNhanMuaHang(searchParam, page));
			request.setAttribute("totalPages", xnBo.getTotalPages(searchParam));

		} catch (Exception e) {
			e.printStackTrace();
		}

		//// Xử lý xác nhận đơn hàng
		String action = request.getParameter("action");
		String maChiTietHD = request.getParameter("ma");

		if ("confirm".equals(action) && maChiTietHD != null) {
			int ma = Integer.parseInt(maChiTietHD);
			try {
				boolean isSuccess = xnBo.xacNhanDaMua(ma);

				if (isSuccess) {
					response.sendRedirect("/QuanLyDonHang");

					return;
				} else {
					// Nếu thất bại, báo lỗi
					String mess = "Xác nhận đơn hàng " + maChiTietHD + " thất bại!";
					request.setAttribute("errorMessage", mess);

					RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/quanlydonhang.jsp");
					rd.forward(request, response);

					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Trả về view
		request.setAttribute("page", page);
		request.setAttribute("search", searchParam);

		RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/quanlydonhang.jsp");
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
