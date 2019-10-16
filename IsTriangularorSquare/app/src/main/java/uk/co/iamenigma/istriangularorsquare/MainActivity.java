package uk.co.iamenigma.istriangularorsquare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private int num;
    public void onClick (View view) {

        EditText numberEditText = (EditText) findViewById(R.id.numberEditText);
        if (numberEditText.getText().toString() != null)
        {
            Integer i = parseInt(numberEditText.getText().toString());
            numberShapes(i);
        }
    }

    public void numberShapes(int num)
    {
        this.num = num;
        boolean tri = isTriangle();
        boolean squ = isSquare();
        if (tri == true)
        {
            if (squ == true)
                Toast.makeText(MainActivity.this, "The number is a triangle and a square", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "The number is just a triangle", Toast.LENGTH_SHORT).show();
        }
        else if (squ == true)
            Toast.makeText(MainActivity.this, "The number is just a square", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "The number is neither a triangle nor a square", Toast.LENGTH_SHORT).show();
    }
    public boolean isTriangle()
    {
        boolean isTri = false;
        int total = 0;
        int tri = 0;
        while(num >= tri)
        {
            tri++;
            if (num == total)
            {

                isTri = true;

            }
            else
            {
                total += tri;

            }

        }
        return isTri;
    }
    public boolean isSquare()
    {
        if (Math.sqrt(num) == Math.round(Math.sqrt(num)))
            return true;
        else
            return false;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
