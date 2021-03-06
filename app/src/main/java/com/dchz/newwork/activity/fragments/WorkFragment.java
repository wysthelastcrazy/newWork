package com.dchz.newwork.activity.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseFragment;
import com.dchz.newwork.adapter.AdapterPendingOrder;
import com.dchz.newwork.common.Global;
import com.dchz.newwork.component.HeaderSearchView;
import com.dchz.newwork.component.HeaderSelectView;
import com.dchz.newwork.listener.ITabChangeListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 */

public class WorkFragment extends BaseFragment {
    private HeaderSelectView mHeader;
    private XRecyclerView xrvVisiting;
    private AdapterPendingOrder mPendingAdapter;
    private HeaderSearchView mSearchView;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_work;
    }

    @Override
    public void initViews(View rootView) {
        mHeader=rootView.findViewById(R.id.header_work);
        mHeader.addTabChangeListener(mTabChangeListener);
        mHeader.setTabTitle("待办事务", "已办事务");

        mSearchView=rootView.findViewById(R.id.header_search);
        mSearchView.setHint("申请人/事务标题/事务类型");
        mSearchView.addTextChangeListener(mSearchTextChangeListener);

        xrvVisiting=rootView.findViewById(R.id.xrv_visiting);
        mPendingAdapter=new AdapterPendingOrder(getActivity(),getLeftData());
        xrvVisiting.setAdapter(mPendingAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvVisiting.setLayoutManager(linearLayoutManager);
        xrvVisiting.setLoadingListener(mLoadingListener);
    }

    /**
     * 下拉刷新，上了加载更多监听
     */
    private XRecyclerView.LoadingListener mLoadingListener=new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            Global.post2UIDelay(new Runnable() {
                @Override
                public void run() {
                    xrvVisiting.refreshComplete();
                }
            },1000);
        }

        @Override
        public void onLoadMore() {
            Global.post2UIDelay(new Runnable() {
                @Override
                public void run() {
                    xrvVisiting.loadMoreComplete();
                    mPendingAdapter.appendList(getRightData());
                }
            },1000);
            xrvVisiting.setLoadingMoreEnabled(false);
        }
    };
    /**
     * 头部tab切换监听
     */
    private ITabChangeListener mTabChangeListener=new ITabChangeListener() {
        @Override
        public void leftTabClick() {
            mPendingAdapter.reSetList(getLeftData());
            mSearchView.clear();
        }

        @Override
        public void rightTabClick() {
            mPendingAdapter.reSetList(getRightData());
            mSearchView.clear();
        }
    };
    /**
     * 搜索栏文本变化监听
     */
    private TextWatcher mSearchTextChangeListener=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private ArrayList<String> getLeftData(){
        ArrayList<String> mList=new ArrayList<>();
        for (int i=1;i<11;i++){
            mList.add("left"+i);
        }
        return mList;
    }
    private ArrayList<String> getRightData(){
        ArrayList<String> mList=new ArrayList<>();
        for (int i=1;i<11;i++){
            mList.add("right"+i);
        }
        return mList;
    }
}
