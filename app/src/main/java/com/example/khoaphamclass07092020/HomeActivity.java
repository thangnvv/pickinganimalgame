package com.example.khoaphamclass07092020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button mButtonStartGame, mButtonHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButtonStartGame    = findViewById(R.id.buttonStartGame);
        mButtonHighScore    = findViewById(R.id.buttonHighScore);

        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStartGame = new Intent(HomeActivity.this, PlayingActivity.class);
                startActivity(intentStartGame);
            }
        });

        mButtonHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHighScore = new Intent(HomeActivity.this, ScoreBoardActivity.class);
                startActivity(intentHighScore);
            }
        });

    }


}