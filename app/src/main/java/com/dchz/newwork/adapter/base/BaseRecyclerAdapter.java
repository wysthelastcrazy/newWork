package com.dchz.newwork.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.dchz.newwork.msglist.base.BaseViewHolder;
import com.dchz.newwork.util.MyLog;

import java.util.ArrayList;

/**
 * Created by yas on 2017/7/19.
 */

public abstract class BaseRecyclerAdapter<VM extends BaseViewHolder,T>extends RecyclerView.Adapter{
    protected Context mContext;
    private ArrayList<T> mList;
    public BaseRecyclerAdapter(Context mContext, ArrayList<T> mList) {
        this.mContext=mContext;
        this.mList = mList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        BaseViewHolder baseViewHolder= (BaseViewHolder) holder;
        VM vm= (VM) holder;
        if (vm!=null){
            vm.setPos(position);
            vm.setSize(getItemCount());
            vm.initView();
            vm.setMsg(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }


    public void appendList(ArrayList<T> mList){
        if(this.mList == null){
            this.mList = mList;
        }else{
            this.mList.addAll(mList);
        }
        notifyDataSetChanged();
    }

    public void reSetList(ArrayList<T> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    protected abstract VM getViewHolder(ViewGroup parent);

}
