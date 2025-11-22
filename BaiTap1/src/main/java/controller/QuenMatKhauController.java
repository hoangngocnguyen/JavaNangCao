package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.KhachHang.KhachHang;
import modal.KhachHang.KhachHangBo;
import modal.KhachHang.KhachHangDao;
import utils.MD5Hasher;
import utils.PasswordGenerator;
import utils.TaskExecutor;

import java.io.IOException;

/**
 * Servlet implementation class QuenMatKhauController
 */
@WebServlet("/QuenMatKhau")
public class QuenMatKhauController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuenMatKhauController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");

		// Tìm kiếm khách hàng qua user name
		KhachHangDao khDao = new KhachHangDao();
		KhachHangBo khBo = new KhachHangBo();

		KhachHang kh;
		try {
			kh = khDao.findKhachHang(username);

			// 1. Nhận yêu cầu: nhập tên tài khoản -> tìm khách hàng nếu có (không có thì
			// trả về lỗi)
			if (kh == null) {
				// Trả về lỗi
				request.setAttribute("error", "Tên đăng nhập không tồn tại");
				RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/quenmatkhau.jsp");
				rd.forward(request, response);

				return;
			}

			// 2. Xử lý tạo mật khẩu mới - lưu vào DB
			String randomPass = PasswordGenerator.generateRandomPassword();
			String password = MD5Hasher.hashMD5(randomPass);

			khDao.doiMatKhau(kh.getMakh(), password);

			// 3. Thực hiện gửi mail song song ở nền
			// Định nghĩa tác vụ Gửi Email (Runnable)
			Runnable mailTask = () -> {
				try {
					// Gửi email cho người dùng thông báo đặt hàng thành công
					// Lấy thông tin từ hóa đơn + chi tiết hóa đơn

					khBo.guiEmailMatKhauMoi(kh, randomPass);
				} catch (Exception e) {
					System.err.println("Lỗi gửi email bất đồng bộ: " + e.getMessage());
				}
			};

			// 3. Gửi tác vụ vào Thread Pool và giải phóng thread chính
			TaskExecutor.submitMailTask(mailTask);

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 4. In ra thông báo mật khẩu mới đã được gửi về email
		request.setAttribute("success", "Lấy mật khẩu mới thành công, vui lòng kiểm tra email của bạn");
		RequestDispatcher rd = request.getRequestDispatcher("/Buoi3/quenmatkhau.jsp");
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
