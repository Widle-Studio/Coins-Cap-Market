package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import com.widle.coinscap.Adapter.PodcastChanel_Adapter;
import com.widle.coinscap.Adapter.Podcast_Part_Adapter;
import com.widle.coinscap.Fragment.Podcast_Fragment;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.PodcastChanel;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Podcast_Part_Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_app_name, tv_title, tv_lbl;

    private ImageView iv_back, iv_title;

    private RecyclerView rv_podcast;

    PopupMenu popupmenu;

    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();

    public ProgressDialog mProgressDialog;

    private String channel_id, title, description, subtitle, image, pro_pic = "";

    private Podcast_Part_Adapter podcast_part_adapter;

//    private CircleImageView iv_profilepic;

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_part);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        channel_id = getIntent().getStringExtra("channel_id");
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        subtitle = getIntent().getStringExtra("subtitle");
        image = getIntent().getStringExtra("image");

        init();

        if (utils.isNetworkAvailable(this)) {
            rv_podcast.setVisibility(View.VISIBLE);
            tv_lbl.setVisibility(View.GONE);
            new apiGet_PodcastPart().execute();
        } else {
            rv_podcast.setVisibility(View.GONE);
            tv_lbl.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }
    }

    public void init(){

        tv_app_name = findViewById(R.id.tv_app_name);
        tv_title = findViewById(R.id.tv_title);
        findViewById(R.id.iv_back).setOnClickListener(this);

//        iv_profilepic = findViewById(R.id.iv_profilepic);
        iv_title = findViewById(R.id.iv_title);
        tv_lbl = (TextView) findViewById(R.id.tv_lbl);
        rv_podcast = (RecyclerView) findViewById(R.id.rv_podcast);
        rv_podcast.setLayoutManager(new LinearLayoutManager(Podcast_Part_Activity.this, LinearLayoutManager.VERTICAL, false));
        rv_podcast.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_app_name.setTypeface(type2);
        tv_title.setTypeface(type);
        tv_lbl.setTypeface(type1);

        tv_title.setText(title);
        Glide.with(Podcast_Part_Activity.this)
                .load(image)
                .into(iv_title);

//        if (pro_pic.equals("")){
//        } else{
//            String url = "http://coinscapmarket.com/API/profile/";
//            Glide.with(Podcast_Part_Activity.this).load(url + pro_pic).into(iv_profilepic);
//        }
//
//
//        iv_profilepic.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
////                popupmenu = new PopupMenu(Podcast_Part_Activity.this, view);
////                PopMenuDisplay();
//
//                Intent intent = new Intent(Podcast_Part_Activity.this, Profile_Activity.class);
//                startActivity(intent);
//                return false;
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    public void PopMenuDisplay() {
        popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Intent intent = new Intent(Podcast_Part_Activity.this,Acknowledgement_Activity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                        return true;

                    case R.id.help:
                        Intent i1 = new Intent(Podcast_Part_Activity.this, Acknowledgement_Activity.class);
                        i1.putExtra("type", "2");
                        startActivity(i1);
                        return true;

                    case R.id.acknowledgement:
                        Intent i2 = new Intent(Podcast_Part_Activity.this, Acknowledgement1_Activity.class);
                        startActivity(i2);
                        return true;

                    case R.id.privacy:
                        Intent i3 = new Intent(Podcast_Part_Activity.this, Acknowledgement_Activity.class);
                        i3.putExtra("type", "4");
                        startActivity(i3);
                        return true;

                    case R.id.terms:
                        Intent i4 = new Intent(Podcast_Part_Activity.this, Acknowledgement_Activity.class);
                        i4.putExtra("type", "5");
                        startActivity(i4);
                        return true;

                    case R.id.cookie:
                        Intent i5 = new Intent(Podcast_Part_Activity.this, Acknowledgement_Activity.class);
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
                        Intent i7 = new Intent(Podcast_Part_Activity.this, Setting_Activity.class);
                        startActivity(i7);
                        return true;
                }
                return false;
            }
        });
        popupmenu.show();
    }


    public class apiGet_PodcastPart extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                String Baseurl = "https://api.podcast.de/channel/"+channel_id+".json?limit=5000";
                ParsedResponse p = Service.apiGetPodcastPart(Podcast_Part_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<PodcastChanel>) p.o;
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
                podcast_part_adapter = new Podcast_Part_Adapter(Podcast_Part_Activity.this, mArrayList);
                rv_podcast.setAdapter(podcast_part_adapter);
            } else {
                utils.showAlertMessage(Podcast_Part_Activity.this, msg);
            }
        }
    }
}
