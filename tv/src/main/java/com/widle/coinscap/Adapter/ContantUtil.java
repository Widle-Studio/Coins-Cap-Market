package com.widle.coinscap.Adapter;

import java.util.Random;

import com.widle.coinscap.R;

/**
 * Created by imperial-web on 5/21/2018.
 */

public class ContantUtil {
    private static int[] mColorIds = {R.color.dark_blue, R.color.dark_blue, R.color.dark_blue,
            R.color.dark_blue, R.color.dark_blue, R.color.dark_blue, R.color.dark_blue,
            R.color.dark_blue, R.color.dark_blue, R.color.dark_blue, R.color.dark_blue, R.color.dark_blue};

    static int getRandColor() {
        Random random = new Random();
        int pos = random.nextInt(mColorIds.length);
        return mColorIds[pos];
    }

   public static String[] TEST_DATAS = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R"};


}
