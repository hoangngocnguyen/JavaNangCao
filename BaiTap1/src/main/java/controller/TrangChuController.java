package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.Loai.LoaiBo;
import modal.Sach.Sach;
import modal.Sach.SachBo;

/**	
 * Servlet implementation class TrangChuController
 */
@WebServlet("/TrangChu")
public class TrangChuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrangChuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Báo unicode
      	request.setCharacterEncoding("utf-8");
      	response.setCharacterEncoding("utf-8");
      	
      	
		// Trả về được danh sách loại, hàng
		LoaiBo loaiBo = new LoaiBo();
		
		// Danh sách sản phẩm
		SachBo sachBo = new SachBo();
		
		// Phân trang lọc sản phẩm:
		// + maLoai, search, bấm nút phân trang (thẻ link)
		
		String maLoai = request.getParameter("maLoai");
      	String search = request.getParameter("search");
      	String pageParam = request.getParameter("page");
      	
//      	System.out.println(maLoai + "s-" + search + "p" + pageParam);
      	
      	// Nếu lần đầu vào trang cho page = 1;
      	int page = 1;
      	if (pageParam != null) {
      		page = Integer.parseInt(pageParam); 
      	}
      	
      	// Mặc định size để 8
      	int pageSize = 8;
      	
      	ArrayList<Sach> dsSach = new ArrayList<Sach>();
      	// Tiến hành lọc
      	try {
      		dsSach = sachBo.locSachPhanTrang(maLoai, search, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
      	// Trả về các tham số
      	request.setAttribute("maLoai", maLoai);
      	request.setAttribute("search", search);
      	request.setAttribute("page", page);
      	
      	
      	
      	int totalPages = 0;
		try {
			totalPages = sachBo.getTotalPages(maLoai, search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
      	request.setAttribute("totalPages", totalPages); // TỔNG SỐ TRANG
      	request.setAttribute("pageIndexHienTai", page); // Đổi tên biến cho rõ ràng
      	
      	request.setAttribute("dsSach", dsSach);
      	try {
			request.setAttribute("dsLoai", loaiBo.getLoai());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Buoi3/trangchu.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
