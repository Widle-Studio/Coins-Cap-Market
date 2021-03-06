package com.widle.coinscap.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.widle.coinscap.Acknowledgement1_Activity;
import com.widle.coinscap.Acknowledgement_Activity;
import com.widle.coinscap.Adapter.PodcastChanel_Adapter;
import com.widle.coinscap.Exchange_Activity;
import com.widle.coinscap.Help_Activity;
import com.widle.coinscap.ICO_Activity;
import com.widle.coinscap.Price_Alert1_Activity;
import com.widle.coinscap.Profile_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Setting_Activity;
import com.widle.coinscap.TopList_Activity;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.PodcastChanel;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by imperial-web on 4/18/2018.
 */

public class Podcast_Fragment extends Fragment  implements  NavigationView.OnNavigationItemSelectedListener{

    private TextView tv_app_name, tv_lbl;

    private RecyclerView rv_podcast;

    PopupMenu popupmenu;

    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    ArrayList<PodcastChanel> mArrayFilterList = new ArrayList<>();

    private PodcastChanel_Adapter podcastChanel_adapter;

    public ProgressDialog mProgressDialog;

    private CircleImageView iv_profilepic;

    private TextView tv_user_name;

    private String pro_pic = "", user_name = "";

    SharedPreferences preferences;

    private ImageView iv_filter;

    utils util;

    SearchView mSearchView;

    private Toolbar mToolbar;

