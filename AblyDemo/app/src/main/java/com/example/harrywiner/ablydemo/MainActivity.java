package com.example.harrywiner.ablydemo;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.ConnectionState;
import io.ably.lib.realtime.ConnectionStateListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ClientOptions;
import io.ably.lib.types.Message;

public class MainActivity extends AppCompatActivity {


    private AblyRealtime ably;
    private Channel channel;
    /**
        This class takes the input from the editText
        after the send button is pressed
        it runs the Ably.io API to send the received message
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectionListener cs = new ConnectionListener(getApplicationContext());

        try {
            ClientOptions options = new ClientOptions("6jfIXA.JfGO9Q:KXjBJ8KYatr-FNHz");
            ably = new AblyRealtime(options);
            ably.connection.on(ConnectionState.connected, new ConnectionStateListener(){

                public void onConnectionStateChanged(ConnectionStateChange state) {
                    Log.i( "CONNECTIONSTATE1", "Ably server state " + state.current.name());

                }

            });
            channel = ably.channels.get("conversation");
        } catch (AblyException e) {
            //e.printStackTrace();
        }

        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

        //Turn airplane mode on
        wifiManager.setWifiEnabled(false);
        wifiManager.setWifiEnabled(true);

    };


}









/*public void onClick(View v)
    {

        Log.i("Info", "button pressed");

        EditText editText = findViewById(R.id.editText);

        String str = editText.getText().toString();

        Log.i("EditText", str);

        editText.setText("");





        try {
            channel.publish("conversation", str);
        } catch (AblyException e) {
            e.printStackTrace();
        }
    }

    public void onChange(View view)
    {

        Intent intent = new Intent(getApplicationContext(), ReceiveActivity.class);
        startActivity(intent);

    }*/