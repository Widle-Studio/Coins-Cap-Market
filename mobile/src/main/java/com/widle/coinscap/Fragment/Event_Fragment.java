package com.widle.coinscap.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.widle.coinscap.Adapter.Event_Adapter;
import com.widle.coinscap.Adapter.Event_Category_adapter;
import com.widle.coinscap.Exchange_Activity;
import com.widle.coinscap.ICO_Activity;
import com.widle.coinscap.Price_Alert1_Activity;
import com.widle.coinscap.Profile_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.TopList_Activity;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.Event;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Event_Fragment extends Fragment implements  NavigationView.OnNavigationItemSelectedListener{

    private TextView tv_app_name, tv_lbl;

    PopupMenu popupmenu;

    public ProgressDialog mProgressDialog;

    ArrayList<Event> mArrayList = new ArrayList<>();

    ArrayList<Event> mCategoryArrayList = new ArrayList<>();

    private CircleImageView iv_profilepic;

    private TextView tv_user_name;

    private String pro_pic = "",  user_name = "";

    SharedPreferences preferences;

    RecyclerView mRecyclerView, rv_chip;

    RecyclerView.LayoutManager mLayoutManager;

    private Toolbar mToolbar;

    DrawerLayout drawer;

    Event_Category_adapter event_category_adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        preferences = getActivity().getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");
        user_name = preferences.getString(General.PREFS_User_Name, "");

        tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);
        tv_lbl = (TextView) view.findViewById(R.id.tv_lbl);

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rv_chip = view.findViewById(R.id.rv_chip);
        mRecyclerView.setHasFixedSize(true);
        rv_chip.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_chip.setLayoutManager(mLayoutManager);


        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        iv_profilepic = header.findViewById(R.id.iv_profilepic);
        tv_user_name = header.findViewById(R.id.tv_user_name);


        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_app_name.setTypeface(type2);
        tv_lbl.setTypeface(type1);

        if (utils.isNetworkAvailable(getActivity())) {
            tv_lbl.setVisibility(View.GONE);
            new apiGet_Token().execute();

        } else {
            tv_lbl.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }


        if (pro_pic.equals("")) {
        } else {
            String url = "https://widle.studio/coinscapmarket/API/profile/";
            Glide.with(getActivity()).load(url + pro_pic).into(iv_profilepic);
        }

        iv_profilepic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Intent intent = new Intent(getActivity(), Profile_Activity.class);
                startActivity(intent);
                return false;
            }
        });
        tv_user_name.setText(user_name);


        rv_chip.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                rv_chip, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                view.setBackgroundResource(R.color.org);
                new apiCatEventList(mCategoryArrayList.get(position).id).execute();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


//        event_category_adapter.setClickListener(this);

        return view;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_exchange) {
            Intent intent =  new Intent(getActivity(), Exchange_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_toplist) {
            Intent intent =  new Intent(getActivity(), TopList_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_icolist) {
            Intent intent =  new Intent(getActivity(), ICO_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pricealert){
            Intent intent = new Intent(getActivity(), Price_Alert1_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile){
            Intent intent = new Intent(getActivity(), Profile_Activity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }



    public class apiGet_Token extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://api.coinmarketcal.com/oauth/v2/token?grant_type=client_credentials&client_id=883_3nwaj37uduskg0cg00wgg40o8c8sw08o8sso4osccgks00ooss&client_secret=3k043tvj5l0ks40440ck04gk00wowkc4wkskws4sc4kow0g80k";
                ParsedResponse p = Service.apiGetToken(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Event>) p.o;
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
                new apiEventCategoryList().execute();
            } else {
            }
        }
    }


    public class apiEventCategoryList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String token = preferences.getString(General.PREFS_Token, "");
            try {
                String Baseurl = "https://api.coinmarketcal.com/v1/categories?access_token=" + token;
                ParsedResponse p = Service.apiGetEventsCategory(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mCategoryArrayList = (ArrayList<Event>) p.o;
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
                event_category_adapter= new Event_Category_adapter(getActivity(),mCategoryArrayList);
                rv_chip.setAdapter(event_category_adapter);
                new apiEventList().execute();
            } else {
            }
        }
    }




    public class apiEventList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String token = preferences.getString(General.PREFS_Token, "");
            try {
                String Baseurl = "https://api.coinmarketcal.com/v1/events?access_token=" + token + "&max=150";
                ParsedResponse p = Service.apiGetEvents(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Event>) p.o;
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
                Event_Adapter  event_adapter= new Event_Adapter(getActivity(),mArrayList);
                mRecyclerView.setAdapter(event_adapter);
            } else {
            }
        }
    }







    public class apiCatEventList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, id;

        public apiCatEventList(String id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String token = preferences.getString(General.PREFS_Token, "");
            try {

                String Baseurl = "https://api.coinmarketcal.com/v1/events?access_token=" + token + "&max=150&categories=" + id;
                ParsedResponse p = Service.apiGetEvents(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Event>) p.o;
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
                Event_Adapter  event_adapter= new Event_Adapter(getActivity(),mArrayList);
                mRecyclerView.setAdapter(event_adapter);
            } else {
            }
        }
    }



    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }


}
