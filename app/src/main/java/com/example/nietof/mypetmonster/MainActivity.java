package com.example.nietof.mypetmonster;

import android.media.MediaPlayer;
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


    //using  3rd party prog. bar, see usage: https://github.com/MasayukiSuda/AnimateHorizontalProgressBar
    private AnimateHorizontalProgressBar foodProgressBar;
    private AnimateHorizontalProgressBar waterProgressBar;
    private AnimateHorizontalProgressBar loveProgressBar;

    boolean isCounterRunning  = false;

    CountDownTimer timer;

    //wellnessTotal will be the sum of all indicators, therefore easier to manage monster state
    private int wellnessTotal = 0;

    //this class handles the sounds
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monsterView = findViewById(R.id.monster_view) ;
        monsterView.setImageResource(R.drawable.monster_sparkle);

        monsterText = findViewById(R.id.monster_status_text);
        monsterText.setText("Hi! Give me food, water and love to keep me alive!");

        foodBtn = findViewById(R.id.btn_add_food);
        loveBtn = findViewById(R.id.btn_add_love);
        waterBtn = findViewById(R.id.btn_add_water);


        //initialize values here
        waterAmt =100;
        foodAmt = 100;
        loveAmt =100;



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

                //don't allow amounts to go below zero
                if (foodAmt>=0) foodAmt= foodAmt-1;
                if (waterAmt>=0) waterAmt = waterAmt-1;
                if (loveAmt>=0) loveAmt = loveAmt-1;
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
        playSound("playChew");

    }



    public void addLove(View view){

        loveAmt= loveAmt+15;
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


        wellnessTotal= waterAmt + foodAmt+loveAmt;
        Log.d("wellness" , "total " +wellnessTotal);


        //change the imageView



        if(wellnessTotal >250 ){

            monsterView.setImageResource(R.drawable.monster_sparkle);
        } else if (wellnessTotal <=250 && wellnessTotal >200){

            monsterView.setImageResource(R.drawable.monster_annoyed);

        } else if (wellnessTotal<=200 && wellnessTotal > 150){

            monsterView.setImageResource(R.drawable.monster_sad);
        } else if (wellnessTotal<=150 && wellnessTotal > 100){

            monsterView.setImageResource(R.drawable.monster_crying);

        } else if (wellnessTotal<=100 && wellnessTotal >50){

            monsterView.setImageResource(R.drawable.monster_despair);
        } else if (wellnessTotal<=50 && wellnessTotal >0){

            monsterView.setImageResource(R.drawable.monster_sick);
        } else if(wellnessTotal<=0){

            monsterView.setImageResource(R.drawable.monster_dead);
            //stop time
            timer.cancel();
            monsterText.setText("You killed me! you are the monster");

        }





        //update the progress bars
        loveProgressBar.setProgressWithAnim(loveAmt);
        waterProgressBar.setProgressWithAnim(waterAmt);
        foodProgressBar.setProgressWithAnim(foodAmt);




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

    //sound methods

    public void playSound(String sound){


        //switch loads a different sound from raw directory
        switch(sound){

            case "playChew":

                mediaPlayer = MediaPlayer.create(this, R.raw.chewing);
                Log.d("playSound" , "playChew");

                break;

        }

        //lifecyle methods for MediaPlayer. Must release them to save resources.
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mediaPlayer.release();
            }
        });
    }


}
