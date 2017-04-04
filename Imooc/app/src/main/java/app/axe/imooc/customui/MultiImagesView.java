package app.axe.imooc.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.support.universalimageloader.ImageLoaderManager;

/**
 * Created by chen on 2017/3/31.
 * 当item的图片超过一张时的展示规则
 */

public class MultiImagesView extends RelativeLayout {

    /**
     * 默认每行显示多少个
     **/
    private static final int LINE_COUNT = 3;

    private static final int IMAGE_MAGIN = 3;
    /**
     * 当measure的模式为AT_MOST时默认的图片大小
     **/
    private static final int DEFAULT_IMAGE_WIDHT = 200;
    /**
     * 每张图片的大小
     **/
    private int imageWidth = 0;
    /**
     * 整个view的大小
     **/
    private int viewWidth = 0;
    /**
     * 整个view的高度
     **/
    private int viewHeight = 0;

    /**
     * 图片的个数
     **/
    private List<String> images = new ArrayList<>();
    private Context mContext;

    /**
     * 图片下载器，这里封装存在缺陷，将业务逻辑封装了
     **/
    private ImageLoaderManager imageLoaderManager;

    int mMagin = 0;
    int lineCount = 0;

    public MultiImagesView(Context context) {
        this(context, null);
    }

    public MultiImagesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiImagesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context.getApplicationContext();
        getAttrs(attrs);
        imageLoaderManager = ImageLoaderManager.getInstance(mContext);
    }

    protected void getAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MultiImagesView);
        lineCount = ta.getInt(R.styleable.MultiImagesView_lineCount, LINE_COUNT);
        mMagin = (int) ta.getDimension(R.styleable.MultiImagesView_imageMagin, IMAGE_MAGIN);
        mMagin = dp2px(mMagin);
        ta.recycle();
    }

    //dp px
    protected int dp2px(int dpval) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpval, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算出每一个图片的宽度
        int childCount = getChildCount();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            //当测量模式是exactly，直接可以获取值
            viewWidth = widthSize;
            //三种绘制模式下的宽度
            imageWidth = (viewWidth - (lineCount - 1) * mMagin) / lineCount;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //父容器的大小无法确定，由子容器的大小决定。所以子容器必须给一个默认的大小。
            imageWidth = DEFAULT_IMAGE_WIDHT;
            if (images.size() == 0) {
                viewWidth = 0;
            } else if (images.size() > lineCount) {
                for (int i = 0; i < images.size(); i++) {
                    if (i == 0) {
                        viewWidth += imageWidth;
                    } else {
                        viewWidth += imageWidth + mMagin;
                    }
                }
            } else if (images.size() >= lineCount) {
                viewWidth = lineCount * imageWidth + mMagin * (lineCount - 1);
            }
        }

        for (int i = 0; i < childCount; i++) {
            if (i == 0 && childCount != 0) {
                viewHeight = imageWidth + getPaddingTop();
            }
            if (i != 0 && i % lineCount == 0) {
                viewHeight = viewHeight + (mMagin + imageWidth);
            }
        }
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        int line = 0;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < childCount; i++) {
            //换行的时候，top和bottom要增加
            View view = getChildAt(i);
            if (i != 0 && i % lineCount == 0) {
                line++;
            }
            if (i % lineCount == 0) {
                left = i % lineCount * imageWidth;
            } else {
                left = i % lineCount * imageWidth + (i % lineCount) * mMagin;
            }
            top = imageWidth * line + line * mMagin;
            right = left + imageWidth;
            bottom = top + imageWidth;
            view.layout(left, top, right, bottom);
        }
    }

    public void setImages(List<String> images) {
        this.images = images;
        removeAllViews();
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(imageWidth, imageWidth);
            imageLoaderManager.displayImage(imageView, images.get(i));
            addView(imageView, lp);
        }
    }
}
