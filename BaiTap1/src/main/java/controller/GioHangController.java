package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.GioHangBo;
import modal.Sach;
import modal.SachBo;

import java.io.IOException;

/**
 * Servlet implementation class GioHangController
 */
@WebServlet("/GioHangController")
public class GioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GioHangController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maSach = request.getParameter("ms");
		
		System.out.println("masach" + maSach);
		HttpSession session = request.getSession();

		if (session.getAttribute("gh") == null) {
			session.setAttribute("gh", new GioHangBo());
		}

		SachBo sbo = new SachBo();

		GioHangBo gio = (GioHangBo) session.getAttribute("gh");

		if (maSach != null) {
			// Thêm một sản phẩm vào giỏ hàng
			Sach sach;
			try {
				sach = sbo.timSachTheoMa(maSach);
				
				if (sach != null) {
					gio.them(maSach, sach.getTenSach(), sach.getTacGia(), 1, sach.getGia(), sach.getAnh());

					session.setAttribute("gh", gio);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Cập nhật, xóa
		String update = request.getParameter("update");
		String slParam = request.getParameter("quantity");
		String remove = request.getParameter("remove");

		if (update != null && update.equals("true") && slParam != null) {
			int sl = Integer.parseInt(slParam);
			gio.capNhat(maSach, sl);
		}

		if (remove != null && remove.equals("true")) {
			gio.xoa(maSach);

		}

		
		// Xóa hết giỏ hàng:
		String deletedAll = request.getParameter("deleted");
		if (deletedAll != null && deletedAll.equals("all")) {
			gio.xoaTatCa();
		}
		
		
		// XỬ LÝ List Checkbox: xóa - checkout
		// Xóa sản phẩm đã chọn trong checkbox
		String[] listCheckbox = request.getParameterValues("checkboxDelete");
		String action = request.getParameter("action");
		
		if ("remove".equals(action)) {
			if (listCheckbox != null) {
				for (String ma : listCheckbox) {
					gio.xoa(ma);
				}
			}
		} else if ("checkout".equals(action)) {
			// CHuyển đến HoaDonController xử lý
			request.setAttribute("dsMaSach", listCheckbox);
			RequestDispatcher rd = request.getRequestDispatcher("/HoaDonController");
			rd.forward(request, response);
			return;
		}
		
		session.setAttribute("gh", gio);
		response.sendRedirect("TrangChuController?q=cart");
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
