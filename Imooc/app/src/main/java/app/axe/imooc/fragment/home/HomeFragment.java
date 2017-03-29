package app.axe.imooc.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.imooc.adapter.home.HomeFragmentRecyclerViewAdapter;
import app.axe.imooc.bean.TrendingBean;
import app.axe.imooc.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/22 0022.
 * Show Trending list.
 */

public class HomeFragment extends BaseFragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;
    private HomeFragmentRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLinerManager;

    private List<Object> trendings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AppCompatActivity) getActivity();
        mLinerManager = new LinearLayoutManager(mActivity);
        loadingData();
        setAdapter();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.home_fragment_recyclerview);
        mSwipeRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.home_fragment_swiperefreshlayout);
        initSwipeView();
    }

    private void initSwipeView(){
        mSwipeRefresh.setColorSchemeResources(R.color.homTabSelectLight);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    private void loadingData() {
        trendings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TrendingBean bean = new TrendingBean();
            bean.setTitle("title"+i);
            trendings.add(bean);
        }
    }

    private void setAdapter(){
        mAdapter = new HomeFragmentRecyclerViewAdapter(mActivity);
        mRecyclerView.setLayoutManager(mLinerManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addItems(trendings);
    }



}
