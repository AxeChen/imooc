package app.axe.imooc.adapter.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.axe.imooc.R;
import app.axe.imooc.customui.home.MultiImagesView;
import app.axe.imooc.module.recommend.RecommandBodyValue;

/**
 * Created by chen on 2017/4/2.
 */

public class HomeItemViewPagerAdapter extends PagerAdapter {

    private Context mContext;

    private ArrayList<RecommandBodyValue> recommandBodyValues;
    private LayoutInflater mInflater;

    public HomeItemViewPagerAdapter(Context context,ArrayList<RecommandBodyValue> recommandBodyValues){
        this.mContext = context;
        this.recommandBodyValues = recommandBodyValues;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return (float) 0.8;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        RecommandBodyValue value = recommandBodyValues.get(position%recommandBodyValues.size());
        View view = mInflater.inflate(R.layout.home_item_viewpager_content_layout,null);
        TextView title = (TextView) view.findViewById(R.id.tv_title_view);
        TextView info = (TextView) view.findViewById(R.id.tv_info_view);
        TextView price = (TextView) view.findViewById(R.id.tv_price_view);
        TextView students = (TextView) view.findViewById(R.id.tv_students_view);
        MultiImagesView multiImagesView = (MultiImagesView) view.findViewById(R.id.multi_images_view);
        title.setText(value.getTitle());
        info.setText(value.getInfo());
        price.setText(value.getPrice());
        students.setText(value.getText());
        multiImagesView.setImages(value.getUrl());
        container.addView(view);
        return view;
    }
}
