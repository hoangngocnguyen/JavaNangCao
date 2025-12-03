package controller.Admin;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.KhachHang.KhachHang;
import modal.Loai.Loai;
import modal.Loai.LoaiBo;
import modal.Sach.Sach;
import modal.Sach.SachBo;
import modal.ThongKe.ThongKeBo;
import modal.XacNhanMuaHang.XacNhanMuaHangBo;

@WebServlet("/QuanLyLoai")
public class QuanLyLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuanLyLoaiController() {
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
		KhachHang kh = (KhachHang) session.getAttribute("ss");
		if (kh == null) {
			session.setAttribute("page", "/QuanLyLoai");
			response.sendRedirect("/DangNhap");

			return;
		}

		if (!"admin".equals(kh.getTendn())) {
			// Về trang đăng nhập, ghi lại trang hiện tại
			session.setAttribute("page", "/QuanLyLoai");
			response.sendRedirect("/DangNhap");

			return;
		}

		// Báo unicode
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// Trả về được danh sách loại, hàng
		LoaiBo loaiBo = new LoaiBo();

		// Phân trang lọc loại:
		// search, bấm nút phân trang (thẻ link)

		String search = request.getParameter("search");
		String pageParam = request.getParameter("page");


		// Nếu lần đầu vào trang cho page = 1;
		int page = 1;
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}

		ArrayList<Loai> dsLoai = new ArrayList<Loai>();
		// Tiến hành lọc
		try {
			dsLoai = loaiBo.getLoaiPhanTrang(search, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//// Xử lý hiển thị lỗi khi không xóa được
		String err = request.getParameter("errorDelete");
		if (err != null) {
			request.setAttribute(err, "Có lỗi khi xóa sách này");
		}

		// Trả về các tham số
		request.setAttribute("search", search);
		request.setAttribute("page", page);

		int totalPages = 0;
		try {
			totalPages = loaiBo.getTotalPages(search);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("totalPages", totalPages); // TỔNG SỐ TRANG
		request.setAttribute("pageIndexHienTai", page); // Đổi tên biến cho rõ ràng

		request.setAttribute("dsLoai", dsLoai);
		RequestDispatcher rd = request.getRequestDispatcher("Buoi3/quanlyloai.jsp");
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
