package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
	private static final String SECRET_KEY = "6Lchr-grAAAAAA4M2sLdM9EFcp5FKhkzXeREeoe1";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify = verifyRecaptcha(gRecaptchaResponse);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (verify) {
            out.println("<h3>Captcha hợp lệ! Xử lý đăng nhập ở đây.</h3>");
        } else {
            out.println("<h3>Captcha sai hoặc chưa chọn!</h3>");
        }
        
        
        
        RequestDispatcher rd = request.getRequestDispatcher("tc.jsp");
        rd.forward(request, response);
    }

    private boolean verifyRecaptcha(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            return false;
        }

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes(StandardCharsets.UTF_8));

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String jsonResponse = in.lines().collect(Collectors.joining());
        in.close();

        // Chỉ cần kiểm tra chứa "success": true
        return jsonResponse.contains("\"success\": true");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
