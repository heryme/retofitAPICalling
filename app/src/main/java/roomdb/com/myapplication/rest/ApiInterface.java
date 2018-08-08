package roomdb.com.myapplication.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import roomdb.com.myapplication.model.Response;


public interface ApiInterface {


    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("user-login")
    Call<ResponseBody> userLogin(@Field("login_type") String login_type, @Field("login_key") String login_key, @Field("password") String password);

    /*@FormUrlEncoded
    @POST("/api/users?")
    Call<Response> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/

    @GET("/api/users?")
    Call<Response> doGetUserList(@Query("page") String page);


}