package org.azamov.learnjakarta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/convert")
public class NumberConverterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String number = request.getParameter("number");
        String fromBaseStr = request.getParameter("fromBase");
        String toBaseStr = request.getParameter("toBase");

        String result = null;
        String error = null;

        try {
            int fromBase = Integer.parseInt(fromBaseStr);
            int toBase = Integer.parseInt(toBaseStr);

            if (!isValidForBase(number, fromBase)) {
                error = "Kiritilgan son '" + number + "' " + fromBase + "-sanoq sistemasi uchun noto'g'ri!";
            } else {
                long decimal = Long.parseLong(number.toUpperCase(), fromBase);
                result = Long.toString(decimal, toBase).toUpperCase();
            }

        } catch (NumberFormatException e) {
            error = "Noto'g'ri son kiritildi: " + number;
        }

        request.setAttribute("number", number);
        request.setAttribute("fromBase", fromBaseStr);
        request.setAttribute("toBase", toBaseStr);
        request.setAttribute("result", result);
        request.setAttribute("error", error);

        request.getRequestDispatcher("/converter.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/converter.jsp").forward(request, response);
    }

    private boolean isValidForBase(String number, int base) {
        if (number == null || number.isEmpty()) return false;
        String upper = number.toUpperCase();
        for (char c : upper.toCharArray()) {
            int digit;
            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (c >= 'A' && c <= 'F') {
                digit = c - 'A' + 10;
            } else {
                return false;
            }
            if (digit >= base) return false;
        }
        return true;
    }
}
