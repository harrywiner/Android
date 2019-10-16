package uk.co.iamenigma.senior;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onBackPress(View view)
    {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        intent.putExtra("phoneNumber", "no");
        startActivity(intent);
    }

    public void onUpdatePress(View view)
    {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.resetPassword);
        String number = editText.getText().toString();
        intent.putExtra("phoneNumber", number);
        startActivity(intent);
    }
}
