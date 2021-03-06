package app.axe.imooc.ui.home;

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
import app.axe.imooc.adapter.home.HomeRecommendAdapter;
import app.axe.imooc.customui.recyclerview.RecyclerViewDivider;
import app.axe.imooc.ui.base.BaseFragment;
import app.axe.imooc.module.recommand.BaseRecommandModel;
import app.axe.imooc.module.recommand.RecommandHeadValue;
import app.axe.imooc.network.RequestCenter;
import app.axe.support.okhttp.listener.DisposeDataListener;
import app.axe.support.universalimageloader.ImageLoaderManager;

/**
 * Created by Chen on 2017/3/22 0022.
 * Show Trending list.
 */

public class HomeFragment extends BaseFragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;
    private HomeRecommendAdapter mAdapter;
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
        initScrollView();
        initSwipeView();
    }

    private void initScrollView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mImageLoaderManager.imageLoader.pause();
                } else {
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
        mAdapter = new HomeRecommendAdapter(mActivity, mImageLoaderManager);
        mRecyclerView.setLayoutManager(mLinerManager);
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addAutoViewPager() {
        RecommandHeadValue headValue = mRecommandModel.data.head;
        mAdapter.addHeadView(headValue);
    }

    private void loadData() {
//        HomeRequestUtils.getTrending(UrlsContants.TRENDING, null, new HomeRequestUtils.HomeRequestListener() {
//            public void getRecommendModel(BaseRecommandModel recommandModel) {
//                //展示数据
//                if (recommandModel != null) {
//                    mRecommandModel = recommandModel;
//                }
//                mAdapter.removeLoadFrist();
//                mAdapter.addRecommandList(mRecommandModel.data.getList());
//                addAutoViewPager();
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure() {
//
//            }
//        });

        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mRecommandModel = (BaseRecommandModel) responseObj;
                //更新UI
                mAdapter.removeLoadFrist();
                mAdapter.addRecommandList(mRecommandModel.data.list);
                addAutoViewPager();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
            }
        });
    }


}
