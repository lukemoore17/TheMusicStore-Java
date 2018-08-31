package servlets;

import entities.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "RemoveFromCartServlet", description = "Handles user request for remove from cart", urlPatterns = { "/RemoveFromCartServlet" })
public class RemoveFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int albumId = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        List<Album> cart = (List<Album>) session.getAttribute("cart");

        for (Iterator<Album> i = cart.iterator(); i.hasNext(); ) {
            Album a = i.next();
            if (a.getAlbumId() == albumId) {
                i.remove();
            }
        }

        if (cart.size() == 0) {
            session.removeAttribute("cart");
        } else {
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("cart.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
