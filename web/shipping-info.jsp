<%@ page import="util.AppData" %>
<%@ page import="dao.AddressDAO" %>
<%@ page import="entities.Address" %>

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

    int userId = (int) session.getAttribute(AppData.USERID);
    List<Address> addresses = (List<Address>) AddressDAO.getAllUserAddresses(userId);
    if (addresses.size() < 1) {
        response.sendRedirect("address.jsp?r=shipping-info.jsp");
        return;
    }
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Shipping Info</title>

    <!-- IMPORTED STYLES -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

    <!-- CUSTOM STYLES -->
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
</head>

<body class="container-fluid">

<%@ include file="header.jsp" %>

<div id="main" class="row">
    <div id="welcome" class="col-md-12" style="text-align:center;">
        <!-- PRINT MESSAGE -->
        <%
            if(request.getAttribute("msg") != null) {
                String msg = AppData.CreateAlert(request.getAttribute("msg").toString(), "danger");
                out.print(msg);
            }
        %>
    </div>
    <div id="addresses" class="col-md-12" style="text-align:center;">
        <form id="shippinginfo" method="post" role="form" action="ShippingServlet">
            <div id="shipAddress" class="form-group">
                <h5>Please select a shipping address</h5>
                <br />
                <div data-toggle="buttons">
                    <%
                        for (Address a : addresses) {
                    %>
                    <label class="btn btn-outline-primary">
                        <input id="<%=a.getAddressId()%>" name="shipaddress" type="radio" value="<%=a.getAddressId()%>"
                               autocomplete="off" style="opacity:0;width:0px;height:0px;"
                               data-validation="required" data-validation-error-msg="Select an option" />

                        <%=a.getFirstName() + " " + a.getLastName()%>
                        <br />
                        <%=a.getAddressLine1() + " " + a.getAddressLine2()%>
                        <br />
                        <%=a.getCity() + ", " + a.getState() + " " + a.getZip()%>
                        <br />
                    </label>
                    <%
                        }
                    %>
                </div>
            </div>
            <label>
                <input type="checkbox" name="isBillSame" id="isBillSame" checked="checked" />
                Billing address same as shipping
            </label>
            <br />
            <br />
            <div id="billAddress" class="form-group">
                <h5>Please select a billing address</h5>
                <br />
                <div data-toggle="buttons">
                    <%
                        for (Address a : addresses) {
                    %>
                    <label class="btn btn-outline-primary">
                        <input id="<%=a.getAddressId()%>" name="billaddress" type="radio" value="<%=a.getAddressId()%>"
                               autocomplete="off" style="opacity:0;width:0px;height:0px;" />

                        <%=a.getFirstName() + " " + a.getLastName()%>
                        <br />
                        <%=a.getAddressLine1() + " " + a.getAddressLine2()%>
                        <br />
                        <%=a.getCity() + ", " + a.getState() + " " + a.getZip()%>
                        <br />
                    </label>
                    <%
                        }
                    %>
                </div>
            </div>
            <br />
            <input type="submit" id="submitUserAddress" name="submitUserAddress" class="btn btn-primary" value="Continue Checkout" />
        </form>
        <br />
        <a href="address.jsp?r=shipping-info.jsp">Or create a new address...</a>
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
<script>
    $(document).ready(function () {
        $("#billAddress").hide();
        $('#isBillSame').click(function () {
            if ($(this).is(":checked")) {
                $("#billAddress").hide(200);
                $("input[name='billaddress']").attr({
                    "data-validation" : "",
                    "data-validation-error-msg" : ""
                });
            } else {
                $("#billAddress").show(300);
                $("input[name='billaddress']").attr({
                    "data-validation" : "required",
                    "data-validation-error-msg" : "Select an option"
                });
            }
        });
    });
</script>
<script>
    $.validate({
        lang: 'en',
        form : '#shippinginfo',
        errorMessagePosition : 'inline',
        errorMessageClass : 'errorMsg'
    });
</script>
<!-- END: LOCAL SCRIPTS -->
</body>
</html>
