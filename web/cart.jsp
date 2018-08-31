<%@ page import="util.AppData" %>
<%@ page import="dao.AlbumsDAO" %>
<%@ page import="entities.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    List<Album> cart = new ArrayList<Album>();
    if (session.getAttribute("cart") != null) {
        cart = (List<Album>) session.getAttribute("cart");
    }
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>View Cart</title>

    <!-- IMPORTED STYLES -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

    <!-- CUSTOM STYLES -->
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
</head>

<body class="container-fluid">

<%@ include file="header.jsp" %>

<div id="main">
    <div id="cart" class="row">
        <%
            if (cart.size() > 0) {
                // PRINT TABLE
        %>
        <div class="col-md-12" style="max-width:1200px;margin:100px auto;">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Album</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        double sum = 0;
                        for (Album album : cart) {
                            sum = sum + album.getPrice();
                    %>
                    <tr>
                        <td>
                            <div class="row">
                                <div class="col-sm-3">
                                    <img class="img-fluid rounded" src="<%=album.getImageLink()%>" />
                                </div>
                                <div class="col-sm-9">
                                    <h5><%=album.getAlbumName()%></h5>
                                    <p><%=album.getArtist()%></p>
                                </div>
                            </div>
                        </td>
                        <td>
                            <p>&dollar;<%=album.getPrice()%></p>
                        </td>
                        <td>
                            <form method="post" action="RemoveFromCartServlet">
                                <input type="hidden" name="id" value="<%=album.getAlbumId()%>" />
                                <button type="submit" class="btn btn-danger">
                                    <i class='fas fa-trash-alt'></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td style="text-align:right;"><p>Total:</p></td>
                        <td><p><strong>&dollar;<%=sum%></strong></p></td>
                        <td></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td>
                            <a href="shop.jsp" class="btn btn-outline-danger">Continue Shopping</a>
                        </td>
                        <td></td>
                        <td style="text-align:right;">
                            <a href="pre-checkout.jsp" class="btn btn-outline-success">Checkout</a>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <%
            } else {
                // DISPLAY THAT NO ITEMS ARE IN CART
        %>
        <div class="col-md-12" style="max-width:500px;margin:100px auto;text-align:center;">
            <div class="alert alert-info">
                Looks like there's nothing in your cart!  :(
            </div>
            <br />
            <a href="shop.jsp" class="btn btn-outline-danger">Continue Shopping</a>
        </div>
        <%
            }
        %>
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
