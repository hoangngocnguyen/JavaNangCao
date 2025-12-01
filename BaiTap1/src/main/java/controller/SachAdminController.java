package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Servlet implementation class SachAdminController
 */

@MultipartConfig(
		// Tùy chọn: Định nghĩa kích thước file tối đa, vị trí lưu tạm, v.v.
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet("/SachAdminController")
public class SachAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SachAdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Nội dung client gửi lên
		if (request.getContentLength() <= 0) {// Chay lan dau chua co du lieu
			RequestDispatcher rd = request.getRequestDispatcher("Buoi3/upfile.jsp");
			rd.forward(request, response);
		} else {

			response.setContentType("text/html;charset=UTF-8");

			// 1. Lấy đường dẫn thư mục "files" (giống như logic cũ)
//			String uploadPath = request.getServletContext().getRealPath("") + File.separator + "files";
			String uploadPath = request.getServletContext().getRealPath("/test-image");
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
			}

			// Xử lý lần đầu (chưa có dữ liệu)
			// Với API mới, không cần kiểm tra request.getContentLength() <= 0.
			// Nếu request không phải là multipart, request.getParts() sẽ báo lỗi.

			try {
				// 2. Lấy tất cả các phần (Part) gửi lên, bao gồm file và các trường form
				Collection<Part> parts = request.getParts();

				for (Part part : parts) {
					String partName = part.getName(); // Lấy tên của trường form/file

					// 3. Kiểm tra xem Part đó là Field (Control) hay File
					if (part.getSubmittedFileName() == null) {
						// Đây là một trường form thông thường (Control)

						// Đọc giá trị của trường form
						String fieldValue = request.getParameter(partName); // Dùng request.getParameter() là cách dễ
																			// nhất

						if ("txtmasach".equals(partName)) {
							response.getWriter().println("Mã sách: " + fieldValue);
						} else if ("txttensach".equals(partName)) {
							response.getWriter().println("Tên sách: " + fieldValue);
						} else if ("txttacgia".equals(partName)) {
							response.getWriter().println("Tác giả: " + fieldValue);
						} else if ("txtsoluong".equals(partName)) {
							response.getWriter().println("Số lượng: " + fieldValue);
						} else if ("txtxoa".equals(partName)) {
							// Thực hiện test xóa một file
							String dirUrl = request.getServletContext().getRealPath("/test-image");
							File deleteFile = new File(dirUrl + "//Gemini_Generated_Image_lp4omglp4omglp4o.png");
							deleteFile.delete();
							System.out.println("Xóa thành công");
						}
					} else {
						// Đây là một File được gửi lên (có tên file)
						String fileName = part.getSubmittedFileName(); // Lấy tên file gốc

						if (fileName != null && !fileName.isEmpty()) {

							// Lưu file vào thư mục đã định nghĩa
							String filePath = uploadPath + File.separator + fileName;

							// Lưu file bằng phương thức write() của Part
							part.write(filePath);

							response.getWriter().println("UPLOAD THÀNH CÔNG...!");
							response.getWriter().println("Đường dẫn lưu file là: " + uploadPath);
						}
					}
				}
			} catch (Exception e) {
				response.getWriter().println("Lỗi Upload: " + e.getMessage());
				e.printStackTrace();
			}
		}

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
