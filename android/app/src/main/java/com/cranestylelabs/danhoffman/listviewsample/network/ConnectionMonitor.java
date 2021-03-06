package com.cranestylelabs.danhoffman.listviewsample.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectionMonitor {

    public static Boolean hasNetworkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

}
