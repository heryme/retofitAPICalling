package roomdb.com.myapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {

    public static ProgressDialog getProgressDialog(Context context) {
        MyCustomProgressDialog myCustomProgressDialog = new MyCustomProgressDialog(context);
        myCustomProgressDialog.setIndeterminate(true);
        myCustomProgressDialog.setCancelable(false);
        myCustomProgressDialog.show();
        return myCustomProgressDialog;

    }

    public static void dismissDialog(Context context,ProgressDialog mProgressDialog) {
        try {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e){

        }

    }

}
