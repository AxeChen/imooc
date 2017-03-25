package app.axe.imooc.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/3/25.
 */

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_NODATA = 0;
    private static final int VIEW_TYPE_FIRST_LOADING = 1;
    private static final int VIEW_TYPE_NEXT_LOADING = 2;

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
