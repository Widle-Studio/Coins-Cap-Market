package com.widle.coinscap.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import com.widle.coinscap.Utils.Model.Currency;
import com.widle.coinscap.Utils.Model.coin;

/**
 * Created by imperial-web1 on 9/3/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CoinsTable";

    // Coins table name
    private static final String TABLE_COINS = "Coins";
    private static final String TABLE_MAINCOINS = "Main_Coins";
    private static final String TABLE_ALERT = "Alert";


    // Coins Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_SYMBOL = "symbol";
    private static final String KEY_MARKET_CAP = "market_cap";
    private static final String KEY_PRICE = "price";
    private static final String KEY_VOLUME = "volume";
    private static final String KEY_PCT = "pct";


//  Main_Coins Table Columns names

    private static final String KEY_COIN_NAME = "coin_name";
    private static final String KEY_COIN_SYMBOL = "coin_symbol";
    private static final String KEY_COIN_MARKET_CAP = "coin_market_cap";
    private static final String KEY_COIN_PRICE = "coin_price";
    private static final String KEY_COIN_VOLUME = "coin_volume";
    private static final String KEY_COIN_PCT = "coin_pct";



//  Alert_Coins Table Columns names

    private static final String KEY_ALERT_COIN_NAME = "alert_coin_name";
    private static final String KEY_ALERT_CURRENT_PRICE = "alert_current_price";
    private static final String KEY_ALERT_ABOVE_PRICE = "alert_above_price";
    private static final String KEY_ALERT_BELOW_PRICE = "alert_below_price";




    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CARS_TABLE = "CREATE TABLE " + TABLE_COINS + "("
                + KEY_NAME + " TEXT,"
                + KEY_SYMBOL + " TEXT,"
                + KEY_MARKET_CAP + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_VOLUME + " TEXT,"
                + KEY_PCT + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CARS_TABLE);


        String CREATE_COINS_TABLE = "CREATE TABLE " + TABLE_MAINCOINS + "("
                + KEY_COIN_NAME + " TEXT,"
                + KEY_COIN_SYMBOL + " TEXT,"
                + KEY_COIN_MARKET_CAP + " TEXT,"
                + KEY_COIN_PRICE + " TEXT,"
                + KEY_COIN_VOLUME + " TEXT,"
                + KEY_COIN_PCT + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_COINS_TABLE);



        String CREATE_ALERT_TABLE = "CREATE TABLE " + TABLE_ALERT + "("
                + KEY_ALERT_COIN_NAME + " TEXT,"
                + KEY_ALERT_CURRENT_PRICE + " TEXT,"
                + KEY_ALERT_ABOVE_PRICE + " TEXT,"
                + KEY_ALERT_BELOW_PRICE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_ALERT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COINS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MAINCOINS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ALERT);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MAINCOINS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }



    // Adding new contact
    public void addCoins(String name, String symbol, String market_cap, String price, String volume, String pct) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_SYMBOL, symbol);
        values.put(KEY_MARKET_CAP, market_cap);
        values.put(KEY_PRICE, price);
        values.put(KEY_VOLUME, volume);
        values.put(KEY_PCT, pct);
        // Inserting Row
        db.insert(TABLE_COINS, null, values);
        db.close(); // Closing database connection
    }




    // Adding new contact
    public void addMainCoins(String name, String symbol, String market_cap, String price, String volume, String pct) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COIN_NAME, name);
        values.put(KEY_COIN_SYMBOL, symbol);
        values.put(KEY_COIN_MARKET_CAP, market_cap);
        values.put(KEY_COIN_PRICE, price);
        values.put(KEY_COIN_VOLUME, volume);
        values.put(KEY_COIN_PCT, pct);
        // Inserting Row
        db.insert(TABLE_MAINCOINS, null, values);
        db.close(); // Closing database connection
    }



    // Adding new contact
    public void addAlertCoins(String alert_coin_name, String current_price, String price_above, String price_below) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ALERT_COIN_NAME, alert_coin_name);
        values.put(KEY_ALERT_CURRENT_PRICE, current_price);
        values.put(KEY_ALERT_ABOVE_PRICE, price_above);
        values.put(KEY_ALERT_BELOW_PRICE, price_below);
        // Inserting Row
        db.insert(TABLE_ALERT, null, values);
        db.close(); // Closing database connection
    }



    //------------------------------------------------------------------------------------------------------------------------------


    public ArrayList<coin> getallcoins() {
        ArrayList<HashMap<String, String>> State;
        State = new ArrayList<HashMap<String, String>>();
        ArrayList<coin> DataList = new ArrayList<coin>();
        String selectQuery = "SELECT  * FROM " + TABLE_COINS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.v("TTT", "event cursor size = " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                coin model = new coin();
                model.NAME= cursor.getString(0);
                model.SYMBOL= cursor.getString(1);
                model.MKTCAP= cursor.getString(2);
                model.PRICE= cursor.getString(3);
                model.CHANGE24HOUR= cursor.getString(4);
                model.CHANGEPCT24HOUR= cursor.getString(5);
                DataList.add(model);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return DataList;
    }






    public ArrayList<coin> getallmaincoins() {
        ArrayList<HashMap<String, String>> State;
        State = new ArrayList<HashMap<String, String>>();
        ArrayList<coin> DataList = new ArrayList<coin>();
        String selectQuery = "SELECT  * FROM " + TABLE_MAINCOINS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.v("TTT", "event cursor size = " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                coin model = new coin();
                model.NAME= cursor.getString(0);
                model.SYMBOL= cursor.getString(1);
                model.MKTCAP= cursor.getString(2);
                model.PRICE= cursor.getString(3);
                model.CHANGE24HOUR= cursor.getString(4);
                model.CHANGEPCT24HOUR= cursor.getString(5);
                DataList.add(model);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return DataList;
    }



    public ArrayList<Currency> getallalert() {
        ArrayList<HashMap<String, String>> Currency;
        Currency = new ArrayList<HashMap<String, String>>();
        ArrayList<Currency> DataList = new ArrayList<Currency>();
        String selectQuery = "SELECT  * FROM " + TABLE_ALERT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.v("TTT", "event cursor size = " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                Currency model = new Currency();
                model.Alert_coin= cursor.getString(0);
                model.Alert_current_price= cursor.getString(1);
                model.Alert_above_price= cursor.getString(2);
                model.Alert_below_price= cursor.getString(3);
                DataList.add(model);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return DataList;
    }



    public boolean deleteTitle(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ALERT, KEY_ALERT_COIN_NAME + "=" + name, null) > 0;
    }



    public void Delete_item(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALERT, KEY_ALERT_COIN_NAME + " = ?", new String[] { name });
        db.close();
    }


    public void Deleteall(String coins) {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.execSQL("D   ELETE FROM "+NewsHandler.NewsTable+"");
//        db.delete(TABLE_RECORDS, null, null);
        db.delete(TABLE_COINS, KEY_SYMBOL + " = ?", new String[] { coins });
        db.close();
    }

    public void Deleteallcoins() {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.execSQL("D   ELETE FROM "+NewsHandler.NewsTable+"");
        db.delete(TABLE_MAINCOINS, null, null);
//        db.delete(TABLE_COINS, KEY_SYMBOL + " = ?", new String[] { coins });
        db.close();
    }


}
