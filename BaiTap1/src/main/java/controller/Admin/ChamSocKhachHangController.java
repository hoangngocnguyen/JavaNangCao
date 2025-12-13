package controller.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.KhachHang.KhachHang;
import modal.LichSuMuaHang.LichSuMuaHangDao;
import modal.ThongKe.ThongKeBo;

import java.io.IOException;

/**
 * Servlet implementation class ChamSocKhachHangController
 */
@WebServlet("/ChamSocKhachHang")
public class ChamSocKhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChamSocKhachHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Trang này chỉ admin vào được
		KhachHang kh = (KhachHang) session.getAttribute("ss");
		if (kh == null) {
			session.setAttribute("pagePrev", "/ChamSocKhachHang");
			response.sendRedirect("/DangNhap");

			return;
		}

		if (!"admin".equals(kh.getTendn())) {
			// Nếu là user thì về trang chủ
			response.sendRedirect("/TrangChu");

			return;
		}
		
		// Lấy danh sách khách hàng top ra
		ThongKeBo tkBo = new ThongKeBo();
		try {
			request.setAttribute("dsKhachHang", tkBo.getTopKhachHang(5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		LichSuMuaHangDao lsDao = new LichSuMuaHangDao();
		// Khi click xem chi tiết đơn hàng khách đã mua
		String makhParam = request.getParameter("makh");
		if (makhParam != null) {
			int makh = Integer.parseInt(makhParam);
			
			try {
				// Trả về lịch sử mua hàng cho trang ChamSocKH_LSMuaHang
				request.setAttribute("dsLichSu", lsDao.getLichSuMuaHangThangHienTai(makh));
				RequestDispatcher rd = request.getRequestDispatcher("Buoi3/ChamSocKH_LSMuaHang.jsp");
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("Buoi3/chamsockhachhang.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
