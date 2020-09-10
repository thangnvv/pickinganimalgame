package com.example.khoaphamclass07092020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class AnimalAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Animal> mArrayListAnimal;
    ImageView mImageViewAnimal;

    public AnimalAdapter(Context context, ArrayList<Animal> mArrayListAnimal) {
        this.context = context;
        this.mArrayListAnimal = mArrayListAnimal;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Animal> getmArrayListAnimal() {
        return mArrayListAnimal;
    }

    public void setmArrayListAnimal(ArrayList<Animal> mArrayListAnimal) {
        this.mArrayListAnimal = mArrayListAnimal;
    }

    @Override
    public int getCount() {
        return mArrayListAnimal.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Animal animal = mArrayListAnimal.get(i);

        view = LayoutInflater.from(context).inflate(R.layout.animal_layout, null);
        mImageViewAnimal = view.findViewById(R.id.imageViewAnimalImage);
        mImageViewAnimal.setImageResource(animal.getResourceIdImage());

        return view;

    }
}
