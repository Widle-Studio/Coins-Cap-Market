package com.widle.coinscap.Servicess;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.R;

import static com.android.volley.VolleyLog.TAG;

public class MyForeGroundService extends Service {

    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";

    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";

    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";

    public static final String ACTION_PAUSE = "ACTION_PAUSE";

    public static final String ACTION_PLAY = "ACTION_PLAY";

    public int counter = 0;

    private String coins = "", currency = "", k = "", a_price = "", b_price = "";
    private int response_price = 0;
    private int above_price = 0;
    private int below_price = 0;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "";

    public MyForeGroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG_FOREGROUND_SERVICE, "My foreground service onCreate().");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        coins = intent.getStringExtra("coins");
//        currency = intent.getStringExtra("currency");
//        a_price = intent.getStringExtra("price_above");
//        b_price = intent.getStringExtra("price_below");
//        if (a_price.equals("")){
//        } else{
//            above_price = Integer.parseInt(a_price);
//        }
//
//        if (b_price.equals("")){
//        } else{
//            below_price = Integer.parseInt(b_price);
//        }

        if(intent != null)
        {
            String action = intent.getAction();

            switch (action)
            {
                case ACTION_START_FOREGROUND_SERVICE:
                    startTimer();
                    Toast.makeText(getApplicationContext(), "Foreground service is started.", Toast.LENGTH_LONG).show();
                    break;
                case ACTION_STOP_FOREGROUND_SERVICE:
                    stoptimertask();
                    stopForegroundService();
                    Toast.makeText(getApplicationContext(), "Foreground service is stopped.", Toast.LENGTH_LONG).show();
                    break;
                case ACTION_PLAY:
                    Toast.makeText(getApplicationContext(), "You click Play button.", Toast.LENGTH_LONG).show();
                    break;
                case ACTION_PAUSE:
                    Toast.makeText(getApplicationContext(), "You click Pause button.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private Timer timer;
    private TimerTask timerTask;

    public void startTimer() {
        timer = new Timer();

        initializeTimerTask();

        timer.schedule(timerTask, 5000, 5000); //
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i("in timer", "in timer ++++  " + (counter++));
            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    /* Used to build and start foreground service. */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startForegroundService(String price)
    {
        Log.d(TAG_FOREGROUND_SERVICE, "Start foreground service.");

        // Create notification default intent.
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        String NOTIFICATION_CHANNEL_ID = "com.imperialinfosys.coinscap";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        // Make notification show big text.
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Alert");
        bigTextStyle.bigText(price);
        // Set big text style.
        builder.setStyle(bigTextStyle);

        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Bitmap largeIconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        builder.setLargeIcon(largeIconBitmap);
        // Make the notification max priority.
        builder.setPriority(Notification.PRIORITY_MAX);
        // Make head-up notification.
        builder.setFullScreenIntent(pendingIntent, true);

        Notification notification = builder.build();

        startForeground(1, notification);
    }

    private void stopForegroundService()
    {
        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.");

        stopForeground(true);

        stopSelf();
    }




//    private void sendAndRequestResponse() {
//
//        //RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        url = "https://min-api.cryptocompare.com/data/price?fsym="+coins+"&tsyms="+currency;
//
//        //String Request initialized
//        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//
//                Log.e("response", "" + response);
//
//                try {
//                    JSONObject mainobj = new JSONObject(response);
//
//                    k = mainobj.getString("USD");
//
//                    String[] separated = k.split("\\.");
//                    String kk = separated[0]; // this will contain "Fruit"
//
//                    response_price = Integer.parseInt(kk);
//
//                    Log.e("response", "" + response_price);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.i(TAG,"Error :" + error.toString());
//            }
//        });
//
//        mRequestQueue.add(mStringRequest);
//
//        load();
//    }
//
//
//    public void load(){
//
//
//        if (a_price.equals("")){
//
//        }else {
//            if (above_price < response_price){
//                Log.e("response", "" + "11");
//                startForegroundService(above_price + " is Available Now");
//            } else{
//                Log.e("response", "" + "12");
//                Log.e("response", "" + above_price + " == " + response_price);
//            }
//        }
//
//        if (b_price.equals("")){
//
//        }else {
//            if (below_price > response_price){
//                Log.e("response", "" + "22");
//                startForegroundService(below_price + " is Available Now");
//
//            } else{
//                Log.e("response", "" + "23");
//                Log.e("response", "" + below_price + " == " + response_price);
//            }
//        }
//    }
}