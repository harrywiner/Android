package uk.co.iamenigma.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("uk.co.iamenigma.sharedpreferences", Context.MODE_PRIVATE);
        Context context = this.getApplication().getApplicationContext();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("hello");
            outputStreamWriter.close();
        }
        catch (FileNotFoundException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        } catch (IOException e) {
            Log.e("Exception", "Input or output error:" + e.toString());
        }

    }
}
