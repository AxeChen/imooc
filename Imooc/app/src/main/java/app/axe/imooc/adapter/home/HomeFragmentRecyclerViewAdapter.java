package app.axe.imooc.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.axe.imooc.R;
import app.axe.imooc.adapter.base.BaseRecyclerViewAdapter;
import app.axe.imooc.module.TrendingBean;

/**
 * Created by Administrator on 2017/3/25.
 * Home界面Adapter
 */

public class HomeFragmentRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private static final int VIEW_TYPE_TRENDBEAN = 3;

    public HomeFragmentRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_TRENDBEAN){
            return new TrendBeanItemViewHolder(mInflater.inflate(R.layout.home_item_layout,parent,false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(getItemViewType(position) == VIEW_TYPE_TRENDBEAN){
            bindTrendItemView((TrendBeanItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object = getItems().get(position);
        if(object instanceof TrendingBean){
            return VIEW_TYPE_TRENDBEAN;
        }
        return super.getItemViewType(position);
    }

    private void bindTrendItemView(TrendBeanItemViewHolder holder, int position){
        TrendingBean bean = (TrendingBean) getItems().get(position);
        holder.mTitle.setText(bean.getTitle());
    }

    private class TrendBeanItemViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitle;

        public TrendBeanItemViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.home_item_title);
        }
    }

}
