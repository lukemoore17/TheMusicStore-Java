<%@ page import="util.AppData" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Register</title>

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
        <form id="register" method="post" action="RegisterServlet" role="form">
            <h5 class="form-group">Register for an Account</h5>
            <!-- PRINT REGISTRATION ERROR -->
            <%
                if(request.getAttribute("msg") != null) {
                    String msg = AppData.CreateAlert(request.getAttribute("msg").toString(), "success");
                    out.print(msg);
                }
            %>

            <div class="form-group">
                <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First name"
                       data-validation="length alphanumeric" data-validation-length="min2"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last name"
                       data-validation="length alphanumeric" data-validation-length="min2"
                       data-validation-error-msg="Not long enough" />
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="Email address"
                       data-validation="email"
                       data-validation-error-msg="Invalid email address" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="Choose a username"
                       data-validation="length alphanumeric" data-validation-length="4-15"
                       data-validation-error-msg="Username must be alphanumeric (4-15 chars)" />
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="Choose a secure password"
                       data-sanitize="escape" data-validation="length strength" data-validation-length="min6" data-validation-strength="2"
                       data-validation-error-msg="Password not strong enough" />
                <small class="text-muted">At least 6 characters</small><br />
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="Confirm password"
                       data-sanitize="escape" data-validation="confirmation" data-validation-confirm="password"
                       data-validation-error-msg="Passwords do not match" />
            </div>
            <div align="center">
                <input type="submit" id="submit" name="submit" class="btn btn-primary" value="Register" />
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
        form : '#register',
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
