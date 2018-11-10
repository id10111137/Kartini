package com.example.tatang.myapplication.Hellper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


/**
 * Created by tatang.it on 04/07/2018.
 */

public class DataConnection {
    @SuppressLint("StaticFieldLeak")
    static Context context;

    private static DataConnection instance = new DataConnection();
    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo, networkInfo;
    boolean connected = false;
    boolean ofline = false;

    public static DataConnection getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }

    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            Log.e("status", e.toString());
        }
        return connected;
    }


}
