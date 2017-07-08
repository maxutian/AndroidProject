package com.demo.mrma.demo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myHolder>
{
    public static class myHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView stylePhoto;

        myHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.allStyles);
            stylePhoto = itemView.findViewById(R.id.style_pic);
        }
    }

    List<Styles> styles;

    MyAdapter(List<Styles> styles){
        this.styles = styles;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        myHolder mh = new myHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(myHolder ViewHolder, int i) {
        ViewHolder.stylePhoto.setImageResource(styles.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return styles.size();
    }

}
