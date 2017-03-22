package app.axe.imooc.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.imooc.adapter.home.HomeViewPagerAdapter;
import app.axe.imooc.fragment.home.HomeFragment;
import app.axe.imooc.fragment.home.MessageFragment;
import app.axe.imooc.fragment.home.MineFragment;

/**
 * 创建首页所有的Fragment
 */
public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{


    private static final int MENU_SEARCH_ID = 0;

    private ImageView mIvHome;
    private ImageView mIvMessage;
    private ImageView mIvMe;

    private RelativeLayout mRlHome;
    private RelativeLayout mRlMessage;
    private RelativeLayout mRlMe;

    private TextView mTvHome;
    private TextView mTvMessage;
    private TextView mTvMe;

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    private HomeViewPagerAdapter mAdapter;

    /**
     * 退出时页面的id
     */
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        initView();
        initActionBar();
        initFragment();
        initAdapter();
    }

    private void initView(){
        mRlHome = (RelativeLayout) findViewById(R.id.home_rl_home);
        mIvHome = (ImageView) findViewById(R.id.home_iv_home);
        mTvHome = (TextView) findViewById(R.id.home_tv_home);
        if(mRlHome!=null){
            mRlHome.setOnClickListener(this);
        }
        mRlMessage = (RelativeLayout) findViewById(R.id.home_rl_message);
        mIvMessage = (ImageView) findViewById(R.id.home_iv_message);
        mTvMessage = (TextView) findViewById(R.id.home_tv_message);
        if(mRlMessage!=null){
            mRlMessage.setOnClickListener(this);
        }
        mRlMe = (RelativeLayout) findViewById(R.id.home_rl_me);
        mIvMe = (ImageView) findViewById(R.id.home_iv_me);
        mTvMe = (TextView) findViewById(R.id.home_tv_me);
        if(mRlMe!=null){
            mRlMe.setOnClickListener(this);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.home_viewpager);
        if(mViewPager!=null){
            mViewPager.addOnPageChangeListener(this);
        }
    }

    private void initActionBar(){
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayShowTitleEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(false);
            mActionBar.setDisplayShowHomeEnabled(false);
            setActionBarTitle(index);
        }
    }

    private void initFragment(){
        mFragments = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mMessageFragment = new MessageFragment();
        mMineFragment = new MineFragment();
        mFragments.add(mHomeFragment);
        mFragments.add(mMessageFragment);
        mFragments.add(mMineFragment);
    }

    private void initAdapter(){
        mAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);
    }

    private void setActionBarTitle(int position){
        if(mActionBar == null){
            return;
        }
        switch (position){
            case 0:
                mActionBar.setTitle(getString(R.string.home_home));
                selectHome();
                break;
            case 1:
                mActionBar.setTitle(getString(R.string.home_message));
                selectMessage();
                break;
            case 2:
                mActionBar.setTitle(getString(R.string.home_mine));
                selectMe();
                break;
            default:
                mActionBar.setTitle("");
                break;
        }
    }

    private void selectHome(){
        mIvHome.setImageResource(R.drawable.subtitle_home);
        mIvMessage.setImageResource(R.drawable.subtitle_course_normal);
        mIvMe.setImageResource(R.drawable.subtitle_mine_normal);
        mTvHome.setTextColor(getResources().getColor(R.color.homTabSelectLight));
        mTvMessage.setTextColor(getResources().getColor(R.color.homTabLight));
        mTvMe.setTextColor(getResources().getColor(R.color.homTabLight));
    }

    private void selectMessage(){
        mIvMessage.setImageResource(R.drawable.subtitle_course);
        mIvHome.setImageResource(R.drawable.subtitle_home_normal);
        mIvMe.setImageResource(R.drawable.subtitle_mine_normal);
        mTvHome.setTextColor(getResources().getColor(R.color.homTabLight));
        mTvMessage.setTextColor(getResources().getColor(R.color.homTabSelectLight));
        mTvMe.setTextColor(getResources().getColor(R.color.homTabLight));
    }

    private void selectMe(){
        mIvMe.setImageResource(R.drawable.subtitle_mine);
        mIvHome.setImageResource(R.drawable.subtitle_home_normal);
        mIvMessage.setImageResource(R.drawable.subtitle_course_normal);
        mTvHome.setTextColor(getResources().getColor(R.color.homTabLight));
        mTvMessage.setTextColor(getResources().getColor(R.color.homTabLight));
        mTvMe.setTextColor(getResources().getColor(R.color.homTabSelectLight));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_SEARCH_ID,0,R.string.search).setIcon(R.drawable.ic_menu_search).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_SEARCH_ID:
                Toast.makeText(getApplicationContext(),"go to seach",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setActionBarTitle(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.home_rl_home:
                mViewPager.setCurrentItem(0);
                selectHome();
                break;

            case R.id.home_rl_message:
                mViewPager.setCurrentItem(1);
                selectMessage();
                break;

            case R.id.home_rl_me:
                mViewPager.setCurrentItem(2);
                selectMe();
                break;
        }
    }
}
