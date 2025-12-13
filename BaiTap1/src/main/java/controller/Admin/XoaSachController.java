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
import modal.Loai.LoaiDao;
import modal.Sach.Sach;
import modal.Sach.SachDao;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@MultipartConfig(
		// Tùy chọn: Định nghĩa kích thước file tối đa, vị trí lưu tạm, v.v.
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet("/XoaSach")
public class XoaSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XoaSachController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		// Trang này chỉ admin vào được
		KhachHang kh = (KhachHang) session.getAttribute("ss");
		if (kh == null) {
			session.setAttribute("pagePrev", "/QuanLyDonHang");
			response.sendRedirect("/DangNhap");

			return;
		}

		if (!"admin".equals(kh.getTendn())) {
			// Nếu là user thì về trang chủ
			response.sendRedirect("/TrangChu");

			return;
		}
		
		
		// Chay lan dau chua co du lieu
		// Gửi về danh sách loại để gắn vào form + sách
		SachDao sDao = new SachDao();

		String maSach = request.getParameter("maSach");
		if (maSach != null) {
			try {

				// Xóa sách từ masach
				Sach delSach = sDao.timSachTuMa(maSach);
				sDao.xoaSach(maSach);

				// Xóa trong thư mục ảo
				// Thực hiện test xóa một file
				String dirUrl = request.getServletContext().getRealPath("");
				File deleteFile = new File(dirUrl + delSach.getAnh());
				deleteFile.delete();
				System.out.println("Xóa thành công");
			} catch (Exception e) {
				// Về trang quản lý sách và trả lỗi
				response.sendRedirect("/QuanLySach?errorDelete=true");
			}
		}
		response.sendRedirect("/QuanLySach");
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
