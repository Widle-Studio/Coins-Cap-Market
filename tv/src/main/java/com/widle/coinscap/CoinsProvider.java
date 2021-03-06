package com.widle.coinscap;

import java.util.ArrayList;

import com.widle.coinscap.Model.coin;

/**
 * Created by imperial-web on 4/12/2018.
 */

public class CoinsProvider {

    private static final String TAG = CoinsProvider.class.getSimpleName();

    private static ArrayList<coin> mItems = null;

    private CoinsProvider() {}

    public static synchronized ArrayList<coin> getMovieItems() {
        if(mItems == null) {
            mItems = new ArrayList<coin>();

            coin movie1 = new coin();
            movie1.setId(1);
            movie1.setCOINSNAME("Bitcoin");
            movie1.setCOINSSTRINGNAME("BTC");
            //movie1.setVideoUrl("http://corochann.com/wp-content/uploads/2015/07/MVI_0949.mp4");
            /* Google sample app's movie */
            mItems.add(movie1);

            coin movie2 = new coin();
            movie2.setId(1);
            movie2.setCOINSNAME("Ethereum");
            movie2.setCOINSSTRINGNAME("ETH");
            //movie1.setVideoUrl("http://corochann.com/wp-content/uploads/2015/07/MVI_0949.mp4");
            /* Google sample app's movie */
            mItems.add(movie2);

            coin movie3 = new coin();
            movie3.setId(1);
            movie3.setCOINSNAME("Ripple");
            movie3.setCOINSSTRINGNAME("XRP");
            //movie1.setVideoUrl("http://corochann.com/wp-content/uploads/2015/07/MVI_0949.mp4");
            /* Google sample app's movie */
            mItems.add(movie3);
        }
        return mItems;
    }

}
