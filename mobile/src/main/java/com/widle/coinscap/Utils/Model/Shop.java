package com.widle.coinscap.Utils.Model;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.Arrays;
import java.util.List;

import com.widle.coinscap.R;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class Shop {

    private static final String STORAGE = "shop";

    public static Shop get() {
        return new Shop();
    }

    private SharedPreferences storage;

    private Shop() {
        storage = App.getInstance().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

    public List<Item> getData() {
        return Arrays.asList(
                new Item(1, "Everyday Candle", "$12.00 USD", R.drawable.aion),
                new Item(2, "Small Porcelain Bowl", "$50.00 USD", R.drawable.aion),
                new Item(3, "Favourite Board", "$265.00 USD", R.drawable.aion),
                new Item(4, "Earthenware Bowl", "$18.00 USD", R.drawable.aion),
                new Item(5, "Porcelain Dessert Plate", "$36.00 USD", R.drawable.aion),
                new Item(6, "Detailed Rolling Pin", "$145.00 USD", R.drawable.aion));
    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
