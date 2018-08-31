<%@ page import="util.AppData" %>
<%@ page import="entities.Album" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Address" %>
<%@ page import="dao.AddressDAO" %>

<%
    if (session.getAttribute("cart") == null) {
        out.print("You cannot checkout with 0 items in your cart! Redirecting you...");
        response.setHeader("Refresh", "3;url=shop.jsp");
        return;
    }
    if (session.getAttribute(AppData.LOGGEDIN) != "true") {
        response.sendRedirect("pre-checkout.jsp");
        return;
    }

    List<Album> cart = (List<Album>) session.getAttribute("cart");
    double sum = 0;
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Review Order</title>

    <!-- IMPORTED STYLES -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

    <!-- CUSTOM STYLES -->
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
</head>

<body class="container-fluid">

<%@ include file="header.jsp" %>

<div id="main" class="row">
    <%
        if (request.getAttribute("msg") != null)
        {
            String msg = AppData.CreateAlert(request.getAttribute("msg").toString(), "danger");
            out.print(msg);
        }
    %>
    <!-- PURCHASE SUMMARY -->
    <div id="ordersummary" class="col-md-12">
        <h2>Purchase Summary</h2>
        <div class="table-responsive">
            <table class="table table-bordered">
                <tr>
                    <th width="80%">Item Name</th>
                    <th width="20%">Price</th>
                </tr>
                <%
                    for (Album a : cart) {
                        sum = sum + a.getPrice();
                %>
                <tr>
                    <td><%=a.getAlbumName()%></td>
                    <td><%=a.getPrice()%></td>
                </tr>
                <%
                    }
                %>
                <tr class="table-footer">
                    <td align="right">Total</td>
                    <td align="right">&dollar;<%=sum%></td>
                </tr>
            </table>
        </div>
    </div>

    <!-- DETAILS -->
    <%
        int shipAddressID = (int) session.getAttribute(AppData.SHIPADDRESSID);
        int billAddressID = (int) session.getAttribute(AppData.BILLADDRESSID);
        Address ShipAddress = AddressDAO.getAddressByAddressID(shipAddressID);
        Address BillAddress = (billAddressID == shipAddressID) ? ShipAddress : AddressDAO.getAddressByAddressID(billAddressID);
    %>
    <div id="orderdetails" class="col-md-12">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Shipping Address</h4>
                    </div>
                    <div class="card-block">
                        <p class="card-text address">
                            <%=ShipAddress.getFirstName() + " " + ShipAddress.getLastName()%>
                            <br />
                            <%=ShipAddress.getAddressLine1() + " " + ShipAddress.getAddressLine2()%>
                            <br />
                            <%=ShipAddress.getCity() + ", " + ShipAddress.getState() + " " + ShipAddress.getZip()%>
                        </p>
                    </div>
                    <div class="card-footer">
                        <a href="shipping-info.jsp" class="float-right">change</a>
                    </div>
                </div>
            </div>
            <div id="confirmorder" class="col-md-8">
                <div class="alert alert-warning">
                    <p>
                        <b>
                            <%=session.getAttribute(AppData.FIRSTNAME)%>
                        </b>, you are about to purchase the items listed above. The total cost of the order to be charged is
                        <b>
                            &dollar;<%=sum%>
                        </b>. If you would like to confirm this transaction, please click 'Complete Purchase'.
                    </p>
                </div>
                <form method="post" role="form" action="ConfirmOrderServlet">
                    <div align="center">
                        <button class="btn btn-outline-success" type="submit" name="submit">Complete Purchase</button>
                    </div>
                </form>
            </div>
        </div>
        <br />
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Billing Address</h4>
                    </div>
                    <div class="card-block">
                        <p class="card-text address">
                            <%=BillAddress.getFirstName() + " " + BillAddress.getLastName()%>
                            <br />
                            <%=BillAddress.getAddressLine1() + " " + BillAddress.getAddressLine2()%>
                            <br />
                            <%=BillAddress.getCity() + ", " + BillAddress.getState() + " " + BillAddress.getZip()%>
                        </p>
                    </div>
                    <div class="card-footer">
                        <a href="shipping-info.jsp" class="float-right">change</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.html" %>

<!-- BEGIN: IMPORTED SCRIPTS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!-- END: IMPORTED SCRIPTS -->

<!-- BEGIN: LOCAL SCRIPTS -->
<!-- END: LOCAL SCRIPTS -->
</body>
</html>
