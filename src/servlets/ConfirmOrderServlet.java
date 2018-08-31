package servlets;

import dao.OrderDAO;
import entities.Album;
import org.hibernate.criterion.Order;
import util.AppData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ConfirmOrderServlet", description = "Handles user request for confirm order", urlPatterns = { "/ConfirmOrderServlet" })
public class ConfirmOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageLocation = "review.jsp";
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(AppData.USERID);
        int shipAddressId = (int) session.getAttribute(AppData.SHIPADDRESSID);
        int billAddressId = (int) session.getAttribute(AppData.BILLADDRESSID);
        List<Album> orderItems = (List<Album>) session.getAttribute("cart");

        boolean isOrderCreated = OrderDAO.createUserOrder(userId, shipAddressId, billAddressId, orderItems);

        if (isOrderCreated) {
            System.out.println("Order created success!");
            pageLocation = "shop.jsp";
            request.setAttribute("msg", "Thank you. Your order has been processed successfully. You may view your order history at any time on your <a href='account.jsp'>account page</a>.");
        } else {
            request.setAttribute("msg", "Sorry, there was an error while attempting to create your order.");
        }

        session.removeAttribute("cart");
        request.getRequestDispatcher(pageLocation).include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
