package com.example.harrywiner.ablydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectionListener extends BroadcastReceiver{

    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private boolean current;

    public ConnectionListener (Context context) {

        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        activeNetwork = cm.getActiveNetworkInfo();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        context.registerReceiver(this, filter);

        current = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Log.i("CONNECTIONSTATE1", "first = " + current);
    }

    public void onReceive(Context context, Intent intent)
    {

        boolean isConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        Log.i("CONNECTIONSTATE1", "ConnectionListener state = " + !isConnected);

    }

}
