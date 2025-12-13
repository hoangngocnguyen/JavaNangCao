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
@WebServlet("/ThemSach")
public class ThemSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThemSachController() {
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
			session.setAttribute("pagePrev", "/QuanLyDonHang");
			response.sendRedirect("/DangNhap");

			return;
		}

		if (!"admin".equals(kh.getTendn())) {
			// Nếu là user thì về trang chủ
			response.sendRedirect("/TrangChu");

			return;
		}

		// Lấy danh sách loại
		LoaiDao lDao = new LoaiDao();

		// Nội dung client gửi lên
		if (request.getContentLength() > 0) {
			response.setContentType("text/html;charset=UTF-8");

			// 1. Lấy đường dẫn thư mục "files" (giống như logic cũ)
			String uploadPath = request.getServletContext().getRealPath("/image_sach");
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
			}

			try {
				// 2. Lấy tất cả các phần (Part) gửi lên, bao gồm file và các trường form
				Collection<Part> parts = request.getParts();
				String maLoai = "", maSach = "", tenSach = "", tacGia = "", anh = "";
				int soLuong = 0, soTap = 0, gia = 0;

				for (Part part : parts) {
					String partName = part.getName(); // Lấy tên của trường form/file

					// 3. Kiểm tra xem Part đó là Field (Control) hay File
					if (part.getSubmittedFileName() == null) {
						// Đây là một trường form thông thường (Control)

						// Đọc giá trị của trường form
						String fieldValue = request.getParameter(partName); // Dùng request.getParameter() là cách dễ
																			// nhất
						if ("maloai".equals(partName)) {
							maLoai = fieldValue;
						} else if ("txtmasach".equals(partName)) {
							maSach = fieldValue;
						} else if ("txttensach".equals(partName)) {
							tenSach = fieldValue;
						} else if ("txttacgia".equals(partName)) {
							tacGia = fieldValue;
						} else if ("txtsoluong".equals(partName)) {
							soLuong = Integer.parseInt(fieldValue);
						} else if ("txtsotap".equals(partName)) {
							soTap = Integer.parseInt(fieldValue);
						} else if ("txtgia".equals(partName)) {
							gia = Integer.parseInt(fieldValue);
						}
					} else {
						// Đây là một File được gửi lên (có tên file)
						String fileName = part.getSubmittedFileName(); // Lấy tên file gốc

						if (fileName != null && !fileName.isEmpty()) {
							String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
							// Đặt tên file trùng với mã sách

							String filePath = uploadPath + File.separator + maSach + fileExtension;

							// Lấy đường dẫn ảnh vào db
							anh = "image_sach/" + maSach + fileExtension;
							// Lưu file bằng phương thức write() của Part
							part.write(filePath);

							System.out.println("UPLOAD THÀNH CÔNG...!");
							System.out.println("Đường dẫn lưu file là: " + uploadPath);
						}
					}
				}

				// Tiến hành thêm vào DB
				System.out.println("maloai: " + maLoai + "-" + maSach);
				SachDao sDao = new SachDao();

				int rs = sDao.themSach(maSach, tenSach, soLuong, gia, maLoai, soTap, anh, tacGia);
				if (rs == 1) {
					System.out.println("Thêm sách thành công");

					// Chuyển về trang sách
					response.sendRedirect("/QuanLySach");
				} else {
					System.out.println("Thêm sách thất bại");
				}
				
				return;

			} catch (Exception e) {
				System.out.println("Lỗi Upload: " + e.getMessage());

				request.setAttribute("errorMessage", "Có lỗi khi thêm sách: Mã sách đã tồn tại!");
			}
		}

		try {
			request.setAttribute("dsLoai", lDao.getLoai());
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("Buoi3/themsach.jsp");
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
