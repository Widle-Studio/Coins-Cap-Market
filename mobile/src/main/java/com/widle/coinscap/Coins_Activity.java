package com.widle.coinscap;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.widle.coinscap.Fragment.Coins_Fragment;
import com.widle.coinscap.Fragment.Event_Fragment;
import com.widle.coinscap.Fragment.Favourite_Fragment;
import com.widle.coinscap.Fragment.News_Fragment;
import com.widle.coinscap.Fragment.Podcast_Fragment;
import com.widle.coinscap.Utils.Model.Advertisement;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Coins_Activity extends AppCompatActivity {

    private ImageView iv_setting;

    private TextView tv_app_name;

    BottomNavigationView navigation;

    Fragment fragment;

    PopupMenu popupmenu;

    ArrayList<Advertisement> mAdvertiseArrayList = new ArrayList<>();

    private static final String APP_ID = "ca-app-pub-7122988079171533~7969850323";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins);


        if (utils.isNetworkAvailable(Coins_Activity.this)) {
            new apiGet_Advertise().execute();
        } else {
            utils.showAlertMessage(Coins_Activity.this, getString(R.string.err_no_internet));
        }


        init();

//        new apiGet_Coins().execute();

        loadFragment(new Coins_Fragment());
    }


    public void init() {

        tv_app_name = (TextView) findViewById(R.id.tv_app_name);

        iv_setting = (ImageView) findViewById(R.id.iv_setting);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//         attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());
//
//
//        iv_setting.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                popupmenu = new PopupMenu(Coins_Activity.this, view);
//                PopMenuDisplay();
//                return false;
//            }
//        });


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    tv_app_name.setText("Home");
                    fragment = new Coins_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new Favourite_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    tv_app_name.setText("Favourite");
                    fragment = new News_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_podcast:
                    fragment = new Podcast_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_event:
                    fragment = new Event_Fragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };


    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------

    public class apiGet_Advertise extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                ParsedResponse p = Service.apiGetAdvertise(Coins_Activity.this);
                error = p.error;
                if (!error) {
                    mAdvertiseArrayList = (ArrayList<Advertisement>) p.o;
                } else {
                    msg = (String) p.o;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                error = true;
                msg = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!error) {
            } else {
                utils.showAlertMessage(Coins_Activity.this, msg);
            }
        }
    }


    public void PopMenuDisplay() {
        popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Intent intent = new Intent(Coins_Activity.this, Acknowledgement_Activity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                        return true;

                    case R.id.help:
                        Intent i1 = new Intent(Coins_Activity.this, Help_Activity.class);
                        startActivity(i1);
                        return true;

                    case R.id.acknowledgement:
                        Intent i2 = new Intent(Coins_Activity.this, Acknowledgement1_Activity.class);
                        startActivity(i2);
                        return true;

                    case R.id.privacy:
                        Intent i3 = new Intent(Coins_Activity.this, Acknowledgement_Activity.class);
                        i3.putExtra("type", "4");
                        startActivity(i3);
                        return true;

                    case R.id.terms:
                        Intent i4 = new Intent(Coins_Activity.this, Acknowledgement_Activity.class);
                        i4.putExtra("type", "5");
                        startActivity(i4);
                        return true;

                    case R.id.cookie:
                        Intent i5 = new Intent(Coins_Activity.this, Acknowledgement_Activity.class);
                        i5.putExtra("type", "6");
                        startActivity(i5);
                        return true;

                    case R.id.rate:
                        String url = "https://play.google.com/store/apps/details?id=com.imperialinfosys.coinscap";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        return true;

                    case R.id.setting:
                        Intent i6 = new Intent(Coins_Activity.this, Setting_Activity.class);
                        startActivity(i6);
                        return true;

                }
                return false;
            }
        });
        popupmenu.show();
    }

    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------


    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        if (utils.isNetworkAvailable(Coins_Activity.this)) {
//            final Dialog dialog = new Dialog(this);
//            dialog.setContentView(R.layout.custom_dialog);
//
//
//            TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
//            ImageView iv_advertise = dialog.findViewById(R.id.iv_advertise);
//            iv_advertise.setVisibility(View.VISIBLE);
//            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
//            Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);
//
//
//            tv_msg.setText(getString(R.string.error_quit));
//            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            String image = "https://widle.studio/coinscapmarket/API/ad-image/";
//
//            Glide.with(this)
//                    .load(image + mAdvertiseArrayList.get(0).ad_image)
//                    .into(iv_advertise);
//
//
//            iv_advertise.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String url = mAdvertiseArrayList.get(0).ad_url;
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                }
//            });
//
//// Action for custom dialog ok button click
//
//            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//            dialog.show();

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);

// Custom Android Allert Dialog Title

            TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
            ImageView iv_advertise = dialog.findViewById(R.id.iv_advertise);
            iv_advertise.setVisibility(View.GONE);
            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
            Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);

            tv_msg.setText(getString(R.string.error_quit));
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            dialog.show();
        } else {

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);

// Custom Android Allert Dialog Title

            TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
            ImageView iv_advertise = dialog.findViewById(R.id.iv_advertise);
            iv_advertise.setVisibility(View.GONE);
            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
            Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);

            tv_msg.setText(getString(R.string.error_quit));
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            dialog.show();

        }
    }

}