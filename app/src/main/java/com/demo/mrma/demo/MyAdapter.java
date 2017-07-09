package com.demo.mrma.demo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myHolder> implements View.OnClickListener
{

    //    设置数据来源
    private List<Styles> styles;

    public MyAdapter(List<Styles> styles){
        this.styles = styles;
    }

    //    item点击事件
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }


    //    设置item数据
    public static class myHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView stylePhoto;

        public myHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.allStyles);
            stylePhoto = itemView.findViewById(R.id.style_pic);
        }

    }

    //    viewholder与cardview绑定
    @Override
    public myHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        myHolder mh = new myHolder(v);
        v.setOnClickListener(this);
        return mh;
    }

    @Override
    public void onBindViewHolder(myHolder ViewHolder, int position) {
        ViewHolder.stylePhoto.setImageResource(styles.get(position).photoId);
        //将position保存在itemView的Tag中，以便点击时进行获取
        ViewHolder.itemView.setTag(position);
    }

    //    与recyclerView绑定
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return styles.size();
    }

}
