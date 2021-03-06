package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class Setting_Activity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private TextView tv_title, tv_version_code, tv_notification;
    private ImageView iv_back;

    private Button google_login, google_logout;

    private GoogleSignInOptions gso;

    private int RC_SIGN_IN = 100;

    // google plus
    private GoogleApiClient mGoogleApiClient;

    private ProgressDialog mProgressDialog;

    private String device_id = "";

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        device_id = preferences.getString(General.PREFS_Device_id, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init();
    }

    public void init(){

        tv_title = findViewById(R.id.tv_title);
        tv_version_code = findViewById(R.id.tv_version_code);
        tv_notification = findViewById(R.id.tv_notification);
        iv_back = findViewById(R.id.iv_back);
        google_login = (Button) findViewById(R.id.google_login);
        google_logout = (Button) findViewById(R.id.google_logout);

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_title.setTypeface(type2);
        tv_version_code.setTypeface(type);
        tv_notification.setTypeface(type1);

        if (preferences.getBoolean(General.PREFS_IsLogin, false)){
            google_logout.setVisibility(View.VISIBLE);
            tv_notification.setVisibility(View.VISIBLE);
        } else{
            google_logout.setVisibility(View.GONE);
            tv_notification.setVisibility(View.GONE);
        }

        //--------------------------------------------------------google plus---------------------------------------------------------------

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(Setting_Activity.this)
                .enableAutoManage(Setting_Activity.this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//-----------------------------------------------------------------------------------------------------------------------

        int versionCode = BuildConfig.VERSION_CODE;
        tv_version_code.setText("Version " + versionCode);

        iv_back.setOnClickListener(this);
        google_login.setOnClickListener(this);
        google_logout.setOnClickListener(this);
        tv_notification.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.google_login:
                if (utils.isNetworkAvailable(this)) {
                    signIn();
                } else {
                    utils.showAlertMessage(this, getString(R.string.err_no_internet));
                }
                break;


            case R.id.google_logout:
                mGoogleApiClient.stopAutoManage(this);
                mGoogleApiClient.disconnect();
                google_logout.setVisibility(View.GONE);
                tv_notification.setVisibility(View.GONE);
                google_login.setVisibility(View.VISIBLE);
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(General.PREFS_IsLogin, false);
                e.apply();
                break;

            case R.id.tv_notification:
                Intent intent = new Intent(Setting_Activity.this, Notification_Activity.class);
                startActivity(intent);
                break;
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
                ParsedResponse p = Service.apiLogin(Setting_Activity.this, name, email, picture, google_id, "0", device_id);
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
                tv_notification.setVisibility(View.VISIBLE);
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(General.PREFS_IsLogin, true);
                e.apply();
            } else {
                utils.showAlertMessage(Setting_Activity.this, msg);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
