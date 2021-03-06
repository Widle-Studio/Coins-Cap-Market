package com.widle.coinscap.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.widle.coinscap.Fragment.Live_Fragment;
import com.widle.coinscap.Fragment.Reddit_News_Fragment;

public class ICO_ViewPagerAdapter extends FragmentPagerAdapter {

    public ICO_ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "live");
            fragment = new Live_Fragment();
            fragment.setArguments(bundle);
        }
        else if (position == 1)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "upcoming");
            fragment = new Live_Fragment();
            fragment.setArguments(bundle);
        }
        else if (position == 2)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "finished");
            fragment = new Live_Fragment();
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Live";
        }
        else if (position == 1)
        {
            title = "Upcoming";
        }

        else if (position == 2)
        {
            title = "Finished";
        }

        return title;
    }

}
