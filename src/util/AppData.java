package util;

public class AppData {
    public static final String USERID = "USERID";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";

    public static final String LOGGEDIN = "LOGGEDIN";
    public static final String LOGGEDINTIME = "LOGGEDINTIME";

    public static final String SHIPADDRESSID = "SHIPADDRESSID";
    public static final String BILLADDRESSID = "BILLADDRESSID";

    public static String CreateAlert(String message, String type) {
        return "<div class='alert alert-" + type + "'>" + message + "</div>";
    }
}
