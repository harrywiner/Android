package uk.co.iamenigma.senior;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class InputScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);
        sharedPreferences = this.getSharedPreferences("uk.co.iamenigma.senior", Context.MODE_PRIVATE);
        Log.i("Log:", sharedPreferences.getAll().toString());
        if (sharedPreferences.contains("notFirstTime"))
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("phoneNumber", "no");
            startActivity(intent);
        }
    }
    public void inputNumber(View view)
    {
        EditText editText = (EditText) findViewById(R.id.phoneInput);
        String phoneNumber = editText.getText().toString();
        if(phoneNumber.indexOf("44") == 0 && phoneNumber.length() == 12) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            sharedPreferences.edit().putString("notFirstTime", "yes").apply();
            startActivity(intent);
        }
    }
}
