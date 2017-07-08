package com.demo.mrma.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myHolder>
{

    private LayoutInflater mInflater;
    private List<Integer> mDatas;

    public MyAdapter(Context context, List<Integer> datas)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    public class myHolder extends RecyclerView.ViewHolder
    {
        private ImageView mImg;

        public myHolder(View v)
        {
            super(v);
            mImg = v.findViewById(R.id.style_01);
        }

    }

    /**
     * 创建ViewHolder
     */
    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int ViewType)
    {
        View view = mInflater.inflate(R.layout.list_view, parent, false);
        return new myHolder(view);
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(myHolder viewHolder, int i)
    {
        viewHolder.mImg.setImageResource(mDatas.get(i));
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

}
