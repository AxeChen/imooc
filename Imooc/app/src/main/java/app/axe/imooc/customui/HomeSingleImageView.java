package app.axe.imooc.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import app.axe.imooc.utils.ScreenUtils;

/**
 * Created by Chen on 2017/4/1.
 * 当item的图片只有一张时的展示规则
 */

public class HomeSingleImageView extends android.support.v7.widget.AppCompatImageView {

    private Context mContext;
    private int screenWidth;
    private int screenHeight;
    private Bitmap bitmap;

    public HomeSingleImageView(Context context) {
        this(context, null);
    }

    public HomeSingleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeSingleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context.getApplicationContext();
        int[] screenSize = ScreenUtils.getScreenWidth(context);
        screenWidth = screenSize[0];
        screenHeight = screenSize[1];
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        int btWidth = bm.getWidth();
        int btHeight = bm.getHeight();
        int imageWidth = 0;
        int imageHeight = 0;
        float scale = 0f;
        if (btWidth < (screenWidth / 2)) {
            imageWidth = screenWidth / 2;
            scale = btWidth * 0.1f / (screenWidth * 0.1f / 2);
        } else if (btWidth > screenWidth) {
            imageWidth = screenWidth;
            scale = screenWidth * 0.1f / btWidth * 0.1f;
        }
        imageHeight = (int) (btHeight / scale);
        if (getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
            layoutParams.width = imageWidth;
            layoutParams.height = imageHeight;
            this.setLayoutParams(layoutParams);
        } else if (getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.getLayoutParams();
            layoutParams.width = imageWidth;
            layoutParams.height = imageHeight;
            this.setLayoutParams(layoutParams);
        } else if (getParent() instanceof FrameLayout) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.getLayoutParams();
            layoutParams.width = imageWidth;
            layoutParams.height = imageHeight;
            this.setLayoutParams(layoutParams);
        }

        /**
         * 缺少对大于屏幕尺寸的图片的判断。
         */
        super.setImageBitmap(bm);
    }
}
