package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.KhachHang.KhachHang;
import modal.KhachHang.KhachHangBo;

/**
 * Servlet implementation class /DangNhap
 */
@WebServlet("/DangNhap")
public class DangNhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SECRET_KEY = "6Lchr-grAAAAAA4M2sLdM9EFcp5FKhkzXeREeoe1";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangNhapController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");

		if (loginAttempts == null) {
			loginAttempts = 0;
		}

		// Xử lý captcha
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = verifyRecaptcha(gRecaptchaResponse);
		response.setContentType("text/html;charset=UTF-8");

		// Nếu captcha sai -> trả về ngay
		if (loginAttempts >= 3 && !verify) {
			request.setAttribute("errCaptcha", "Vui lòng tick xác thực captcha");
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangnhap.jsp");
			rd.forward(request, response);
			
			return;
		}

		// Kiểm tra login
		KhachHangBo khBo = new KhachHangBo();
		if (username != null && password != null) {
			try {
				KhachHang kh = khBo.ktDangNhap(username, password);
				if ( kh != null) {
					session.removeAttribute("loginAttempts");
					
					// Lưu khách hàng vào session
					session.setAttribute("ss", kh);
					request.setAttribute("username", username);
					request.setAttribute("password", password);
					
					if (kh.getTendn().equals("admin")) {
						response.sendRedirect("/Admin");
						return;
					}
					
					// Quay lại trang trước đó nếu có
					String page = (String) session.getAttribute("pagePrev");
					if (page != null) {
						response.sendRedirect(page);
						return;
					}
					
					response.sendRedirect("/TrangChu");
					
					return;
				} else {
					loginAttempts++;
					session.setAttribute("loginAttempts", loginAttempts);
					
					request.setAttribute("username", username);
					request.setAttribute("password", password);
					request.setAttribute("err", "Sai tên đăng nhập hoặc mật khẩu");
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("Buoi3/dangnhap.jsp");
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

	private boolean verifyRecaptcha(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
			return false;
		}

		String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;

		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.getOutputStream().write(params.getBytes(StandardCharsets.UTF_8));

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String jsonResponse = in.lines().collect(Collectors.joining());
		in.close();

		// Chỉ cần kiểm tra chứa "success": true
		return jsonResponse.contains("\"success\": true");
	}

}
