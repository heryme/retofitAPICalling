package roomdb.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import roomdb.com.myapplication.model.Response;
import roomdb.com.myapplication.rest.ApiInitialize;
import roomdb.com.myapplication.rest.ApiInterface;
import roomdb.com.myapplication.rest.ApiRequest;
import roomdb.com.myapplication.rest.ApiResponseInterface;
import roomdb.com.myapplication.rest.ApiResponseManager;

public class MainActivity extends AppCompatActivity implements ApiResponseInterface {
    ApiInterface apiInterface;
    String model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiInitialize.initialize().create(ApiInterface.class);

      /*  new ApiRequest<>(this, apiInterface.userLogin("0", "dhaval.p.arvaan@gmail.com", "123123"),
                1, true,
                MainActivity.this);*/

        new ApiRequest<>(this, apiInterface.doGetUserList("2"), 1, true, this);

    }


    @Override
    public void getApiResponse(ApiResponseManager apiResponse) {
        //Response get from Response body
        if (apiResponse.type == 0) {
            ResponseBody responseBody = (ResponseBody) apiResponse.response;
            try {
                String response = responseBody.string();
                Log.d("TAg", "Response---> " + response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (apiResponse.type == 1) {
            //Response get from specific model
            Response response = (Response) apiResponse.response;
            Log.d("TAG", "Page-->" + response.getPage());
        }
    }
}
