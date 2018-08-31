<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<title>The Music Store | Login</title>
	
	<!-- IMPORTED STYLES -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
	
	<!-- CUSTOM STYLES -->
	<link rel="stylesheet" type="text/css" href="styles/styles.css" />
</head>

<body class="container-fluid">

	<%@ include file="header.jsp" %>
	
	<div id="main" class="row">
		<div id="login" class="col-md-12">
	        <form method="post" action="LoginServlet" role="form">
            	<h5 class="form-group">Please Log In</h5>
                <!-- PRINT LOGIN ERROR -->
                <% if(request.getAttribute("msg") != null) { %>
                <p style="color: red">
                    <%= request.getAttribute("msg") %>
                </p>
                <% } %>
                <% if(request.getAttribute("msg2") != null) { %>
                <p style="color: green;">
                    <%= request.getAttribute("msg2") %>
                </p>
                <% } %>
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" />
                </div>
                <div align="center">
                    <input type="hidden" id="redirect" name="redirect" value="<%=request.getParameter("r")%>" />
                    <input type="submit" id="submit" name="submit" class="btn btn-primary" value="Log In" />
                </div>
                <br />
                <div class="form-group" style="text-align:center">
                    <a href="register.jsp">Not registered? Create an account here</a>
                </div>
            </form>
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