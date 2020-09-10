package com.example.khoaphamclass07092020;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class GameoverDialogActivity extends Activity {

    EditText mEdtTextPlayer;
    Button mBtnSaveScore, mBtnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover_dialog);
        this.setFinishOnTouchOutside(false);

        mEdtTextPlayer = findViewById(R.id.editTextPlayer);
        mBtnNewGame    = findViewById(R.id.buttonNewGame);
        mBtnSaveScore  = findViewById(R.id.buttonSaveScore);

        mBtnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewGame = new Intent(GameoverDialogActivity.this, PlayingActivity.class);
                intentNewGame.addFlags(FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentNewGame);

            }
        });

        mBtnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerName = mEdtTextPlayer.getText().toString();
                if(playerName.trim().length() > 0) {
                    Intent intentGetScore = getIntent();
                    int playerScore = intentGetScore.getIntExtra("Score", 0);
                    Toast.makeText(GameoverDialogActivity.this, "Save Score Successfull", Toast.LENGTH_LONG).show();
                    Intent intentScoreBoard = new Intent(GameoverDialogActivity.this, ScoreBoardActivity.class);
                    intentScoreBoard.putExtra("score", playerScore);
                    intentScoreBoard.putExtra("playername", playerName);
                    intentScoreBoard.addFlags(FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentScoreBoard);
                    finish();
                }
                else{
                    Toast.makeText(GameoverDialogActivity.this, "Insert your name to save the score", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}