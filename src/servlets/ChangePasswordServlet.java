package servlets;

import dao.CustomerLoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", description = "Handles user request to change password", urlPatterns = { "/ChangePasswordServlet" })
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String pageLocation = "account.jsp";

        if (password.trim().length() > 0) {
            if (password.matches(confirmPassword)) {
                HttpSession session = request.getSession();
                int userID = (int) session.getAttribute("userID");
                CustomerLoginDAO.changePassword(userID, password);
                request.setAttribute("msg", "Password updated successfully!");
            } else {
                request.setAttribute("msg2", "Passwords do not match, Try again!!!");
            }
        } else {
            request.setAttribute("msg2", "Please enter a password!");
        }

        request.getRequestDispatcher(pageLocation).include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
