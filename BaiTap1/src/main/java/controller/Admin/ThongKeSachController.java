package controller.Admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.ThongKe.ThongKeBo;

/**
 * Servlet implementation class ThongKeSach
 */
@WebServlet("/ThongKeSach")
public class ThongKeSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThongKeSachController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Lấy trang
		String pageParam = request.getParameter("page");
		int page = 1;
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}
		
		// Lấy search (tên loại)
		String search = request.getParameter("search");
		// Thống kê kho sách
		ThongKeBo tkBo = new ThongKeBo();
		try {
			request.setAttribute("dsThongKeKhoSach", tkBo.getThongKeKhoSach(search, page));
			request.setAttribute("totalPages", tkBo.getTotalPages_ThongKeKhoSach(search));

			request.setAttribute("thongKeTongQuan", tkBo.getThongKeTongQuan());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("currentPage", page);
		request.setAttribute("search", search);
		RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/dashboard.jsp");
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
