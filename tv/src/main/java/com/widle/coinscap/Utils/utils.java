package com.widle.coinscap.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.widle.coinscap.R;


/**
 * Created by imperial-android on 8/8/17.
 */
public class utils {

    public static Context context;
    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showAlertMessage(Context context, String message) {
        if (message != null && !message.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(context.getString(R.string.app_name))
                    .setMessage(message).setPositiveButton(
                    context.getString(R.string.ok), null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public static boolean hasPermission(final Context context, final String permission) {
        return PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(
                permission, context.getPackageName());
    }

}
