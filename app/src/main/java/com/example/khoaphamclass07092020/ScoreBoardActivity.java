package com.example.khoaphamclass07092020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class ScoreBoardActivity extends AppCompatActivity {

    ScoreListAdapter mScoreListAdapter;
    RecyclerView mRecyclerViewHighScore;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Score> mScoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        Hawk.init(getApplicationContext()).build();
        mScoreList = Hawk.get("scoreboard", new ArrayList<Score>() ) ;
        mRecyclerViewHighScore = findViewById(R.id.recyclerViewHighScore);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewHighScore.setLayoutManager(mLayoutManager);
        mScoreListAdapter = new ScoreListAdapter(ScoreBoardActivity.this, mScoreList);
        mRecyclerViewHighScore.setAdapter(mScoreListAdapter);

        Intent intentGetScore = getIntent();
        if(intentGetScore != null){
            String playerName = intentGetScore.getStringExtra("playername");
            int playerScore = intentGetScore.getIntExtra("score", -1);
            if (playerScore == -1){
                Log.d("DDD", "Something Wrong in transfer score");
            }
            addNewScore(playerName, playerScore);
        }
    }

    private void addNewScore(String playerName, int playerScore) {
        for (int i = 0; i < mScoreList.size(); i++) {
            if (Integer.parseInt(mScoreList.get(i).getScore()) < playerScore) {
                int numberKeeper = 0;
                String nameKeeper = "";
                numberKeeper = Integer.parseInt(mScoreList.get(i).getScore());
                mScoreList.get(i).setScore(playerScore + "");
                playerScore = numberKeeper;
                nameKeeper = mScoreList.get(i).getName();
                mScoreList.get(i).setName(playerName);
                playerName = nameKeeper;
            }
        }
        mScoreList.add(new Score(String.valueOf(mScoreList.size()+ 1), playerName, String.valueOf(playerScore)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentGoToHome = new Intent(ScoreBoardActivity.this, HomeActivity.class);
        intentGoToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentGoToHome);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Hawk.put("scoreboard", mScoreList);
    }

}