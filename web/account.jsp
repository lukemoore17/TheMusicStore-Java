<%@ page import="util.AppData" %>
<%@ page import="entities.CustomerOrder" %>
<%@ page import="dao.OrderDAO" %>
<%
    if (session.getAttribute(AppData.LOGGEDIN) != "true")
    {
        out.print("You do not have permission to view this page! Redirecting you...");
        response.setHeader("Refresh", "3;url=index.jsp");
        return;
    }
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Account Home</title>

    <!-- IMPORTED STYLES -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

    <!-- CUSTOM STYLES -->
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
</head>

<body class="container-fluid">

<%@ include file="header.jsp" %>

<div id="main">
    <div id="account" class="row">
        <div id="accountWelcome" class="col-md-12">
            <div style="max-width:500px;text-align:center;margin:0 auto;">
            <%
                String msg = AppData.CreateAlert("Welcome back to your account, " + session.getAttribute(AppData.FIRSTNAME), "info");
                out.print(msg);

                if(request.getAttribute("msg") != null) {
                    String msg2 = AppData.CreateAlert(request.getAttribute("msg").toString(), "success");
                    out.print(msg2);
                }
            %>
            </div>
        </div>
        <div class="col-md-4">
            <form id="changePword" method="post" action="ChangePasswordServlet" role="form">
                <h5 class="form-group">Change your password</h5>
                <!-- PRINT ERROR MESSAGE -->
                <% if(request.getAttribute("msg2") != null) { %>
                <p style="color: green;">
                    <%= request.getAttribute("msg2") %>
                </p>
                <% } %>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your new password"
                           data-sanitize="escape" data-validation="length strength" data-validation-length="min6" data-validation-strength="2"
                           data-validation-error-msg="Password not strong enough" />
                    <small class="text-muted">At least 6 characters</small><br />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="Confirm your password"
                           data-sanitize="escape" data-validation="confirmation" data-validation-confirm="password"
                           data-validation-error-msg="Passwords do not match" />
                </div>
                <div align="center">
                    <input type="submit" id="submit" name="submit" class="btn btn-primary" value="Submit" />
                </div>
            </form>
        </div>
        <div id="orders" class="col-md-8">
            <div class="table-responsive" style="max-width:500px;text-align:center;margin:0 auto;">
                <h3>Your Orders</h3>
                <table class="table table-bordered">
                    <tr>
                        <th width="25%" style="text-align:center;">Order ID</th>
                        <th width="40%" style="text-align:center;">Date</th>
                        <th width="35"></th>
                    </tr>
                    <%
                        List<CustomerOrder> orders = OrderDAO.getAllUserOrders((int)session.getAttribute(AppData.USERID));
                        if (orders.size() > 0) {
                            for (CustomerOrder o : orders) {
                    %>
                    <tr>
                        <td><%=o.getOrderId()%></td>
                        <td><%=o.getDate()%></td>
                        <td><a href="order.jsp?r=<%=o.getOrderId()%>&d=<%=o.getDate()%>" class="btn btn-outline-success btn-sm">See Details</a></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="3" style="text-align:center;">
                            <h5 style="color:darkred;">You have no orders yet.</h5>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
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
<script>
    $.validate({
        lang: 'en',
        form : '#changePword',
        modules : 'sanitize, security',
        errorMessagePosition : 'inline',
        errorMessageClass : 'errorMsg',

        onModulesLoaded : function() {
            var optionalConfig = {
                fontSize: '12pt',
                padding: '4px',
                bad : 'Poor',
                weak : 'Weak',
                good : 'Good',
                strong : 'Strong'
            };

            $('input[name="password"]').displayPasswordStrength(optionalConfig);
        }
    });
</script>
<!-- END: LOCAL SCRIPTS -->
</body>
</html>
