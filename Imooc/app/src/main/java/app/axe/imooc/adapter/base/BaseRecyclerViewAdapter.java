package app.axe.imooc.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;

/**
 * Created by Administrator on 2017/3/28.
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_NODATA = 0;
    private static final int VIEW_TYPE_FIRST_LOADING = 1;
    private static final int VIEW_TYPE_NEXT_LOADING = 2;


    private List<Object> mItems;
    private LayoutInflater mInflater;
    private Context mContext;

    /**
     * 设置三个默认的view
     */
    private int nodataview = R.layout.common_adapter_nodata_layout;
    private int loadnextview = R.layout.common_adapter_load_next_layout;
    private int loadfirstview = R.layout.common_adapter_first_load_layout;

    public BaseRecyclerViewAdapter(Context context){
        mContext = context.getApplicationContext();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        Object object = getItems().get(position);
        if(object != null){
            if(object instanceof Integer){
                return (int) object;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_FIRST_LOADING){
            return new CommonViewHolder(mInflater.inflate(loadfirstview,parent));
        }else if(viewType == VIEW_TYPE_NEXT_LOADING){
            return new CommonViewHolder(mInflater.inflate(loadnextview,parent));
        }else {
            return new CommonViewHolder(mInflater.inflate(nodataview,parent));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * 获取数据集合
     * @return
     */
    public List<Object> getItems(){
        if(mItems == null){
            mItems = new ArrayList<>();
        }
        return mItems;
    }

    /**
     * 添加一个集合的数据
     * @param objects
     */
    public void addItems(List<Object> objects){
        for(Object object:objects){
            getItems().add(object);
        }
    }

    /**
     * 添加单个数据
     * @param object
     */
    public void addItem(Object object){
        if(object == null){
            return;
        }
        getItems().add(object);
    }

    /**
     * 添加单个数据
     * @param object
     * @param position
     */
    public void addItem(Object object,int position){
        if(object == null){
            return;
        }
        getItems().add(position,object);
    }

    /**
     * 删除一个数据
     * @param position
     */
    public void deleteItem(int position){
        if(getItems().get(position)!=null){
            getItems().remove(position);
        }
        notifyDataSetChanged();
    }

    /**移除第一次加载的loading**/
    public void removeLoadFrist(){
        getItems().clear();
        notifyDataSetChanged();
    }

    /**移除加载下一次的loading**/
    public void removeLoadNext(){
        getItems().remove(VIEW_TYPE_NEXT_LOADING);
        notifyDataSetChanged();
    }

    /**
     * 清除没有数据的界面
     */
    public void removeNodataView(){
        getItems().clear();
        notifyDataSetChanged();
    }

    /**添加下一页Loading**/
    public void addLoadNext(){
        getItems().add(VIEW_TYPE_NEXT_LOADING);
        notifyDataSetChanged();
    }

    /**添加第一次加载loading**/
    public void addLoadFrist(){
        getItems().add(VIEW_TYPE_FIRST_LOADING);
        notifyDataSetChanged();
    }

    /**
     * 添加没有数据的界面
     */
    public void addNoDataView(){
        getItems().clear();
        getItems().add(VIEW_TYPE_NODATA);
        notifyDataSetChanged();
    }

    /**
     * 设置三个公共view展示的逻辑
     * @param nodataview
     * @param loadfirstview
     * @param loadnextview
     */
    public void setCommonView(int nodataview,int loadfirstview,int loadnextview){
        setLoadfirstview(loadfirstview);
        setLoadnextview(loadnextview);
        setNodataview(nodataview);
    }

    public void setNodataview(int nodataview) {
        if(nodataview == 0){
            return;
        }
        this.nodataview = nodataview;
    }

    public void setLoadnextview(int loadnextview) {
        if(loadnextview == 0){
            return;
        }
        this.loadnextview = loadnextview;
    }

    public void setLoadfirstview(int loadfirstview) {
        if(loadfirstview == 0){
            return;
        }
        this.loadfirstview = loadfirstview;
    }

    /**
     * 公用UI的viewholder
     */
    public class CommonViewHolder extends RecyclerView.ViewHolder{

        public CommonViewHolder(View itemView) {
            super(itemView);
        }
    }
}
