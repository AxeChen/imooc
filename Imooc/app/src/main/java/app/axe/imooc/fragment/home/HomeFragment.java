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

import app.axe.imooc.R;
import app.axe.imooc.adapter.home.HomeFragmentRecyclerViewAdapter;
import app.axe.imooc.fragment.base.BaseFragment;
import app.axe.imooc.module.recommend.BaseRecommandModel;
import app.axe.imooc.network.UrlsContants;
import app.axe.imooc.network.home.HomeRequestUtils;
import app.axe.support.universalimageloader.ImageLoaderManager;

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

    private BaseRecommandModel mRecommandModel;
    private ImageLoaderManager mImageLoaderManager;

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
        mImageLoaderManager = ImageLoaderManager.getInstance(getContext().getApplicationContext());
        setAdapter();
        mAdapter.addLoadFrist();
        loadData();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.home_fragment_recyclerview);
        mSwipeRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.home_fragment_swiperefreshlayout);
        initSwipeView();
    }

    private void initScrollView(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    mImageLoaderManager.imageLoader.pause();
                }else{
                    mImageLoaderManager.imageLoader.resume();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initSwipeView() {
        mSwipeRefresh.setColorSchemeResources(R.color.homTabSelectLight);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }

    private void setAdapter() {
        mAdapter = new HomeFragmentRecyclerViewAdapter(mActivity, mImageLoaderManager);
        mRecyclerView.setLayoutManager(mLinerManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        HomeRequestUtils.getTrending(UrlsContants.TRENDING, null, new HomeRequestUtils.HomeRequestListener() {
            public void getRecommendModel(BaseRecommandModel recommandModel) {
                //展示数据
                if (recommandModel != null) {
                    mRecommandModel = recommandModel;
                }
                mAdapter.removeLoadFrist();
                mAdapter.addRecommandList(mRecommandModel.data.getList());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {

            }
        });
    }


}
