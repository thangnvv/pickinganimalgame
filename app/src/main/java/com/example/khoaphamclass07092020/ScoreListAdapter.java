package com.example.khoaphamclass07092020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreListViewHolder> {

    private ArrayList<Score> mArrayListScore;
    private LayoutInflater mLayoutInFlater;

    public ScoreListAdapter(Context context, ArrayList<Score> mArrayListScore) {
        this.mArrayListScore = mArrayListScore;
        this.mLayoutInFlater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoreListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInFlater.inflate(R.layout.score_layout, parent, false);
        return new ScoreListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreListViewHolder holder, int position) {
        Score score = mArrayListScore.get(position);
        holder.txtViewPlayer.setText(score.getName());
        holder.txtViewScore.setText(score.getScore());
        holder.txtViewRank.setText(score.getRank());
    }

    @Override
    public int getItemCount() {
        return mArrayListScore.size();
    }

    public class ScoreListViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewRank, txtViewPlayer, txtViewScore;

        public ScoreListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewPlayer = itemView.findViewById(R.id.textViewPlayer);
            txtViewRank   = itemView.findViewById(R.id.textViewRank);
            txtViewScore  = itemView.findViewById(R.id.textViewScore);

        }
    }
}
