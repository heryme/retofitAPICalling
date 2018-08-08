package roomdb.com.myapplication.rest;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roomdb.com.myapplication.R;
import roomdb.com.myapplication.utils.DialogUtil;

/**
 * Created by admin on 2/25/2015.
 */
public class ApiRequest<T> {

    private static final String TAG = "ApiRequest";
    private ProgressDialog mProgressDialog;
    private Activity activity;
    private boolean isShowProgressDialog;
    private ApiResponseInterface apiResponseInterface;

    public ApiRequest(final Activity activity, final T objectType, final int type, boolean isShowDialog, ApiResponseInterface objInterface) {
        this.activity = activity;
        this.isShowProgressDialog = isShowDialog;
        this.apiResponseInterface = objInterface;
        showProgress();
        Call<T> call = (Call<T>) objectType;
        call.enqueue(new Callback<T>() {
                         @Override
                         public void onResponse(Call<T> call, Response<T> response) {
                             dismissProgress();
                             Log.e(TAG, call.request().url().toString());
                             Log.e(TAG, "isSuccessful: " + response.isSuccessful());
                             if (response.isSuccessful()) {
                                 apiResponseInterface.getApiResponse(new ApiResponseManager<>(response.body(), type, ""));
                             } else  {
                                 Toast.makeText(activity,response.message(),Toast.LENGTH_SHORT);

                                 ApiError error = ErrorUtils.parseError(response);
                                 Log.e(TAG, "MESSAGE==>>>>>" + error.message());
                                 apiResponseInterface.getApiResponse(new ApiResponseManager<>(error, -1, error.message()));
                             }
                         }

                         @Override
                         public void onFailure(Call<T> call, Throwable error) {
                             dismissProgress();
                             Log.e(TAG, "onFailure");
                             Log.e(TAG, "onFailure error: " + error.getMessage());
                             if (error instanceof NetworkErrorException) {
                                 Toast.makeText(activity, activity.getResources().getString(R.string.toast_no_internet),Toast.LENGTH_SHORT);
                             } else if (error instanceof TimeoutException) {
                                 Toast.makeText(activity, activity.getResources().getString(R.string.toast_time_out), Toast.LENGTH_SHORT);
                             } else if (error instanceof SocketTimeoutException) {
                                 Toast.makeText(activity, activity.getResources().getString(R.string.toast_try_after_sometimes),Toast.LENGTH_SHORT);
                             } else if (error instanceof ParseException) {
                                 Toast.makeText(activity, activity.getResources().getString(R.string.toast_something_wrong), Toast.LENGTH_SHORT);
                             } else if (error instanceof ConnectException) {
                                 Toast.makeText(activity, activity.getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT);
                             } else if (error instanceof IOException) {
                                 Toast.makeText(activity, activity.getResources().getString(R.string.toast_no_internet),Toast.LENGTH_SHORT);
                             }
                         }
                     }
        );
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    private void showProgress() {
        if (isShowProgressDialog) {
           mProgressDialog = DialogUtil.getProgressDialog(activity);
        }
    }

    private void dismissProgress() {
        if (isShowProgressDialog) {
            DialogUtil.dismissDialog(activity, mProgressDialog);
        }
    }
}