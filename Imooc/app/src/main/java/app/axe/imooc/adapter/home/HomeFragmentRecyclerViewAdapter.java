package app.axe.imooc.adapter.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.imooc.adapter.base.BaseRecyclerViewAdapter;
import app.axe.imooc.customui.home.MultiImagesView;
import app.axe.imooc.customui.viewpager.AutoScrollViewPager;
import app.axe.imooc.customui.viewpager.CirclePageIndicator;
import app.axe.imooc.module.recommend.RecommandBodyValue;
import app.axe.imooc.module.recommend.RecommandFooterValue;
import app.axe.imooc.module.recommend.RecommandHeadValue;
import app.axe.imooc.utils.RecommendModuleUtils;
import app.axe.support.universalimageloader.ImageLoaderManager;

/**
 * Created by Administrator on 2017/3/25.
 * Home界面Adapter
 */

public class HomeFragmentRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private static final int VIEW_TYPE_VIDOE_TYPE = 3;
    private static final int VIEW_TYPE_MULTI_PIC = 4;
    private static final int VIEW_TYPE_SINGLE_PIC = 5;
    private static final int VIEW_TYPE_PAGER = 6;
    private static final int VIEW_TYPE_HEAD_VIEW = 7;

    private ImageLoaderManager mImageLoadermanger;

    public HomeFragmentRecyclerViewAdapter(Context context, ImageLoaderManager manager) {
        super(context);
        mImageLoadermanger = manager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SINGLE_PIC) {
            return new HomeContentPicItemViewHolder(mInflater.inflate(R.layout.home_item_single_imagae_layout, parent, false));
        } else if (viewType == VIEW_TYPE_MULTI_PIC) {
            return new HomeContentMultiImagesItemViewHolder(mInflater.inflate(R.layout.home_item_multi_image_layout, parent, false));
        } else if (viewType == VIEW_TYPE_PAGER) {
            return new HomeContentViewPagerViewHolder(mInflater.inflate(R.layout.home_item_viewpager_layout, parent, false));
        } else if (viewType == VIEW_TYPE_HEAD_VIEW) {
            return new HomeContentHeaderViewHolder(mInflater.inflate(R.layout.home_head_viewpager_layout, parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == VIEW_TYPE_SINGLE_PIC) {
            bindTrendItemView((HomeContentPicItemViewHolder) holder, position);
        } else if (getItemViewType(position) == VIEW_TYPE_MULTI_PIC) {
            bindMultiImagesViewHolder((HomeContentMultiImagesItemViewHolder) holder, position);
        } else if (getItemViewType(position) == VIEW_TYPE_PAGER) {
            bindViewPagerViewHolder((HomeContentViewPagerViewHolder) holder, position);
        } else if (getItemViewType(position) == VIEW_TYPE_HEAD_VIEW) {
            bindHeadViewHolder((HomeContentHeaderViewHolder) holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object = getItems().get(position);
        if (object instanceof RecommandBodyValue) {
            RecommandBodyValue value = (RecommandBodyValue) object;
            switch (value.getType()) {
                case 0:
                    return VIEW_TYPE_VIDOE_TYPE;
                case 1:
                    return VIEW_TYPE_MULTI_PIC;
                case 2:
                    return VIEW_TYPE_SINGLE_PIC;
                case 3:
                    return VIEW_TYPE_PAGER;
            }
        } else if (object instanceof RecommandHeadValue) {
            return VIEW_TYPE_HEAD_VIEW;
        }
        return super.getItemViewType(position);
    }

    private void bindTrendItemView(HomeContentPicItemViewHolder holder, int position) {
        RecommandBodyValue bean = (RecommandBodyValue) getItems().get(position);
        holder.title.setText(bean.getTitle());
        holder.zan.setText(bean.getZan());
        holder.from.setText(bean.getFrom());
        ArrayList<String> urls = bean.getUrl();
        if (urls != null && urls.size() > 0) {
            mImageLoadermanger.displayImage(holder.contentImage, urls.get(0));
        }
        mImageLoadermanger.displayImage(holder.logo, bean.getLogo());
        holder.ivzan.setImageResource(R.drawable.ic_zan_select);
        holder.text.setText(bean.getText());
    }

    private void bindMultiImagesViewHolder(HomeContentMultiImagesItemViewHolder holder, int position) {
        RecommandBodyValue bean = (RecommandBodyValue) getItems().get(position);
        holder.title.setText(bean.getTitle());
        holder.zan.setText(bean.getZan());
        holder.from.setText(bean.getFrom());
        ArrayList<String> urls = bean.getUrl();
        holder.contentImage.setImages(bean.getUrl());
        mImageLoadermanger.displayImage(holder.logo, bean.getLogo());
        holder.ivzan.setImageResource(R.drawable.ic_zan_select);
        holder.text.setText(bean.getText());
    }

    private void bindViewPagerViewHolder(HomeContentViewPagerViewHolder holder, int position) {
        RecommandBodyValue bean = (RecommandBodyValue) getItems().get(position);
        ArrayList<RecommandBodyValue> recommandBodyValues = RecommendModuleUtils.handleData(bean);
        holder.mViewPager.setAdapter(new HomeItemViewPagerAdapter(mContext, recommandBodyValues));
        holder.mViewPager.setPageMargin((int) mContext.getResources().getDimension(R.dimen.fab_margin));
        holder.mViewPager.setCurrentItem(recommandBodyValues.size() * 100);
    }

    private void bindHeadViewHolder(HomeContentHeaderViewHolder headerViewHolder, int position) {
        RecommandHeadValue bean = (RecommandHeadValue) getItems().get(position);
        //自动循环的数据
        ArrayList<String> autos = bean.getAds();
        headerViewHolder.autoView.setVisibility((autos == null || autos.size() == 0) ? View.GONE : View.VISIBLE);
        headerViewHolder.autoScrollViewPager.setAdapter(new PhotoPagerAdapter(mContext, autos, true));
        headerViewHolder.autoScrollViewPager.startAutoScroll(3000);
        headerViewHolder.circlePageIndicator.setViewPager(headerViewHolder.autoScrollViewPager);

        //中间的推荐的数据
        ArrayList<String> recommands = bean.getMiddle();
        headerViewHolder.recommendView.setVisibility((recommands == null || recommands.size() == 0) ? View.GONE : View.VISIBLE);
        headerViewHolder.multiImagesView.setImages(recommands);
        //尾部的数据
        ArrayList<RecommandFooterValue> footer = bean.getFooter();
        headerViewHolder.footLayout.setVisibility((footer == null || footer.size() == 0) ? View.GONE : View.VISIBLE);
    }

    private class HomeContentPicItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView logo;
        public TextView title;
        public ImageView contentImage;
        public TextView from;
        public TextView zan;
        public ImageView ivzan;
        public TextView text;

        public HomeContentPicItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.home_item_content_tv_title);
            logo = (ImageView) itemView.findViewById(R.id.home_item_content_iv_logo);
            contentImage = (ImageView) itemView.findViewById(R.id.home_item_content_image);
            from = (TextView) itemView.findViewById(R.id.home_item_content_from);
            zan = (TextView) itemView.findViewById(R.id.home_item_content_tv_zan);
            ivzan = (ImageView) itemView.findViewById(R.id.home_item_content_iv_zan);
            text = (TextView) itemView.findViewById(R.id.home_item_content_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private class HomeContentMultiImagesItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView logo;
        public TextView title;
        public MultiImagesView contentImage;
        public TextView from;
        public TextView zan;
        public ImageView ivzan;
        public TextView text;

        public HomeContentMultiImagesItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.home_item_content_tv_title);
            logo = (ImageView) itemView.findViewById(R.id.home_item_content_iv_logo);
            contentImage = (MultiImagesView) itemView.findViewById(R.id.home_item_multi_images);
            from = (TextView) itemView.findViewById(R.id.home_item_content_from);
            zan = (TextView) itemView.findViewById(R.id.home_item_content_tv_zan);
            ivzan = (ImageView) itemView.findViewById(R.id.home_item_content_iv_zan);
            text = (TextView) itemView.findViewById(R.id.home_item_content_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private class HomeContentViewPagerViewHolder extends RecyclerView.ViewHolder {

        private ViewPager mViewPager;

        public HomeContentViewPagerViewHolder(View itemView) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.home_item_viewpager);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private class HomeContentHeaderViewHolder extends RecyclerView.ViewHolder {

        public AutoScrollViewPager autoScrollViewPager;
        public CirclePageIndicator circlePageIndicator;
        public MultiImagesView multiImagesView;
        public RelativeLayout autoView;
        public RelativeLayout recommendView;
        public LinearLayout footLayout;

        public HomeContentHeaderViewHolder(View itemView) {
            super(itemView);
            autoScrollViewPager = (AutoScrollViewPager) itemView.findViewById(R.id.home_head_autopager);
            circlePageIndicator = (CirclePageIndicator) itemView.findViewById(R.id.home_head_cli);
            multiImagesView = (MultiImagesView) itemView.findViewById(R.id.home_multi_images);
            footLayout = (LinearLayout) itemView.findViewById(R.id.home_head_ll_foot);
            autoView = (RelativeLayout) itemView.findViewById(R.id.home_auto_view);
            recommendView = (RelativeLayout) itemView.findViewById(R.id.home_head_recommend_view);
        }
    }

    public void addRecommandList(List<RecommandBodyValue> list) {
        for (int i = 0; i < list.size(); i++) {
            getItems().add(list.get(i));
        }
    }

    public void addHeadView(RecommandHeadValue headValue) {
        getItems().add(0, headValue);
    }
}
