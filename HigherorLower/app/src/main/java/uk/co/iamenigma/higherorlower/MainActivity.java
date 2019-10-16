package uk.co.iamenigma.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Integer random;
    public MainActivity()
    {
        random = (int) (1 + Math.random()*20);
        Log.i("Random number", random.toString());
    }

    public void detectNumber(View view) {
        EditText numberField = (EditText) findViewById(R.id.numberField);
        Integer i = Integer.parseInt(numberField.getText().toString());

        Log.i("Number", i.toString());

        if (i < 1 || i > 20)
            Toast.makeText(MainActivity.this, "The number is not within 1-20 try again!", Toast.LENGTH_SHORT).show();

        else if (i < random) {
            Toast.makeText(MainActivity.this, "Higher!", Toast.LENGTH_SHORT).show();

        } else if (i > random)
            Toast.makeText(MainActivity.this, "Lower!", Toast.LENGTH_SHORT).show();

        else if (i == random) {
            Toast.makeText(MainActivity.this, "You got it! Play again!", Toast.LENGTH_SHORT).show();
            random = (int) (1 + Math.random() * 20);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
