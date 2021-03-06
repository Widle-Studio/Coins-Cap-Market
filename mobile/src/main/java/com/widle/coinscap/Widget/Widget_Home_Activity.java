package com.widle.coinscap.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Parcelable;
import android.widget.ListView;
import android.widget.RemoteViews;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.Adapter.Favourite_Adapter;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Fragment.Favourite_Fragment;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.Fav_Coins;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

/**
 * Implementation of App Widget functionality.
 */
public class Widget_Home_Activity extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {


            Intent svcIntent = new Intent(context, MyWidgetService.class);
            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.widget__home__activity);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                widget.setRemoteAdapter(R.id.listView_widget, svcIntent);
            else
                widget.setRemoteAdapter(appWidgetId, R.id.listView_widget, svcIntent);


            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant fun   ctionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

