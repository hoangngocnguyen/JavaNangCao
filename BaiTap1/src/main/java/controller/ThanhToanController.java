package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.KhachHang.KhachHang;
import modal.LichSuMuaHang.LichSuMuaHang;
import modal.LichSuMuaHang.LichSuMuaHangBo;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ThanhToanController
 */
@WebServlet("/ThanhToan")
public class ThanhToanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThanhToanController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Nhận tham số là maChiTietHoaDon và lấy mã khách hàng từ session
		String maChiTietHD = request.getParameter("ma");

		HttpSession session = request.getSession();
		KhachHang kh = (KhachHang) session.getAttribute("ss");

		// Nếu chưa đăng nhập -> bắt đăng nhập
		if (kh == null) {
			// Ghi lại page hiện tại để sau khi login sẽ quay lại
			session.setAttribute("pagePrev", "/ThanhToan");
			response.sendRedirect("/DangNhap");
			return;
		}
		
		LichSuMuaHang chiTietHoaDon = null;
		// Lấy chi tiết đơn hàng ra
		if (maChiTietHD != null) {
			int maCTHD = Integer.parseInt(maChiTietHD);
			LichSuMuaHangBo lsBo = new LichSuMuaHangBo();

			try {
				chiTietHoaDon = lsBo.getChiTietHoaDon(kh.getMakh(), maCTHD);

				// Trả về cho view
				request.setAttribute("chiTietHoaDon", chiTietHoaDon);
				RequestDispatcher rd = request.getRequestDispatcher("Buoi3/thanhtoan.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Nếu không tìm thấy đơn hàng -> về trang lịch sử 
		if (chiTietHoaDon == null) {
			response.sendRedirect("/LichSuMuaHang");
		}

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
