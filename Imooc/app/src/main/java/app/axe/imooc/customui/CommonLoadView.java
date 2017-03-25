package app.axe.imooc.customui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.axe.imooc.R;

/**
 * Created by Administrator on 2017/3/25.
 * 半自定义load view。通用的loading view。
 */

public class CommonLoadView extends RelativeLayout{

    /**
     * 提供三种不同的UI类型展示：只有loading，loading和横向的描述，loading和纵向的描述
     */
    private static final int SHOW_ONLY_PROGRESS = 0;
    private static final int SHOW_PROGRESS_HORIZONTAL_DESCRIPTION = 1;
    private static final int SHOW_PROGRESS_VERTICAL_DESCRIPTION = 2;

    private static final float DEFAULT_HEIGHT = 50;
    private static final int DEFAULT_DESCRIPTION_TEXTSIZE = 20;
    private static final int DEFUALT_INTERVAL_SIZE = 20;

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    /**
     * 展示的UI类型
     */
    private int showType = 0;

    /**
     * progress 的宽高
     */
    private float progressWidth = 0;

    /**
     * description 文字的大小,颜色
     */
    private float descriptionTextSize = 12;
    private ColorStateList descriptionTextColor;

    /**
     * 进度条和文字之间的间隙
     */
    private int intervalSize;

    private View mView;
    private ProgressBar mProgress;
    private TextView mHorizontalDescription;
    private TextView mVerticalDescription;


    public CommonLoadView(Context context) {
        this(context,null);
    }

    public CommonLoadView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommonLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context.getApplicationContext();
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mLayoutInflater.inflate(R.layout.common_load_view_layout,this);
        initView();
        getAttrs(attrs);
        showView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 获取参数
     * @param attrs
     */
    private void getAttrs(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CommonLoadView);
        showType = ta.getInt(R.styleable.CommonLoadView_showType,SHOW_ONLY_PROGRESS);
        progressWidth  = ta.getDimension(R.styleable.CommonLoadView_progressWidth,DEFAULT_HEIGHT);
        descriptionTextSize = ta.getDimensionPixelSize(R.styleable.CommonLoadView_descriptionTextSize,DEFAULT_DESCRIPTION_TEXTSIZE);
        descriptionTextColor = ta.getColorStateList(R.styleable.CommonLoadView_descriptionTextColor);
        intervalSize = ta.getDimensionPixelSize(R.styleable.CommonLoadView_intervalSize,DEFUALT_INTERVAL_SIZE);
        ta.recycle();
    }

    /**
     * 初始化布局
     */
    private void initView(){
        mProgress = (ProgressBar) mView.findViewById(R.id.common_load_progressbar);
        mVerticalDescription = (TextView) mView.findViewById(R.id.common_vertical_description);
        mHorizontalDescription = (TextView) mView.findViewById(R.id.common_horizontal_description);
    }


    /**
     * 设置属性
     */
    private void showView(){

        switch (showType){
            case SHOW_ONLY_PROGRESS:
                mHorizontalDescription.setVisibility(View.GONE);
                mVerticalDescription.setVisibility(View.GONE);
                break;
            case SHOW_PROGRESS_HORIZONTAL_DESCRIPTION:
                mVerticalDescription.setVisibility(View.GONE);
                mHorizontalDescription.setVisibility(View.VISIBLE);
                break;
            case SHOW_PROGRESS_VERTICAL_DESCRIPTION:
                mHorizontalDescription.setVisibility(View.GONE);
                mVerticalDescription.setVisibility(View.VISIBLE);
                break;
        }

        mHorizontalDescription.setTextSize(px2sp((int) descriptionTextSize));
        mVerticalDescription.setTextSize(px2sp((int) descriptionTextSize));

        mHorizontalDescription.setTextColor(descriptionTextColor);
        mVerticalDescription.setTextColor(descriptionTextColor);

        mHorizontalDescription.setPadding(intervalSize,0,0,0);
        mVerticalDescription.setPadding(0,intervalSize,0,0);
    }

    /**
     * 获取到值为px，需要转为sp。低设备可能存在问题
     * @param pxValue
     * @return
     */
    public int px2sp(float pxValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


}
