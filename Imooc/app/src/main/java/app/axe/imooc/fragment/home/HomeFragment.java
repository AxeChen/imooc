package app.axe.imooc.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.axe.imooc.R;
import app.axe.imooc.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/22 0022.
 * Show Trending list.
 */

public class HomeFragment extends BaseFragment{

    private View mView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_layout,container,false);
        initView();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(){
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.home_fragment_recyclerview);
        mSwipeRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.home_fragment_swiperefreshlayout);
    }
}
