package app.axe.imooc.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.imooc.activity.ProductDetailActivity;
import app.axe.imooc.adapter.base.CommonPagerAdapter;

/**
 * Created by Chen on 2017/5/20.
 */

public class ProductMainFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private View mView;
    private ProductDetailActivity mActivity;

    private CommonPagerAdapter commonPagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product_main, container, false);
        initView();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (ProductDetailActivity) getActivity();
        initFragment();
        setAdapter();
    }

    private void initView() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) mView.findViewById(R.id.viewPager);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    private void initFragment() {
        ProductDetailFragment detailFragment = new ProductDetailFragment();
        ProductLessonsFragment lessonsFragment = new ProductLessonsFragment();
        ProductCommentFragment commentFragment = new ProductCommentFragment();
        fragments.add(detailFragment);
        fragments.add(lessonsFragment);
        fragments.add(commentFragment);
    }

    private void setAdapter() {
        commonPagerAdapter = new CommonPagerAdapter(getFragmentManager(), fragments);
        mViewPager.setAdapter(commonPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            if (i == 0) {
                mTabLayout.getTabAt(i).setText("课程介绍");
            } else if (i == 1) {
                mTabLayout.getTabAt(i).setText("课程章节");
            } else {
                mTabLayout.getTabAt(i).setText("课程评论");
            }
        }
    }
}
