package com.widle.coinscap;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created by imperial-web on 4/11/2018.
 */

public abstract class LeanbackActivity extends FragmentActivity {
    @Override
    public boolean onSearchRequested() {
        startActivity(new Intent(this, SearchActivity.class));
        return true;
    }
}
