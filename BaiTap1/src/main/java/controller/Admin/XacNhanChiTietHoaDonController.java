package controller.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.ChiTietHoaDon.ChiTietHoaDonBo;
import modal.HoaDon.HoaDonDao;
import modal.XacNhanMuaHang.XacNhanMuaHangBo;

import java.io.IOException;

/**
 * Servlet implementation class XacNhanChiTietDonHang
 */
@WebServlet("/XacNhanChiTietHoaDon")
public class XacNhanChiTietHoaDonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XacNhanChiTietHoaDonController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy trang
		String pageParam = request.getParameter("page");
		int page = 1;
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}

		ChiTietHoaDonBo ctBo = new ChiTietHoaDonBo();
		XacNhanMuaHangBo xnBo = new XacNhanMuaHangBo();

		// Lấy mã hđ
		String mahoadon = request.getParameter("mahoadon");
		
		if (mahoadon != null) {
			int maHD = Integer.parseInt(mahoadon);
			try {
				request.setAttribute("dsChiTietHoaDon", ctBo.getChiTietHoaDon(maHD, page));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		// Xác nhận chi tiết hóa đơn
		String action = request.getParameter("action");
		String maChiTietHD = request.getParameter("cthd");

		if ("confirm_cthd".equals(action) && maChiTietHD != null) {
			int maCTHD = Integer.parseInt(maChiTietHD);
			try {
				
				// Lấy mã hóa đơn ra trước khi xác nhận
				HoaDonDao hdDao = new HoaDonDao();
				int maHD = hdDao.getMaHoaDonByMaChiTietHD(maCTHD);
				
				boolean isSuccess = xnBo.xacNhanDaMuaChiTiet(maCTHD);
				
				if (isSuccess) {
					// Nếu thành công, quay về trang chi tiết hóa đơn
					response.sendRedirect("/XacNhanChiTietHoaDon?mahoadon=" + maHD);

					return;
				} else {
					// Nếu thất bại, báo lỗi
					String mess = "Xác nhận đơn hàng " + maChiTietHD + " thất bại!";
					request.setAttribute("errorMessage", mess);

					RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/xacnhanchitiethoadon.jsp");
					rd.forward(request, response);

					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/xacnhanchitiethoadon.jsp");
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
