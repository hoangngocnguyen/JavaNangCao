package controller.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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
@WebServlet("/XoaLoai")
public class XoaLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XoaLoaiController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoaiDao lDao = new LoaiDao();

		// Nhận maLoai, tenLoai rồi tạo mới lưu vào DB
		String maLoai = request.getParameter("maLoai");

		// Tiến hành thêm vào DB
		try {
			lDao.xoaLoai(maLoai);
			// Quay lại trang quản lý loại
			response.sendRedirect("/QuanLyLoai");
			return;

		} catch (Exception e) {
			// Tiến hành forward về form báo lỗi
			request.setAttribute("errorMessage", e.getMessage());
		}

		// Trả về form thêm
		response.sendRedirect("/QuanLyLoai");
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
