package com.widle.coinscap.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.widle.coinscap.R;


/**
 * Created by imperial-android on 8/8/17.
 */
public class utils {

    LayoutInflater inflater;


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


    public void setupDialog(final Context context, String proof){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_proof);

        ImageView iv_title = (dialog).findViewById(R.id.iv_title);
        Glide.with(context)
                .load(proof)
                .into(iv_title);

        dialog.show();

    }




}
