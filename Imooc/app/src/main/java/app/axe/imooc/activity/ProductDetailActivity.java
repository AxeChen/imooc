package app.axe.imooc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import app.axe.imooc.R;
import app.axe.imooc.ui.product.ProductDetailFragment;
import app.axe.imooc.ui.product.ProductMainFragment;
import app.axe.support.universalimageloader.ImageLoaderManager;

/**
 * Created by Chen on 2017/5/20.
 */

public class ProductDetailActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private ImageView mBgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        setActionBar();
        initMainFragment();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mBgView = (ImageView) findViewById(R.id.backdrop);
        ImageLoaderManager.getInstance(getApplicationContext()).displayImage(mBgView, "http://img.mukewang.com/54f55ee60001850f05000280.jpg");
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setTitle("Grunt");
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initMainFragment() {
        ProductMainFragment fragment = new ProductMainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

}
