package uk.co.iamenigma.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView red1, red2, red3, red4, red5, yellow1, yellow2, yellow3, yellow4, yellow5;
    private int currentRed, currentYellow, turn, left, middle, right;
    private int [] [] gameBoard;



    public void rightClick(View view)
    {
        Log.i("Info:", "Right box pressed");

        if (right >= 3)
            Toast.makeText(MainActivity.this, "This line is full" , Toast.LENGTH_SHORT).show();
        else {
            if (currentYellow <=5 || currentRed <= 5) {
                if (turn == 0) {


                    if (right == 0)
                        detectCurrentRed().animate().translationYBy(1400).setDuration(1000);
                    else if (right == 1)
                        detectCurrentRed().animate().translationYBy(900).setDuration(1000);
                    else if (right == 2)
                        detectCurrentRed().animate().translationYBy(400).setDuration(1000);

                    detectCurrentRed().setTranslationX(900);
                    detectCurrentRed().setAlpha(1f);
                    currentRed++;
                    turn++;
                    gameBoard[2][2 - right] = 1;
                    right++;

                } else {

                    detectCurrentYellow().setTranslationY(-500f);

                    if (right == 0)
                        detectCurrentYellow().animate().translationYBy(1400).setDuration(1000);
                    else if (right == 1)
                        detectCurrentYellow().animate().translationYBy(900).setDuration(1000);
                    else if (right == 2)
                        detectCurrentYellow().animate().translationYBy(400).setDuration(1000);

                    detectCurrentYellow().setTranslationX(900);

                    detectCurrentYellow().setAlpha(1f);
                    currentYellow++;
                    turn--;
                    gameBoard[2][2 - right] = 2;
                    right++;

                }
            }
        }

    }
    public void middleClick(View view)
    {
        if (middle >= 3)
            Toast.makeText(MainActivity.this, "This line is full" , Toast.LENGTH_SHORT).show();
        if (currentYellow <= 5 || currentRed <= 5) {
            Log.i("Info:", "Middle box pressed");
            if (turn == 0) {
                if (middle == 0)
                    detectCurrentRed().animate().translationYBy(1400).setDuration(1000);
                else if (middle == 1)
                    detectCurrentRed().animate().translationYBy(900).setDuration(1000);
                else if (middle == 2)
                    detectCurrentRed().animate().translationYBy(400).setDuration(1000);

                detectCurrentRed().setTranslationX(450);
                detectCurrentRed().setAlpha(1f);
                currentRed++;
                turn++;
                gameBoard[2][2 - middle] = 1;
                middle++;
            } else {

                detectCurrentYellow().setTranslationY(-500f);

                if (middle == 0)
                    detectCurrentYellow().animate().translationYBy(1400).setDuration(1000);
                else if (middle == 1)
                    detectCurrentYellow().animate().translationYBy(900).setDuration(1000);
                else if (middle == 2)
                    detectCurrentYellow().animate().translationYBy(400).setDuration(1000);

                detectCurrentYellow().setTranslationX(450);
                detectCurrentYellow().setAlpha(1f);
                currentYellow++;
                turn--;
                gameBoard[2][2 - middle] = 2;
                middle++;
            }
        }

    }
    public void leftClick(View view)
    {
        Log.i("Info:", "Left box pressed");
        if (currentYellow <= 5 || currentRed <= 5) {
            if (turn == 0) {

                detectCurrentRed().setTranslationX(0);
                detectCurrentRed().setAlpha(1f);
                detectCurrentRed().animate().translationYBy(2000).setDuration(1000);
                currentRed++;
                turn++;

            } else {

                detectCurrentYellow().setTranslationX(0);
                detectCurrentYellow().setTranslationY(-500f);
                detectCurrentYellow().setAlpha(1f);
                detectCurrentYellow().animate().translationYBy(2000).setDuration(1000);
                currentYellow++;
                turn--;

            }
        }

    }

    public ImageView detectCurrentRed()
    {
        ImageView v = (ImageView) findViewById(R.id.red1);
        if (currentRed <= 5) {
            switch (currentRed) {
                case 1:
                    v = red1;
                    break;
                case 2:
                    v = red2;
                    break;
                case 3:
                    v = red3;
                    break;
                case 4:
                    v = red4;
                    break;
                case 5:
                    v = red5;
                    break;

            }
        }
        return v;
    }

    public ImageView detectCurrentYellow()
    {
        ImageView v = (ImageView) findViewById(R.id.yellow1);
        if (currentYellow <= 5) {
            switch (currentYellow) {
                case 1:
                    v = yellow1;
                    break;
                case 2:
                    v = yellow2;
                    break;
                case 3:
                    v = yellow3;
                    break;
                case 4:
                    v = yellow4;
                    break;
                case 5:
                    v = yellow5;
                    break;

            }
        }
        return v;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentRed = 1;
        currentYellow = 1;
        turn = 0;

        red1 = (ImageView) findViewById(R.id.red1);
        red2 = (ImageView) findViewById(R.id.red2);
        red3 = (ImageView) findViewById(R.id.red3);
        red4 = (ImageView) findViewById(R.id.red4);
        red5 = (ImageView) findViewById(R.id.red5);

        red1.setAlpha(0f);
        red2.setAlpha(0f);
        red3.setAlpha(0f);
        red4.setAlpha(0f);
        red5.setAlpha(0f);

        yellow1 = (ImageView) findViewById(R.id.yellow1);
        yellow2 = (ImageView) findViewById(R.id.yellow2);
        yellow3 = (ImageView) findViewById(R.id.yellow3);
        yellow4 = (ImageView) findViewById(R.id.yellow4);
        yellow5 = (ImageView) findViewById(R.id.yellow5);

        yellow1.setAlpha(0f);
        yellow2.setAlpha(0f);
        yellow3.setAlpha(0f);
        yellow4.setAlpha(0f);
        yellow5.setAlpha(0f);

        gameBoard = new int [3] [3];
        for (int i = 0; i < gameBoard.length - 1; i++)
            for (int k = 0; i < gameBoard.length - 1; i++)
                gameBoard[i][k] = 0;
        // a slot on gameBoard being 0 means empty 1 means red and 2 means yellow
        right = 0;
        middle = 0;
        left = 0;
    }
}
