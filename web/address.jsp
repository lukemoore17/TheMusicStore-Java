<%@ page import="util.AppData" %>

<%
    if (session.getAttribute(AppData.LOGGEDIN) != "true") {
        out.print("You must be logged in to create an address! Redirecting you...");
        response.setHeader("Refresh", "3;url=login.jsp");
        return;
    }
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Create an Address</title>

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
        <form id="shipping" method="post" action="AddressServlet" role="form">
            <h5 class="form-group">Enter New Address Info</h5>
            <!-- PRINT REGISTRATION ERROR -->
            <%
                if(request.getAttribute("msg") != null) {
                    String msg = AppData.CreateAlert(request.getAttribute("msg").toString(), "danger");
                    out.print(msg);
                }
            %>

            <div class="form-group">
                <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First name"
                       data-sanitize="escape" data-validation="length alphanumeric" data-validation-length="min2"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last name"
                       data-sanitize="escape" data-validation="length alphanumeric" data-validation-length="min2"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="address1" name="address1" placeholder="Address 1"
                       data-sanitize="escape" data-validation="length" data-validation-length="min2"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="address2" name="address2" placeholder="Address 2 (optional)"
                       data-sanitize="escape" data-validation="length" data-validation-length="min2" data-validation-optional="true"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="city" name="city" placeholder="City"
                       data-sanitize="escape" data-validation="length" data-validation-length="min2"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="state" name="state" placeholder="State"
                       data-sanitize="escape" data-validation="length" data-validation-length="min2"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="zip" name="zip" placeholder="Zip Code"
                       data-sanitize="escape" data-validation="length number" data-validation-length="5"
                       data-validation-error-msg="Must be 5 chars, numbers only" />
            </div>
            <div align="center">
                <input type="submit" id="submit" name="submit" class="btn btn-outline-primary" value="Continue" />
            </div>

        </form>
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
        form : '#shipping',
        modules : 'sanitize, security',
        errorMessagePosition : 'inline',
        errorMessageClass : 'errorMsg'
    });
</script>
<!-- END: LOCAL SCRIPTS -->
</body>
</html>
