package com.widle.coinscap.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.widle.coinscap.Fragment.Crypto_Fragment;
import com.widle.coinscap.Fragment.News1_Fragment;
import com.widle.coinscap.Fragment.Reddit_News_Fragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new News1_Fragment();
        }
        else if (position == 1)
        {
            fragment = new Reddit_News_Fragment();
        }
        else if (position == 2)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "ANALYSIS");

            fragment = new Crypto_Fragment();
            fragment.setArguments(bundle);
        }
        else if (position == 3)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "BLOCKCHAIN");

            fragment = new Crypto_Fragment();
            fragment.setArguments(bundle);
        }
        else if (position == 4)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "EXCHANGES");

            fragment = new Crypto_Fragment();
            fragment.setArguments(bundle);
        }
        else if (position == 5)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "GOVERNMENT");

            fragment = new Crypto_Fragment();
            fragment.setArguments(bundle);
        }
        else if (position == 6)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "MINING");

            fragment = new Crypto_Fragment();
            fragment.setArguments(bundle);
        }
        else if (position == 7)
        {
            Bundle bundle = new Bundle();
            bundle.putString("type", "ICOS");

            fragment = new Crypto_Fragment();
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "News";
        }
        else if (position == 1)
        {
            title = "Reddit";
        }

        else if (position == 2)
        {
            title = "Analysis";
        }

        else if (position == 3)
        {
            title = "Blockchain";
        }

        else if (position == 4)
        {
            title = "Exchanges";
        }

        else if (position == 5)
        {
            title = "Government";
        }

        else if (position == 6)
        {
            title = "Mining";
        }

        else if (position == 7)
        {
            title = "ICOs";
        }
        return title;
    }

}
