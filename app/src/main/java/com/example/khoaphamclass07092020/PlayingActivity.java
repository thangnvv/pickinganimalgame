package com.example.khoaphamclass07092020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayingActivity extends AppCompatActivity {
    TextView mTxtViewTimeLeft, mTxtViewRecentScore;
    ImageView mImgViewLookingAnimal, mImgViewAnswer;
    final int PICKING_ANSWER_REQUEST_CODE = 123;
    int challengeIndex;
    Random random;
    public static ArrayList<Animal> mArrayListAnimal;
    CountDownTimer mCountDownTimer;
    int idAnswer;
    int recentScore = 0;
    int timeLeftAtOut = 0;
    boolean atPlayingActivity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        initView();
        createAnimalList();
        makeRandom();
        startCountDown(10300);

        Log.d("DDD", "On Create Playing");

        mImgViewLookingAnimal.setImageResource(challengeIndex);
        mImgViewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(mArrayListAnimal);
                Intent intentPickAnswer = new Intent(PlayingActivity.this, RandomAnimalActivity.class);
                startActivityForResult(intentPickAnswer, PICKING_ANSWER_REQUEST_CODE);
            }
        });
    }

    private void startCountDown(int time) {
        mCountDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                mTxtViewTimeLeft.setText(String.valueOf(l/1000));
                if(atPlayingActivity == false){
                    timeLeftAtOut = (int) l;
                    mCountDownTimer.cancel();
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Out of time", Toast.LENGTH_LONG).show();
                Intent intentGameover = new Intent(PlayingActivity.this, GameoverDialogActivity.class);
                intentGameover.putExtra("Score", recentScore);
                startActivity(intentGameover);
            }
        };
        mCountDownTimer.start();
    }

    private void makeRandom() {
        if(random == null){
            random = new Random();
        }
        challengeIndex = mArrayListAnimal.get(random.nextInt(mArrayListAnimal.size())).getResourceIdImage();
    }

    private void createAnimalList() {
        mArrayListAnimal = new ArrayList<>();

        mArrayListAnimal.add(new Animal(R.drawable.bo));
        mArrayListAnimal.add(new Animal(R.drawable.bocanhcung));
        mArrayListAnimal.add(new Animal(R.drawable.bongua));
        mArrayListAnimal.add(new Animal(R.drawable.cachep));
        mArrayListAnimal.add(new Animal(R.drawable.cadia));
        mArrayListAnimal.add(new Animal(R.drawable.chimcanhcut));
        mArrayListAnimal.add(new Animal(R.drawable.cho));
        mArrayListAnimal.add(new Animal(R.drawable.chodom));
        mArrayListAnimal.add(new Animal(R.drawable.chuonchuon));
        mArrayListAnimal.add(new Animal(R.drawable.ech));
        mArrayListAnimal.add(new Animal(R.drawable.heo));
        mArrayListAnimal.add(new Animal(R.drawable.khi));
        mArrayListAnimal.add(new Animal(R.drawable.meoden));
        mArrayListAnimal.add(new Animal(R.drawable.meotrang));
        mArrayListAnimal.add(new Animal(R.drawable.rua));
        mArrayListAnimal.add(new Animal(R.drawable.soi));
        mArrayListAnimal.add(new Animal(R.drawable.voi));
    }

    private void initView() {
        mTxtViewTimeLeft        = findViewById(R.id.textViewTimeLeft);
        mTxtViewRecentScore     = findViewById(R.id.textViewRecentScore);
        mImgViewAnswer          = findViewById(R.id.imageViewAnswer);
        mImgViewLookingAnimal   = findViewById(R.id.imageViewLookingAnimal);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICKING_ANSWER_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            idAnswer = data.getIntExtra("answer", -1);
            mImgViewAnswer.setImageResource(idAnswer);
            if(idAnswer == challengeIndex ) {
                recentScore += 3;
                mTxtViewRecentScore.setText(recentScore +"");
                makeRandom();
                while(challengeIndex == idAnswer){
                    makeRandom();
                }
                mImgViewLookingAnimal.setImageResource(challengeIndex);
                mCountDownTimer.cancel();
                startCountDown(10300);
            }else{
                Toast.makeText(getApplicationContext(), "Wrong Animal", Toast.LENGTH_LONG).show();
                mCountDownTimer.cancel();
                Intent intentGameover = new Intent(PlayingActivity.this, GameoverDialogActivity.class);
                intentGameover.putExtra("Score", recentScore);
                startActivity(intentGameover);
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DDD", "On Stop Playing");
        atPlayingActivity = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DDD", "On Start Playing: " + timeLeftAtOut );
        if(timeLeftAtOut != 0){
            atPlayingActivity = true;
            startCountDown(timeLeftAtOut);
        }
    }


}