package controller.Admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.KhachHang.KhachHang;
import modal.ThongKe.ThongKeBo;
import modal.XacNhanMuaHang.XacNhanMuaHangBo;

@WebServlet("/HomNay")
public class HomNayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomNayController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy danh sách hóa đơn hôm nay
		HttpSession session = request.getSession();
		KhachHang kh = (KhachHang) session.getAttribute("ss");
		if (kh == null) {
			session.setAttribute("pagePrev", "/HomNay");
			response.sendRedirect("/DangNhap");

			return;
		}

		if (!"admin".equals(kh.getTendn())) {
			// Về trang đăng nhập, ghi lại trang hiện tại
			session.setAttribute("pagePrev", "/HomNay");
			response.sendRedirect("/DangNhap");

			return;
		}

		// Lấy trang
		String pageParam = request.getParameter("page");
		int page = 1;
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}

		// Lấy tất cả hóa đơn hôm nay
		ThongKeBo tkBo = new ThongKeBo();
		
		
		

		// Chuyển view về trang quản lý đơn hàng
		try {
			request.setAttribute("dsHoaDonHomNay", tkBo.getHoaDonHomNay(page));
			request.setAttribute("totalPages", tkBo.getTotalPages());
			request.setAttribute("totalElements", tkBo.getTotalElements());
			
			// Lấy tổng tiền kiếm được hôm nay
			request.setAttribute("totalAmount", tkBo.getTongThuNhapHomNay());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Trả về view
		request.setAttribute("page", page);

		RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/homnay.jsp");
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
