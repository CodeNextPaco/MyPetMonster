package com.example.nietof.mypetmonster;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daasuu.ahp.AnimateHorizontalProgressBar;


public class MainActivity extends AppCompatActivity {


    private ImageView monsterView;
    private TextView monsterText ;

    private ImageButton foodBtn;
    private ImageButton loveBtn;
    private ImageButton waterBtn;

    private int foodAmt;
    private int waterAmt;
    private int loveAmt;

    private Handler handler = new Handler();


    //using this 3rd party prog. bar : https://github.com/MasayukiSuda/AnimateHorizontalProgressBar
    private AnimateHorizontalProgressBar foodProgressBar;
    private AnimateHorizontalProgressBar waterProgressBar;
    private AnimateHorizontalProgressBar loveProgressBar;

    boolean isCounterRunning  = false;

    private String lowestValue;

    CountDownTimer timer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monsterView = findViewById(R.id.monster_view) ;
        monsterText = findViewById(R.id.monster_status_text);

        foodBtn = findViewById(R.id.btn_add_food);
        loveBtn = findViewById(R.id.btn_add_love);
        waterBtn = findViewById(R.id.btn_add_water);



        waterAmt =70;
        foodAmt = 30;
        loveAmt =40;


        //setup the food progress bar
        foodProgressBar =  findViewById(R.id.food_progress_bar);
        foodProgressBar.setMax(100);
        foodProgressBar.setProgress(foodAmt);

        waterProgressBar = findViewById(R.id.water_progress_bar);
        waterProgressBar.setMax(100);
        waterProgressBar.setProgress(waterAmt);

        loveProgressBar = findViewById(R.id.love_progress_bar);
        loveProgressBar.setMax(100);
        loveProgressBar.setProgress(waterAmt);

        timer = new CountDownTimer(500000, 5000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                Log.d("timer", "seconds remaining: " + millisUntilFinished / 1000);

                foodAmt= foodAmt-1;
                waterAmt = waterAmt-1;
                loveAmt = loveAmt-1;
                updateMonster();



            }

            public void onFinish() {
                Log.d("timer ", "done!");
                isCounterRunning = false;
            }
        }.start();

        updateMonster();



    }


    public void addFood(View view){

        foodAmt= foodAmt+15;


        updateMonster();
        restartTimer();

    }



    public void addLove(View view){

        loveAmt= loveAmt+15;
        Log.d("addLove" , " "+ loveAmt);

        updateMonster();
        restartTimer();



    }
    public void addWater(View view){

        waterAmt= waterAmt+15;
        Log.d("addWater" , " "+ waterAmt);

        updateMonster();
        restartTimer();



    }


    public void updateMonster(){




        //change the imageView



        if(waterAmt < 50 || loveAmt < 50 || foodAmt < 50){

            monsterView.setImageResource(R.drawable.monster_crying);
        } else {

            monsterView.setImageResource(R.drawable.monster_sparkle);


        }

        loveProgressBar.setProgressWithAnim(loveAmt);
        waterProgressBar.setProgressWithAnim(waterAmt);
        foodProgressBar.setProgressWithAnim(foodAmt);

        if(waterAmt < loveAmt && waterAmt <foodAmt){

            monsterText.setText(R.string.need_water);
        } else if( foodAmt <loveAmt && foodAmt < waterAmt){

            monsterText.setText(R.string.need_food);
        } else if (loveAmt< foodAmt && loveAmt <waterAmt) {

            monsterText.setText(R.string.need_love);
        }

        if(waterAmt<=0 || foodAmt <=0 || loveAmt <=0){

            monsterView.setImageResource(R.drawable.monster_dead);
            monsterText.setText("You killed me! you are the monster");

        }




    }

    public void restartTimer(){

        if( !isCounterRunning ){
            isCounterRunning = true;
            timer.start();
        }
        else{
            timer.cancel(); // cancel
            timer.start();  // then restart
        }
    }


}
