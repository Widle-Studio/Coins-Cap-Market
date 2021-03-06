package com.widle.coinscap;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.widle.coinscap.Notification.Notification_News_reciever;
import com.widle.coinscap.Notification.Notification_reciever;

public class Notification_Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_notification, tv_podcast, tv_title;

    private ImageView iv_back;

    private RadioGroup radioGroup, radioGroup1;

    private RadioButton rb_hourly, rb_daily, rb1_hourly, rb1_daily, rb1_weekly;

    private int NOTIFICATION_ID_NORMAL_SIZE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        init();
    }

    public void init(){

        tv_notification = findViewById(R.id.tv_notification);
        tv_podcast = findViewById(R.id.tv_podcast);
        tv_title = findViewById(R.id.tv_title);

        iv_back = findViewById(R.id.iv_back);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup1 = findViewById(R.id.radioGroup1);

        rb_hourly = findViewById(R.id.rb_hourly);
        rb_daily = findViewById(R.id.rb_daily);
        rb1_hourly = findViewById(R.id.rb1_hourly);
        rb1_daily = findViewById(R.id.rb1_daily);
        rb1_weekly = findViewById(R.id.rb1_weekly);



        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_notification.setTypeface(type1);
        tv_podcast.setTypeface(type1);
        tv_title.setTypeface(type2);
        rb_hourly.setTypeface(type);
        rb_daily.setTypeface(type);
        rb1_hourly.setTypeface(type);
        rb1_daily.setTypeface(type);
        rb1_weekly.setTypeface(type);

        radioGroup.clearCheck();
        radioGroup1.clearCheck();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                String rb_type = (String) rb.getText();

                if (rb_type.equals("Hourly")){
                    AlarmManager am=(AlarmManager)Notification_Activity.this.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(Notification_Activity.this, Notification_News_reciever.class);
                    PendingIntent pi = PendingIntent.getBroadcast(Notification_Activity.this, 0, intent, 0);
                    am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),  1000*60*60 , pi);

                } else if (rb_type.equals("Daily")){

                    AlarmManager am=(AlarmManager)Notification_Activity.this.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(Notification_Activity.this, Notification_News_reciever.class);
                    PendingIntent pi = PendingIntent.getBroadcast(Notification_Activity.this, 0, intent, 0);
                    am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 24*60*60*1000 , pi);

                }

            }
        });



        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                String rb_type = (String) rb.getText();
                if (rb_type.equals("Hourly")){

                    AlarmManager am=(AlarmManager)Notification_Activity.this.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(Notification_Activity.this, Notification_reciever.class);
                    PendingIntent pi = PendingIntent.getBroadcast(Notification_Activity.this, 0, intent, 0);
                    am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),  1000*60*60 , pi);

                } else if (rb_type.equals("Daily")){

                    AlarmManager am=(AlarmManager)Notification_Activity.this.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(Notification_Activity.this, Notification_reciever.class);
                    PendingIntent pi = PendingIntent.getBroadcast(Notification_Activity.this, 0, intent, 0);
                    am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 24*60*60*1000 , pi);

                } else{
                    Calendar calNow = Calendar.getInstance();
                    long Seconds=calNow.getTimeInMillis();
                    AlarmManager am=(AlarmManager)Notification_Activity.this.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(Notification_Activity.this, Notification_reciever.class);
                    PendingIntent pi = PendingIntent.getBroadcast(Notification_Activity.this, 0, intent, 0);
                    am.setRepeating(AlarmManager.RTC_WAKEUP, Seconds,AlarmManager.INTERVAL_DAY * 7 , pi);

                }
            }
        });


        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Notification_Activity.this, Notification_News_Activity.class);
        switch (view.getId()){
//            case R.id.tv_notification_news:
//                intent.putExtra("name", "Notification News");
//                startActivity(intent);
//                break;
//
//            case R.id.tv_notification_podcast:
//                intent.putExtra("name", "Notification Podcast");
//                startActivity(intent);
//                break;

            case R.id.iv_back:
                finish();
                break;
        }
    }


    public void notification(){
        // BEGIN_INCLUDE(notificationCompat)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // END_INCLUDE(notificationCompat)

        // BEGIN_INCLUDE(intent)
        //Create Intent to launch this Activity again if the notification is clicked.
        Intent i = new Intent(this, Home_Activity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);
        // END_INCLUDE(intent)
        builder.setTicker(getResources().getString(R.string.custom_notification));

        builder.setSmallIcon(R.mipmap.ic_icon);
        // END_INCLUDE(ticker)

        builder.setAutoCancel(true);

        builder.setDefaults(Notification.DEFAULT_ALL);
        // Build the notification
        Notification notification = builder.build();
        // BEGIN_INCLUDE(customLayout)
        // Inflate the notification layout as RemoteViews
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);

        // Set text on a TextView in the RemoteViews programmatically.
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        String text = getResources().getString(R.string.custom_notification, time);
        String text1 = getResources().getString(R.string.app_name, time);
        contentView.setTextViewText(R.id.textView, text);
        contentView.setTextViewText(R.id.textView1, text1);


        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView =
                    new RemoteViews(getPackageName(), R.layout.notification);
            notification.bigContentView = expandedView;
        }
        // Use the NotificationManager to show the notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
    }
}
