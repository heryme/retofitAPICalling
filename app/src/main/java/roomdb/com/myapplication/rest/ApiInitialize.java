package roomdb.com.myapplication.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 5/12/2016.
 */
public class ApiInitialize {

//    public static String MAIN_URl = "http://192.168.1.45/Dropbox/happening-media/api/"; //local
//    public static String MAIN_URl = "http://54.201.245.47/happening-media/api/"; // This is main url
    //public static String MAIN_URl = "http://139.59.24.105/artcompass/public/api/"; // This is temporary URL
    public static String MAIN_URl = "https://reqres.in";

    private static Retrofit retrofit = null;

    public static Retrofit initialize() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MAIN_URl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}