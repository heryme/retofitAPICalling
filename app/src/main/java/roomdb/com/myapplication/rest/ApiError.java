package roomdb.com.myapplication.rest;

/**
 * Created by admin on 5/11/2016.
 */
public class ApiError {
    private int statusCode;
    private String message;

    public ApiError(int code, String messages) {
        statusCode=code;
        message=messages;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}