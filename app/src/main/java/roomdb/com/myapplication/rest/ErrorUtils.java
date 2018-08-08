package roomdb.com.myapplication.rest;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by admin on 5/11/2016.
 */
public class ErrorUtils {
    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter = ApiInitialize.initialize().responseBodyConverter(ApiError.class, new Annotation[0]);
        ApiError error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError(-1,"");
        }
        return error;
    }
}