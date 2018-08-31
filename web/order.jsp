<%@ page import="util.AppData" %>
<%@ page import="entities.CustomerOrder" %>
<%@ page import="dao.OrderDAO" %>
<%@ page import="entities.CustomerOrderItem" %>
<%@ page import="entities.Album" %>
<%
    if (session.getAttribute(AppData.LOGGEDIN) != "true")
    {
        out.print("You do not have permission to view this page! Redirecting you...");
        response.setHeader("Refresh", "3;url=index.jsp");
        return;
    }

    int orderId = 0;
    String date = "";
    CustomerOrder order = null;
    List<CustomerOrderItem> orderItems = null;

    if (request.getParameter("r") != null) {
        orderId = Integer.parseInt(request.getParameter("r"));
        date = request.getParameter("d");
        orderItems = OrderDAO.getUserOrderItemsByOrderID(orderId);
    }
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Order Details</title>

    <!-- IMPORTED STYLES -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

    <!-- CUSTOM STYLES -->
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
</head>

<body class="container-fluid">

<%@ include file="header.jsp" %>

<div id="main" class="row">
    <div id="orderitems" class="col-md-12" style="text-align:center;">
        <h5>Items in your order place on <strong><%=date%></strong></h5>
        <small><a href="account.jsp">&larr;&nbsp;back to account</a></small>
        <br /><br />
        <div class="row">
            <%
                if (orderId > 0) {
                    if (orderItems.size() > 0) {
                        for (CustomerOrderItem item : orderItems) {
                            Album a = item.getAlbumByAlbumId();

            %>
            <div class="col-md-4">
                <div class="card">
                    <img class="card-img-top" src="<%=a.getImageLink()%>" />
                    <div class="card-block">
                        <h4 class="card-title"><%=a.getAlbumName()%></h4>
                        <h6 class="card-subtitle mb-2 text-muted"><%=a.getArtist()%> (<%=a.getYear()%>)</h6>
                        <p class="card-text">
                            <strong>&dollar;<%=a.getPrice()%></strong>
                        </p>
                    </div>
                    <div class="card-footer">
                        Quantity purchased: <strong><%=item.getQuantity()%></strong>
                    </div>
                </div>
            </div>
            <%
                        }
                    } else {
                        String msg = AppData.CreateAlert("Can't get the items in this order.", "danger");
                        out.print(msg);
                    }
                } else {
                    String msg = AppData.CreateAlert("No order was selected!", "danger");
                    out.print(msg);
                }
        %>
        </div>
    </div>
</div>

<%@ include file="footer.html" %>

<!-- BEGIN: IMPORTED SCRIPTS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!-- END: IMPORTED SCRIPTS -->

<!-- BEGIN: LOCAL SCRIPTS -->
<!-- END: LOCAL SCRIPTS -->
</body>
</html>