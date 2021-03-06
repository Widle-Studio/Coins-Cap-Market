package com.widle.coinscap;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;

public class SearchActivity extends Activity {
    private static final String TAG = SearchActivity.class.getSimpleName();
    private SearchFragment mSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
     }

    @Override
    public boolean onSearchRequested() {
//        if (mFragment.hasResults()) {
//            startActivity(new Intent(this, SearchActivity.class));
        startActivity(new Intent(this, SearchActivity.class));

//        } else {
//            mFragment.startRecognition();
//        }
        return true;
    }

}
