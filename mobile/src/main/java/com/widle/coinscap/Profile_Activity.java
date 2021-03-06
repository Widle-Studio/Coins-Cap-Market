package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;

import java.io.IOException;

import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Profile_Activity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private ImageView iv_cancel, iv_in, iv_twitter, iv_fb, iv_gplus, iv_youtube;
//
    private TextView tv_setting, tv_app_about, tv_about, tv_help, tv_rate, tv_legal, tv_privacy, tv_acknowledgement, tv_terms, tv_cookie;

    private Button google_login, google_logout;

    private GoogleSignInOptions gso;

    private int RC_SIGN_IN = 100;

    // google plus
    private GoogleApiClient mGoogleApiClient;

    SharedPreferences preferences;

    private String device_id = "";

    private ProgressDialog mProgressDialog;

//    private LinearLayout ll_notification;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        device_id = preferences.getString(General.PREFS_Device_id, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init();
    }

    public void init(){

        iv_cancel = findViewById(R.id.iv_cancel);
        iv_in = findViewById(R.id.iv_in);
        iv_twitter = findViewById(R.id.iv_twitter);
        iv_fb = findViewById(R.id.iv_fb);
        iv_gplus = findViewById(R.id.iv_gplus);
        iv_youtube = findViewById(R.id.iv_youtube);
//        ll_notification  =findViewById(R.id.ll_notification);

        google_login = (Button) findViewById(R.id.google_login);
        google_logout = (Button) findViewById(R.id.google_logout);

        tv_setting = findViewById(R.id.tv_setting);
        tv_app_about = findViewById(R.id.tv_app_about);
        tv_about = findViewById(R.id.tv_about);
        tv_help = findViewById(R.id.tv_help);
        tv_rate = findViewById(R.id.tv_rate);
        tv_legal = findViewById(R.id.tv_legal);
        tv_privacy = findViewById(R.id.tv_privacy);
        tv_acknowledgement = findViewById(R.id.tv_acknowledgement);
        tv_terms = findViewById(R.id.tv_terms);
        tv_cookie = findViewById(R.id.tv_cookie);
//        tv_notification = findViewById(R.id.tv_notification);


        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");


        tv_setting.setTypeface(type2);
        tv_app_about.setTypeface(type1);
        tv_about.setTypeface(type1);
        tv_help.setTypeface(type1);
        tv_rate.setTypeface(type1);
        tv_legal.setTypeface(type1);
        tv_privacy.setTypeface(type1);
        tv_acknowledgement.setTypeface(type1);
        tv_terms.setTypeface(type1);
        tv_cookie.setTypeface(type1);
//        tv_notification.setTypeface(type1);

//
//        iv_back.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_rate.setOnClickListener(this);
        tv_privacy.setOnClickListener(this);
        tv_acknowledgement.setOnClickListener(this);
        tv_terms.setOnClickListener(this);
        tv_cookie.setOnClickListener(this);
//        tv_notification.setOnClickListener(this);
//        ll_notification.setOnClickListener(this);

        if (preferences.getBoolean(General.PREFS_IsLogin, false)){
            google_logout.setVisibility(View.VISIBLE);
//            tv_notification.setVisibility(View.VISIBLE);
//            ll_notification.setVisibility(View.VISIBLE);
        } else{
            google_logout.setVisibility(View.GONE);
//            tv_notification.setVisibility(View.GONE);
//            ll_notification.setVisibility(View.GONE);
        }

        //--------------------------------------------------------google plus---------------------------------------------------------------

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(Profile_Activity.this)
                .enableAutoManage(Profile_Activity.this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//-----------------------------------------------------------------------------------------------------------------------

        iv_cancel.setOnClickListener(this);
        iv_in.setOnClickListener(this);
        iv_twitter.setOnClickListener(this);
        iv_fb.setOnClickListener(this);
        iv_gplus.setOnClickListener(this);
        iv_youtube.setOnClickListener(this);
        google_login.setOnClickListener(this);
        google_logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_cancel:
                finish();
                break;

//            case R.id.tv_notification:
//                Intent in1 = new Intent(Profile_Activity.this, Notification_Activity.class);
//                startActivity(in1);
//                break;

            case R.id.google_login:
                if (utils.isNetworkAvailable(this)) {
                    signIn();
                } else {
                    utils.showAlertMessage(this, getString(R.string.err_no_internet));
                }
                break;
//
            case R.id.tv_about:
                Intent intent = new Intent(Profile_Activity.this, Acknowledgement_Activity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
//
            case R.id.tv_help:
                Intent i1 = new Intent(Profile_Activity.this, Help_Activity.class);
                startActivity(i1);
                break;

            case R.id.tv_rate:
                String url = "https://play.google.com/store/apps/details?id=com.imperialinfosys.coinscap";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.tv_privacy:
                Intent i3 = new Intent(Profile_Activity.this, Acknowledgement_Activity.class);
                i3.putExtra("type", "4");
                startActivity(i3);
                break;

            case R.id.tv_acknowledgement:
                Intent i2 = new Intent(Profile_Activity.this, Acknowledgement1_Activity.class);
                startActivity(i2);
                break;

            case R.id.tv_terms:
                Intent i4 = new Intent(Profile_Activity.this, Acknowledgement_Activity.class);
                i4.putExtra("type", "5");
                startActivity(i4);
                break;

            case R.id.tv_cookie:
                Intent i5 = new Intent(Profile_Activity.this, Acknowledgement_Activity.class);
                i5.putExtra("type", "6");
                startActivity(i5);
                break;

            case R.id.iv_in:
                Uri uri = Uri.parse("https://www.linkedin.com/company/coinscapmarket/"); // missing 'http://' will cause crashed
                Intent it1 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it1);
                break;

            case R.id.iv_twitter:
                Uri uri1 = Uri.parse("https://twitter.com/coinscapmarket"); // missing 'http://' will cause crashed
                Intent it2 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(it2);
                break;

            case R.id.iv_fb:
                Uri uri2 = Uri.parse("https://www.facebook.com/coinscap/"); // missing 'http://' will cause crashed
                Intent it3 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(it3);
                break;

            case R.id.iv_gplus:
                Uri uri3 = Uri.parse("https://plus.google.com/u/0/104458482184300912407"); // missing 'http://' will cause crashed
                Intent it4 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(it4);
                break;

            case R.id.iv_youtube:
                Uri uri4 = Uri.parse("https://www.youtube.com/channel/UCGQQFjvona79KaGNR-M0zkA"); // missing 'http://' will cause crashed
                Intent it5 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(it5);
                break;

            case R.id.google_logout:
                mGoogleApiClient.stopAutoManage(this);
                mGoogleApiClient.disconnect();
                google_logout.setVisibility(View.GONE);
//                tv_notification.setVisibility(View.GONE);
//                ll_notification.setVisibility(View.GONE);
                google_login.setVisibility(View.VISIBLE);
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(General.PREFS_IsLogin, false);
                e.putString(General.PREFS_Picture, "");
                e.apply();
                Intent int1 = new Intent(Profile_Activity.this, Coins_Activity.class);
                int1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                int1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(int1);
                finish();
                break;
//            case R.id.tv_setting:
//                Intent i6 = new Intent(Profile_Activity.this, Setting_Activity.class);
//                startActivity(i6);
//                break;
        }
    }

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }

    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            SharedPreferences.Editor e = preferences.edit();
            e.putString(General.PREFS_User_Name, acct.getDisplayName());
            e.apply();
            new socialLoginTask(acct.getId(), acct.getDisplayName(), acct.getEmail(), acct.getPhotoUrl().toString()).execute();

        } else {
            Log.e("Signed out", "unauthenticated UI");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }


    // -----------------------------------------Api socialLogin-------------------------------------------------------------------


    public class socialLoginTask extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;
        private String google_id = "", name = "", email = "", picture = "";

        public socialLoginTask(String google_id, String name, String email, String picture) {

            this.name = name;
            this.email = email;
            this.picture = picture;
            this.google_id = google_id;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                ParsedResponse p = Service.apiLogin(Profile_Activity.this, name, email, picture, google_id, "0", device_id);
                error = p.error;
                if (!error) {
                    msg = (String) p.o;
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
            mProgressDialog.dismiss();

            if (!error) {
                google_logout.setVisibility(View.VISIBLE);
//                tv_notification.setVisibility(View.VISIBLE);
//                ll_notification.setVisibility(View.VISIBLE);
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(General.PREFS_IsLogin, true);
                e.apply();

                Intent int1 = new Intent(Profile_Activity.this, Coins_Activity.class);
                int1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                int1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(int1);
                finish();

            } else {
                utils.showAlertMessage(Profile_Activity.this, msg);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
