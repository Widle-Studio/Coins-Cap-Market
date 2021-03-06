package com.widle.coinscap.Widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import com.widle.coinscap.Utils.Model.coin;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class


MyWidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new MyWidgetViewsFactory(this.getApplicationContext(), intent));
    }
}