package controller.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modal.KhachHang.KhachHang;
import modal.Loai.LoaiBo;
import modal.Loai.LoaiDao;
import modal.Sach.SachDao;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Servlet implementation class ThemSach
 */

@MultipartConfig(
		// Tùy chọn: Định nghĩa kích thước file tối đa, vị trí lưu tạm, v.v.
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet("/SuaLoai")
public class SuaLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuaLoaiController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Trang này chỉ admin vào được
		KhachHang kh = (KhachHang) session.getAttribute("ss");
		if (kh == null) {
			session.setAttribute("page", "/QuanLyDonHang");
			response.sendRedirect("/DangNhap");

			return;
		}

		if (!"admin".equals(kh.getTendn())) {
			// Về trang đăng nhập, ghi lại trang hiện tại
			session.setAttribute("page", "/QuanLyDonHang");
			response.sendRedirect("/DangNhap");

			return;
		}
		
		LoaiBo lBo = new LoaiBo();
		LoaiDao lDao = new LoaiDao();
		
		// Nhận maLoai, tenLoai rồi tạo mới lưu vào DB
		String maLoai = request.getParameter("maLoai");
		String tenLoai = request.getParameter("tenLoai");
		
		if (maLoai != null && tenLoai != null) {
			// Tiến hành thêm vào DB
			try {
				lDao.suaLoai(maLoai, tenLoai);
				
				
				// Quay lại trang quản lý loại
				response.sendRedirect("/QuanLyLoai");
				return;
				
			} catch (Exception e) {
				// Tiến hành forward về form báo lỗi
				request.setAttribute("errorMessage", e.getMessage());
			}
		}
		
		// Lấy loại ra
		try {
			request.setAttribute("loai", lDao.timLoaiTuMa(maLoai));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Trả về form thêm
		RequestDispatcher rd = request.getRequestDispatcher("Buoi3/sualoai.jsp");
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
