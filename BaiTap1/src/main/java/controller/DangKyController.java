package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.KhachHang.KhachHangBo;
import modal.KhachHang.KhachHangDao;

/**
 * Servlet implementation class DangKyController
 */
@WebServlet("/DangKy")
public class DangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangKyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy username, password, confirmPassword
		String username = request.getParameter("username");
		String nameInput = request.getParameter("nameInput");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		KhachHangBo khBo = new KhachHangBo();

		if (username != null && nameInput != null && password != null && confirmPassword != null && email != null) {
			// Kiểm tra tên đăng nhập nếu tồn tại
			try {
				if (khBo.ktTenDangNhap(username)) {
					request.setAttribute("errorUsername", "User name đã tồn tại!");

					// Cho quay lại
					RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangky.jsp");
					rd.forward(request, response);

					return;
				}
				
				
				if (khBo.ktEmail(email)) {
					request.setAttribute("errorEmail", "Email đã tồn tại!");

					// Cho quay lại
					RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangky.jsp");
					rd.forward(request, response);

					return;
				}

				// Kiểm tra mật khẩu nhập lại trước khi save user
				if (!password.equals(confirmPassword)) {
					request.setAttribute("errorPassword", "Mật khẩu nhập lại không đúng!");

					// Cho quay lại
					RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangky.jsp");
					rd.forward(request, response);

					return;
				}
				

				try {
					khBo.dangky(username, password, nameInput, email);

					// Quay về trang đăng nhập với tài khoản mật khẩu đã đăng ký
					request.setAttribute("username", username);
					request.setAttribute("password", password);

					// Cho quay lại
					RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangnhap.jsp");
					rd.forward(request, response);

				} catch (RuntimeException e) {
					request.setAttribute("errorRegister", e.getMessage());

					// Cho quay lại
					RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangky.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Cho quay lại
		RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangky.jsp");
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
