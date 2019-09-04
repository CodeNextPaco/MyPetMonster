package com.example.nietof.mypetmonster;

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
    private  ProgressBar foodProgress;
    private ProgressBar loveProgress;
    private ProgressBar waterProgress;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monsterView = findViewById(R.id.monster_view) ;
        monsterText = findViewById(R.id.monster_status_text);
        foodProgress = findViewById(R.id.food_progressBar);
        loveProgress = findViewById(R.id.love_progressBar);
        waterProgress = findViewById(R.id.water_progressBar);
        foodBtn = findViewById(R.id.btn_add_food);
        loveBtn = findViewById(R.id.btn_add_love);
        waterBtn = findViewById(R.id.btn_add_water);



        waterAmt =20;
        foodAmt = 30;
        loveAmt =40;

        foodProgress.setProgress(foodAmt);
        loveProgress.setProgress(loveAmt);
        waterProgress.setProgress(waterAmt);




        foodProgressBar =  findViewById(R.id.animate_progress_bar);
        foodProgressBar.setMax(100);
        foodProgressBar.setProgress(40);











    }


    public void addFood(View view){

        foodAmt= foodAmt+5;

        foodProgressBar.setProgressWithAnim(foodAmt);



    }







    public void addLove(View view){

        loveAmt= loveAmt+10;
        Log.d("addLove" , " "+ loveAmt);


        loveProgress.setProgress(foodAmt);
    }
    public void addWater(View view){

        waterAmt= waterAmt+10;
        Log.d("addWater" , " "+ waterAmt);


        waterProgress.setProgress(waterAmt);
    }
}
