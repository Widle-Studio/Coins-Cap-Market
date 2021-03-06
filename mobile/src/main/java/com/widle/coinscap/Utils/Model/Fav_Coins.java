package com.widle.coinscap.Utils.Model;

/**
 * Created by imperial-web1 on 10/3/18.
 */

public class Fav_Coins {

    private USD USD;


    public USD getUSD ()
    {
        return USD;
    }


    public void setUSD (USD USD)
    {
        this.USD = USD;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [USD = "+USD+"]";
    }
}
