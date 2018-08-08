package roomdb.com.myapplication.rest;

/**
 * Created by admin on 5/12/2016.
 */
public class ApiResponseManager<T> {

    public T response;
    public int type;
    public String message;

    public ApiResponseManager(T response, int type, String message) {
        this.response = response;
        this.type = type;
        this.message = message;
    }
}