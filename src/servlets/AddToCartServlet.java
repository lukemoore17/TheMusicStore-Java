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
import java.util.List;

@WebServlet(name = "AddToCartServlet", description = "Handles user request for add to cart", urlPatterns = { "/AddToCartServlet" })
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Album album = new Album();
        album.setAlbumId(Integer.parseInt(request.getParameter("id")));
        album.setAlbumName(request.getParameter("name"));
        album.setArtist(request.getParameter("artist"));
        album.setYear(request.getParameter("year"));
        album.setPrice(Double.parseDouble(request.getParameter("price")));
        album.setImageLink(request.getParameter("image"));

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") ==  null) {
            List<Album> cart = new ArrayList<Album>();
            cart.add(album);
            session.setAttribute("cart", cart);
        } else {
            List<Album> cart = (List<Album>) session.getAttribute("cart");
            cart.add(album);
            session.setAttribute("cart", cart);
        }

        response.sendRedirect("shop.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
