package com.example.khoaphamclass07092020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import static com.example.khoaphamclass07092020.PlayingActivity.mArrayListAnimal;

public class RandomAnimalActivity extends AppCompatActivity {

    GridView mGridViewAnimal;
    AnimalAdapter mAnimalAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_animal);
        initView();
        mGridViewAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentAnswer = new Intent();
                intentAnswer.putExtra("answer", mArrayListAnimal.get(i).getResourceIdImage());
                setResult( RESULT_OK , intentAnswer);
                finish();
            }
        });
    }

    private void initView() {
        mGridViewAnimal = findViewById(R.id.gridViewAnimal);
        mAnimalAdapter = new AnimalAdapter(this, mArrayListAnimal);
        mGridViewAnimal.setAdapter(mAnimalAdapter);
    }
}