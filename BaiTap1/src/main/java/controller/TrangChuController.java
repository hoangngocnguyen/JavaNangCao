package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.LoaiBo;
import modal.SachBo;

import java.io.IOException;

/**
 * Servlet implementation class TrangChuController
 */
@WebServlet("/TrangChuController")
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
		
		String maLoai = request.getParameter("maLoai");
      	String search = request.getParameter("search");
      	
		try {
			request.setAttribute("dsLoai", loaiBo.getLoai());
			request.setAttribute("dsSach", sachBo.getSach());
			
			if (maLoai != null) {
	      		try {
					request.setAttribute("dsSach", sachBo.getSach(maLoai));
				} catch (Exception e) {
					e.printStackTrace();
				}      
	      	}
	      	
	      	if (search != null) {
	      		try {
					request.setAttribute("dsSach", sachBo.timSach(search));
				} catch (Exception e) {
					e.printStackTrace();
				}
	      	}
		} catch (Exception e) {
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
