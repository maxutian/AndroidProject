package com.demo.mrma.demo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Styles> styles;
    private Context context;

    public MyAdapter(Context context, List<Styles> styles){
        this.styles = styles;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.iv.setImageResource(styles.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return styles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv;

        public ViewHolder(View view) {
            super(view);

            iv = view.findViewById(R.id.style_pic);
            view.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "haha", Toast.LENGTH_SHORT).show();
        }
    }


}

