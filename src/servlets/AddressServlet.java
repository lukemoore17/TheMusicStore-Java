package servlets;

import dao.AddressDAO;
import util.AppData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddressServlet", description = "Handles user request for creating new address", urlPatterns = { "/AddressServlet" })
public class AddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        String pageLocation = "address.jsp";

        HttpSession session = request.getSession();

        if (firstname.trim().length() > 1 && lastname.trim().length() > 1 && address1.trim().length() > 1
                && city.trim().length() > 1 && state.trim().length() > 1 && zip.trim().length() > 1) {

            int userId = (int) session.getAttribute(AppData.USERID);
            boolean isCreated = AddressDAO.createNewUserAddress(userId, firstname, lastname, address1, address2, city, state, zip);

            if (isCreated) {
                System.out.println("Success!");
                pageLocation = "shipping-info.jsp";
                request.setAttribute("msg", "Address added successfully");
            } else {
                request.setAttribute("msg", "Some fields were filled out incorrectly. Please try again!");
            }
        }

        if (session.getAttribute("cart") == null) {
            pageLocation = "account.jsp";
        }

        request.getRequestDispatcher(pageLocation).include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
