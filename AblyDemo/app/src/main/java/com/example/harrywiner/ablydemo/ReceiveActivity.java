package com.example.harrywiner.ablydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.ConnectionState;
import io.ably.lib.realtime.ConnectionStateListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ClientOptions;
import io.ably.lib.types.Message;

public class ReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);



        try {
            ClientOptions options = new ClientOptions("6jfIXA.JfGO9Q:KXjBJ8KYatr-FNHz");
            AblyRealtime ably = new AblyRealtime(options);
            ably.connection.on(ConnectionState.connected, new ConnectionStateListener(){

                public void onConnectionStateChanged(ConnectionStateChange state) {
                    Log.i("Info", "Realtime connection established");
                }
            });
            Channel channel = ably.channels.get("conversation");
            channel.subscribe(new Channel.MessageListener() {
                @Override
                public void onMessage(Message message) {
                    String str = "" + message.data;

                    Log.i("Info","message recieved " + str);

                    TextView textView = findViewById(R.id.textView);

                    textView.setText(str);
                }
            });
        } catch (AblyException e) {
            e.printStackTrace();
        }
    };


        public void onChange(View view)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }
