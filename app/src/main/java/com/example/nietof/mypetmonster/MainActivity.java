package com.example.nietof.mypetmonster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ImageView monsterView;
    private TextView monsterText ;
    private ProgressBar foodProgress;
    private ProgressBar loveProgress;
    private ProgressBar waterProgress;
    private ImageButton foodBtn;
    private ImageButton loveBtn;
    private ImageButton waterBtn;

    private int foodAmt;
    private int waterAmt;
    private int loveAmt;




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



        waterAmt =10;
        foodAmt = 10;
        loveAmt =10;

        foodProgress.setProgress(foodAmt);
        loveProgress.setProgress(loveAmt);
        waterProgress.setProgress(waterAmt);

    }


    public void addFood(View view){

        foodAmt= foodAmt+10;
        Log.d("addFood" , " "+ foodAmt);


        foodProgress.setProgress(foodAmt);
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