    DrawerLayout drawer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_podcast, container, false);

        preferences = getActivity().getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");
        user_name = preferences.getString(General.PREFS_User_Name, "");

        rv_podcast = (RecyclerView) view.findViewById(R.id.rv_podcast);
        rv_podcast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);
        tv_lbl = (TextView) view.findViewById(R.id.tv_lbl);
        iv_filter = view.findViewById(R.id.iv_filter);
        mSearchView = (SearchView)view.findViewById(R.id.search);

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

        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_app_name.setTypeface(type2);
        tv_lbl.setTypeface(type1);
        tv_user_name.setTypeface(type1);

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);


        if (pro_pic.equals("")){
        } else{
            String url = "https://widle.studio/coinscapmarket/API/profile/";
            Glide.with(getActivity()).load(url + pro_pic).into(iv_profilepic);
        }
        tv_user_name.setText(user_name);


        if (utils.isNetworkAvailable(getActivity())) {
            rv_podcast.setVisibility(View.VISIBLE);
            tv_lbl.setVisibility(View.GONE);
            new apiGet_PodcastChanel().execute();
        } else {
            rv_podcast.setVisibility(View.GONE);
            tv_lbl.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }


        iv_filter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setupFilterDialog(getActivity());
                return false;
            }
        });


        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(mArrayList.contains(query)){
                    podcastChanel_adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(getActivity(), "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                podcastChanel_adapter.getFilter().filter(s);
                return false;
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (utils.isNetworkAvailable(getActivity())) {
                    rv_podcast.setVisibility(View.VISIBLE);
                    tv_lbl.setVisibility(View.GONE);
                    new apiGet_PodcastChanel().execute();
                } else {
                    rv_podcast.setVisibility(View.GONE);
                    tv_lbl.setVisibility(View.VISIBLE);
                    tv_lbl.setText(R.string.err_no_internet);
                }
                return false;
            }
        });

        return view;
    }


    public void PopMenuDisplay() {
        popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Intent intent = new Intent(getActivity(), Acknowledgement_Activity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                        return true;

                    case R.id.help:
                        Intent i1 = new Intent(getActivity(), Help_Activity.class);
                        startActivity(i1);
                        return true;

                    case R.id.acknowledgement:
                        Intent i2 = new Intent(getActivity(), Acknowledgement1_Activity.class);
                        startActivity(i2);
                        return true;

                    case R.id.privacy:
                        Intent i3 = new Intent(getActivity(), Acknowledgement_Activity.class);
                        i3.putExtra("type", "4");
                        startActivity(i3);
                        return true;

                    case R.id.terms:
                        Intent i4 = new Intent(getActivity(), Acknowledgement_Activity.class);
                        i4.putExtra("type", "5");
                        startActivity(i4);
                        return true;

                    case R.id.cookie:
                        Intent i5 = new Intent(getActivity(), Acknowledgement_Activity.class);
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
                        Intent i6 = new Intent(getActivity(), Setting_Activity.class);
                        startActivity(i6);
                        return true;
                }
                return false;
            }
        });
        popupmenu.show();
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


    public class apiGet_PodcastChanel extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://api.podcast.de/search.json?q=blockchain";
                ParsedResponse p = Service.apiGetPodcastChannel(getActivity(), Baseurl);
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
                podcastChanel_adapter = new PodcastChanel_Adapter(getActivity(), mArrayList);
                rv_podcast.setAdapter(podcastChanel_adapter);
            } else {
                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }



    public void setupFilterDialog(final Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_filter);

        final TextView tv_rate = (dialog).findViewById(R.id.tv_rate);
        TextView tv_rate1 = (dialog).findViewById(R.id.tv_rate1);
        TextView tv_rate2 = (dialog).findViewById(R.id.tv_rate2);
        TextView tv_rate3 = (dialog).findViewById(R.id.tv_rate3);
        TextView tv_rate4 = (dialog).findViewById(R.id.tv_rate4);
        TextView tv_rate5 = (dialog).findViewById(R.id.tv_rate5);
        TextView tv_search = (dialog).findViewById(R.id.tv_search);
        TextView tv_clear = (dialog).findViewById(R.id.tv_clear);
        TextView tv_cancel = (dialog).findViewById(R.id.tv_cancel);

        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_rate.setTypeface(type2);
        tv_rate1.setTypeface(type1);
        tv_rate2.setTypeface(type1);
        tv_rate3.setTypeface(type1);
        tv_rate4.setTypeface(type1);
        tv_rate5.setTypeface(type1);
        tv_search.setTypeface(type1);
        tv_clear.setTypeface(type1);
        tv_cancel.setTypeface(type1);

        tv_rate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_rate.setText("1");
            }
        });

        tv_rate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_rate.setText("2");
            }
        });

        tv_rate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_rate.setText("3");
            }
        });

        tv_rate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_rate.setText("4");
            }
        });

        tv_rate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_rate.setText("5");
            }
        });

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rate = tv_rate.getText().toString();
               for (int i = 0; i < mArrayList.size(); i++){
                   if (rate.equals("1")){
                       mArrayFilterList.addAll(mArrayList);
                   } else if (rate.equals("2")){
                       if (mArrayList.get(i).rating.equals("2") || mArrayList.get(i).rating.equals("3") || mArrayList.get(i).rating.equals("4") || mArrayList.get(i).rating.equals("5")){
                           mArrayFilterList.add(mArrayList.get(i));
                       } else {
                       }

                   } else if (rate.equals("3")){
                       if (mArrayList.get(i).rating.equals("3") || mArrayList.get(i).rating.equals("4") || mArrayList.get(i).rating.equals("5")){
                           mArrayFilterList.add(mArrayList.get(i));
                       } else {
                       }
                   } else if (rate.equals("4")){
                       if (mArrayList.get(i).rating.equals("4") || mArrayList.get(i).rating.equals("5")){
                           mArrayFilterList.add(mArrayList.get(i));
                       } else {
                       }
                   } else if (rate.equals("5")){
                       if (mArrayList.get(i).rating.equals("5")){
                           mArrayFilterList.add(mArrayList.get(i));
                       } else {
                       }
                   }
               }

               dialog.dismiss();
                podcastChanel_adapter = new PodcastChanel_Adapter(getActivity(), mArrayFilterList);
                rv_podcast.setAdapter(podcastChanel_adapter);
            }
        });

        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_rate.setText("");
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        dialog.show();

    }



}
