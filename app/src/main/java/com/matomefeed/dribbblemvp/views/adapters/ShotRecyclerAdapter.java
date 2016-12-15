package com.matomefeed.dribbblemvp.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.matomefeed.dribbblemvp.R;
import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.views.components.OnListItemClickedListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by takusemba on 2016/11/01.
 */

public class ShotRecyclerAdapter extends RecyclerView.Adapter<ShotRecyclerAdapter.ItemViewHolder> {

    private List<Shot> shots;
    private Context context;
    private OnListItemClickedListener listener;

    public ShotRecyclerAdapter(Context context, List<Shot> shots, OnListItemClickedListener listener) {
        super();
        this.listener = listener;
        this.context = context;
        this.shots = shots;
    }


    @Override
    public ShotRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shot, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShotRecyclerAdapter.ItemViewHolder holder, final int position) {
        Shot shot = shots.get(position);
        if (shot.image.normal != null) {
            try {
                Picasso.with(context)
                        .load(shot.image.normal)
                        .fit().centerCrop()
                        .into(holder.image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shots.size();
    }

    public void addAll(List<Shot> shots) {
        this.shots.addAll(shots);
        notifyDataSetChanged();
    }

    public void clear() {
        shots.clear();
        notifyDataSetChanged();
    }

    public Shot getItem(int position) {
        return shots.get(position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
