package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.KhachHang.KhachHang;
import modal.LichSuMuaHang.LichSuMuaHangBo;

/**
 * Servlet implementation class LichSuMuaHang
 */
@WebServlet("/LichSuMuaHang")
public class LichSuMuaHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LichSuMuaHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		KhachHang kh = (KhachHang) session.getAttribute("ss");
		
		// Nếu chưa đăng nhập về đăng nhập
		if (kh == null) {
			response.sendRedirect("/DangNhap");
			return;
		}
		
		// Trả về lịch sử mua hàng từ DB
		LichSuMuaHangBo lsBo = new LichSuMuaHangBo();
		
		// Lấy trang
		String pageParam = request.getParameter("page");
		int page = 1;
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}
		
		
		try {
			request.setAttribute("dsLichSuMuaHang", lsBo.getLichSuMuaHang(kh.getMakh(), page));
			
			request.setAttribute("totalPages", lsBo.getTotalPages(kh.getMakh()));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("currentPage", page);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/lichsumuahang.jsp");
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
