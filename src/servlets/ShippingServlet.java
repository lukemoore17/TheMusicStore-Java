package servlets;

import util.AppData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ShippingServlet", description = "Handles user request for shipping info", urlPatterns = { "/ShippingServlet" })
public class ShippingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int shipAddressID = Integer.parseInt(request.getParameter("shipaddress"));
        boolean isBillSame = request.getParameter("isBillSame") != null;
        int billAddressID = (isBillSame) ? shipAddressID : Integer.parseInt(request.getParameter("billaddress"));
        String pageLocation = "shipping-info.jsp";

        HttpSession session = request.getSession();

        if (shipAddressID != 0 && billAddressID != 0) {
            session.setAttribute(AppData.SHIPADDRESSID, shipAddressID);
            session.setAttribute(AppData.BILLADDRESSID, billAddressID);
            pageLocation = "review.jsp";
            response.sendRedirect(pageLocation);
            return;
        } else {
            request.setAttribute("msg", "Please select a shipping and billing address!");
        }
        request.getRequestDispatcher(pageLocation).include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
