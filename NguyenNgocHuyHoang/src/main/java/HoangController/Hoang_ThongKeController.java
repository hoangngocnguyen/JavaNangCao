package HoangController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import HoangModal.Hoang_SinhVienBo;
import HoangModal.Hoang_ThongKeDTB;

/**
 * Servlet implementation class Hoang_ThongKeController
 */
@WebServlet("/Hoang_ThongKeController")
public class Hoang_ThongKeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hoang_ThongKeController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Trả về trang thống kê
		Hoang_SinhVienBo svBo = new Hoang_SinhVienBo();
		ArrayList<Hoang_ThongKeDTB> ds = new ArrayList<Hoang_ThongKeDTB>();
		try {
			 ds = svBo.thongKeDTBTheoLop();
			 
			request.setAttribute("ds", ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("dangthongke", "1");
		RequestDispatcher rd = request.getRequestDispatcher("thongke.jsp");
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
