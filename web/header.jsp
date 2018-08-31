<%@ page import="util.AppData" %>
<%@ page import="java.util.List" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="index.jsp">The Music Store</a>
    <%
        int cartCount = 0;
        if (session.getAttribute("cart") != null) {
            List cartSum = (List) session.getAttribute("cart");
            cartCount = cartSum.size();
        }
    %>
    <a class="btn btn-outline-primary" href="cart.jsp">
        <% if (cartCount > 0) { %>
        <span class="badge badge-primary"><%=cartCount%></span>&nbsp;
        <% } %>
        <span class="hidden-sm hidden-xs">View Cart&nbsp;</span>
        <span class="fa fa-shopping-cart" aria-hidden="true"></span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li id="navShop" class="nav-item">
                <a class="nav-link" href="shop.jsp">Shop <span class="sr-only">(current)</span></a>
            </li>
            <%--<li id="navContact" class="nav-item">--%>
                <%--<a class="nav-link" href="#">Contact Us</a>--%>
            <%--</li>--%>
        </ul>
        <%
            if (session.getAttribute(AppData.LOGGEDIN) == "true")
            {
        %>
        <a class="btn btn-link" href="account.jsp">My Account</a>
        <form method="post" action="LogoutServlet" role="form">
            <button id="logout" name="logout" class="btn btn-link btn-sm" type="submit" style="margin:0 5px 0 0;">Log Out</button>
        </form>
        <%
            } else {
        %>
        <a class="btn" href="login.jsp">Log In</a>
        <%
            }
        %>

    </div>

</nav>