package servlets;

import dao.CustomerLoginDAO;
import entities.Customer;
import util.AppData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", description = "Handles user request for login", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String redirect =  request.getParameter("redirect");
        String pageLocation = "login.jsp";

        if(username.trim().length() > 0  && password.trim().length() > 0) {
            //CustomerLoginDAO loginDAO = new CustomerLoginDAO();
            boolean isValid = CustomerLoginDAO.login(username, password);
            if (isValid) {
                Customer user = CustomerLoginDAO.getUserInfoByUsername(username);
                if (user == null) {
                    request.setAttribute("msg", "Error requesting user info, IT will look into this...");
                    return;
                }
                HttpSession session = request.getSession();
                System.out.println("Login success!");
                //request.setAttribute("username", username);
                session.setAttribute(AppData.USERNAME, username);
                session.setAttribute(AppData.USERID, user.getCustomerId());
                session.setAttribute(AppData.FIRSTNAME, user.getFirstName());
                session.setAttribute(AppData.LASTNAME, user.getLastName());
                session.setAttribute(AppData.LOGGEDIN, "true");
                //AppData.CreateAlert("Welcome back, " + App)
                request.setAttribute("msg", "Login success!");
                if (redirect.equals("pre-checkout.jsp")) {
                    pageLocation = "pre-checkout.jsp";
                } else {
                    pageLocation = "index.jsp";
                }
                response.sendRedirect(pageLocation);
                return;
            } else {
                request.setAttribute("msg", "Wrong Username or Password, Try again!!!");
            }
        } else {
            request.setAttribute("msg", "Please enter username and password...");
        }

        request.getRequestDispatcher(pageLocation).include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
