package org.azamov.learnjakarta;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/calc")
public class CalculatorPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("""
                <html>
                <head>
                    <title>Calculator</title>
                </head>
                <body>
                    <h2>Oddiy Calculator</h2>
                    <form method="post">
                        <label>Birinchi son:</label>
                        <input type="number" name="firstNumber"><br><br>
                
                        <label>Ikkinchi son:</label>
                        <input type="number" name="secondNumber"><br><br>
                
                        <label>Amal:</label>
                        <select name="operation">
                            <option value="+">Qo‘shish (+)</option>
                            <option value="-">Ayirish (-)</option>
                            <option value="*">Ko‘paytirish (*)</option>
                            <option value="/">Bo‘lish (/)</option>
                        </select><br><br>
                
                        <button type="submit">Hisoblash</button>
                    </form>
                </body>
                </html>
                """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            int firstNumber = Integer.parseInt(req.getParameter("firstNumber"));
            int secondNumber = Integer.parseInt(req.getParameter("secondNumber"));
            String operation = req.getParameter("operation");

            int result = 0;

            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        out.println("<h3>Xatolik: 0 ga bo‘lib bo‘lmaydi!</h3>");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
                default:
                    out.println("<h3>Xatolik: noto‘g‘ri amal tanlandi!</h3>");
                    return;
            }

            out.println("""
                    <html>
                    <head><title>Natija</title></head>
                    <body>
                    """);

            out.println("<h2>Hisoblash natijasi</h2>");
            out.println("<p>" + firstNumber + " " + operation + " " + secondNumber + " = " + result + "</p>");
            out.println("<a href='calculator'>Orqaga qaytish</a>");

            out.println("""
                    </body>
                    </html>
                    """);

        } catch (Exception e) {
            out.println("<h3>Xatolik: input noto‘g‘ri kiritildi!</h3>");
            out.println("<a href='calculator'>Qaytish</a>");
        }
    }
}