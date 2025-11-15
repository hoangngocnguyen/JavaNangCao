package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class CheckCaptcha extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String input = request.getParameter("captchaInput");
        String saved = (String) request.getSession().getAttribute("captcha");

        if (saved != null && saved.equals(input)) {
            out.println("<h3 style='color:green'>✅ Captcha chính xác!</h3>");
        } else {
            out.println("<h3 style='color:red'>❌ Sai mã captcha!</h3>");
        }
    }
}
