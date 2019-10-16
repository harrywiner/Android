package uk.co.iamenigma.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view)
    {
        EditText editText = (EditText) findViewById(R.id.moneyText);
        String str = editText.getText().toString();
        Double amount = Double.parseDouble(str);

        Log.i("Pounds", amount.toString());

        Double dollars = amount*1.22;

        String s = String.format("$%.2f", dollars);

        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
