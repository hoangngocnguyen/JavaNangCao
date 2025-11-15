package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class mayTinhController
 */
@WebServlet("/mayTinh")
public class mayTinhController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mayTinhController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String temp_a = request.getParameter("txta");
		String temp_b = request.getParameter("txtb");
		double ketqua = 0.0;
		int a = 0, b = 0;

		if (temp_a != null && temp_b != null) {

			a = Integer.parseInt(temp_a);
			b = Integer.parseInt(temp_b);

			String loi = "";

			if (request.getParameter("butc") != null) {
				ketqua = a + b;
			}
			if (request.getParameter("butt") != null) {
				ketqua = a - b;
			}
			if (request.getParameter("butn") != null) {
				ketqua = a * b;
			}
			if (request.getParameter("butchia") != null) {

				try {
					ketqua = (double) a / b;
				} catch (Exception e) {
					loi = "Không thể chia với số 0";
				}
			}
		}
		request.setAttribute("aa", a);
		request.setAttribute("bb", b);
		request.setAttribute("kq", ketqua);
		
		RequestDispatcher rd = request.getRequestDispatcher("maytinh.jsp");
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
