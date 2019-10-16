package uk.co.iamenigma.weatherapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Templates;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;

            } catch(Exception e) {

                e.printStackTrace();

                return "Failed";

            }

        }
    }


    public void onClick(View v)
    {
        DownloadTask task = new DownloadTask();
        String result = "";
        String str = "";
        switch(v.getId())
        {
            case R.id.button:
                str = "http://api.openweathermap.org/data/2.5/weather?q=London&mode=JSON&units=metric&appid=8aa88b9d4da5d9fcd2c8adfd7699885e";
                break;
            case R.id.button2:
                str = "http://api.openweathermap.org/data/2.5/weather?lat=46.316619&lon=6.19357&mode=JSON&units=metric&appid=8aa88b9d4da5d9fcd2c8adfd7699885e";
                break;
            case R.id.button3:
                str = "http://api.openweathermap.org/data/2.5/weather?q=Honolulu&mode=JSON&units=metric&appid=8aa88b9d4da5d9fcd2c8adfd7699885e";
                break;
        }
        try {
            result = task.execute(str).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("Result", result);
        processOutput(result);
    }

    public void processOutput(String string) {

        String s = string.substring(string.indexOf("main") + 7);
        String main = s.substring(0, s.indexOf(",") - 1);

        TextView m = (TextView) findViewById(R.id.main);
        m.setText(main);

        s = string.substring(string.indexOf("visibility") + 12);
        main = s.substring(0, s.indexOf(","));

        TextView vis = (TextView) findViewById(R.id.vis);
        vis.setText(main);
        Log.i("Vis", main);


        s = string.substring(string.indexOf("temp") + 6);
        main = s.substring(0, s.indexOf(",")) + "C";

        TextView temp = (TextView) findViewById(R.id.temp);
        temp.setText(main);



    }
}