package HoangController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import HoangModal.Hoang_SinhVien;
import HoangModal.Hoang_SinhVienBo;

/**
 * Servlet implementation class Hoang_SinhVienController
 */
@WebServlet("/Hoang_SinhVienController")
public class Hoang_SinhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hoang_SinhVienController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Hoang_SinhVienBo svBo = new Hoang_SinhVienBo();

		String search = request.getParameter("search");
		ArrayList<Hoang_SinhVien> ds = new ArrayList<Hoang_SinhVien>();
		try {
			// Nếu có search thì tiến hành tìm kiếm trả về
			if (search != null) {
				ds = svBo.findSinhVienBySearch(search);
				request.setAttribute("search", search);

			} else {
				// Trả về trang lần đầu với đầy đủ danh sách
				ds = svBo.getAllSinhVien();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("ds", ds);

		RequestDispatcher rd = request.getRequestDispatcher("sinhvien.jsp");
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
