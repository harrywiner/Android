package uk.co.iamenigma.senior;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Harry-Laptop on 22/05/2017.
 */

public class ContactButton {
    public static final String API_KEY = "714dbf8b";
    public static final String API_SECRET = "14beb0e1a2a4d105";
    private String message = "I+require+assistance";


    public class DownloadClass extends AsyncTask<String, Void, String>
    {

        URL url;
        String result = "";
        @Override
        protected String doInBackground(String... urls) {
            try {
                url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1)
                {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }
                Log.i("Log","MESSAGE SENDING");
            } catch(MalformedURLException e)
            {
                Log.i("Error Log:", "MAL" + e.getMessage());
                result = null;
            } catch (IOException e) {
                Log.i("Error Log:", "IO" + e.getMessage());
            }
            return result;
        }
    }

    public boolean sendText(String string)
    {

        DownloadClass downloadClass = new DownloadClass();
        Log.i("Log", string);
        System.out.println(string);
        boolean isEmpty = downloadClass.execute("https://rest.nexmo.com/sms/json?api_key="+API_KEY+"&api_secret="+API_SECRET+"&to="+ string +"&from=NexmoWorks&text="+ message).equals(null);
        return !isEmpty;
    }

    public void sendLocation(String str, String number)
    {
        DownloadClass downloadClass = new DownloadClass();
        downloadClass.execute("https://rest.nexmo.com/sms/json?api_key="+API_KEY+"&api_secret="+API_SECRET+"&to="+ number +"&from=NexmoWorks&text=I+am+at+this+location:"+ str + "Please+pick+me+up");
    }

}
