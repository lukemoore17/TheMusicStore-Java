package servlets;

import dao.CustomerLoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", description = "Handles user request for registration", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String pageLocation = "register.jsp";

        if (firstname.trim().length() > 1 && lastname.trim().length() > 1 && email.trim().length() > 0
                && username.trim().length() > 3 && password.trim().length() > 5) {
            boolean isCreated = CustomerLoginDAO.createNewUser(firstname, lastname, username, password, email);
            if (isCreated) {
                System.out.println("Login success!");
                pageLocation = "registersuccess.jsp";
                request.setAttribute("msg", "Your account has been created successfully! Please log in to continue.");
            } else {
                request.setAttribute("msg", "There was an error while creating your account. Please try again!");
            }
        } else {
            request.setAttribute("msg", "Some fields were filled out incorrectly. Please try again!");
        }

        request.getRequestDispatcher(pageLocation).include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
