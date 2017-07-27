package com.demo.mrma.demo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Styles> styles;
    private Context context;
    private String[] styleName;
    private int[] useTime;


    public MyAdapter(Context context, List<Styles> styles, String[] styleName, int[] useTime){
        this.styles = styles;
        this.context = context;
        this.useTime = useTime;
        this.styleName = styleName;
    }

    public void updateData(int[] newUseTime) {
        this.useTime = newUseTime;
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.iv.setImageResource(styles.get(i).photoId);
        viewHolder.tv.setText(String.valueOf(useTime[i]));
    }

    @Override
    public int getItemCount() {
        return styles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv;
        private TextView tv;

        public ViewHolder(View view) {
            super(view);

            iv = view.findViewById(R.id.style_pic);
            tv = view.findViewById(R.id.count_num);
            view.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            Toast.makeText(context, styleName[getAdapterPosition()], Toast.LENGTH_SHORT).show();
            useTime[getAdapterPosition()] += 1;
            updateData(useTime);
        }
    }

}

