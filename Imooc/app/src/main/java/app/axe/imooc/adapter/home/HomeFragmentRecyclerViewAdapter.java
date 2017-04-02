package app.axe.imooc.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.imooc.adapter.base.BaseRecyclerViewAdapter;
import app.axe.imooc.customui.HomeMultiImagesView;
import app.axe.imooc.module.recommend.RecommandBodyValue;
import app.axe.support.universalimageloader.ImageLoaderManager;

/**
 * Created by Administrator on 2017/3/25.
 * Home界面Adapter
 */

public class HomeFragmentRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private static final int VIEW_TYPE_VIDOE_TYPE = 3;
    private static final int VIEW_TYPE_MULTI_PIC = 4;
    private static final int VIEW_TYPE_SINGLE_PIC = 5;
    private static final int CARD_VIEW_PAGER = 6;

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
                    return CARD_VIEW_PAGER;
            }
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
        }
    }

    private class HomeContentMultiImagesItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView logo;
        public TextView title;
        public HomeMultiImagesView contentImage;
        public TextView from;
        public TextView zan;
        public ImageView ivzan;
        public TextView text;

        public HomeContentMultiImagesItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.home_item_content_tv_title);
            logo = (ImageView) itemView.findViewById(R.id.home_item_content_iv_logo);
            contentImage = (HomeMultiImagesView) itemView.findViewById(R.id.home_item_multi_images);
            from = (TextView) itemView.findViewById(R.id.home_item_content_from);
            zan = (TextView) itemView.findViewById(R.id.home_item_content_tv_zan);
            ivzan = (ImageView) itemView.findViewById(R.id.home_item_content_iv_zan);
            text = (TextView) itemView.findViewById(R.id.home_item_content_text);
        }
    }

    public void addRecommandList(List<RecommandBodyValue> list) {
        for (int i = 0; i < list.size(); i++) {
            getItems().add(list.get(i));
        }
    }
}
