package app.axe.support.universalimageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import app.axe.support.R;

/**
 * Created by Administrator on 2017/3/25.
 */

public class ImageLoaderManager {

    /**线程数量**/
    private static final int THREAD_COUNT = 4;
    /**优先级**/
    private static final int PRIORITY = 2;
    /**物理最大缓存**/
    private static final int DISK_CACHE_size = 50*1024*1024;
    /**连接超时**/
    private static final int CONNECTION_TIME_OUT = 5*1000;
    /**读取超时**/
    private static final int READ_TIME_OUT = 30*1000;

    private static ImageLoader imageLoader;

    /**
     * 单例模式
     */
    private static ImageLoaderManager imageLoaderManager;

    private static ImageLoaderManager ImageLoaderManager(Context context) {
        if(imageLoaderManager == null){
            synchronized (ImageLoaderManager.class){
                if(imageLoaderManager == null){
                    imageLoaderManager = new ImageLoaderManager(context);
                }
            }
        }
        return imageLoaderManager;
    }

    private ImageLoaderManager(Context context){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_COUNT)//最大线程池
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY)
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套
                .memoryCache(new WeakMemoryCache())//使用弱引用
                .diskCacheSize(DISK_CACHE_size)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(getDefaultOptions())
                .imageDownloader(new BaseImageDownloader(context,CONNECTION_TIME_OUT,READ_TIME_OUT))
                .build();
        ImageLoader.getInstance().init(configuration);
        imageLoader = ImageLoader.getInstance();
    }

    /**
     * 创建DisplayImageOptions
     * @return
     */
    private DisplayImageOptions getDefaultOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_nodata_icon)//图片地址为空的情况
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

    /**
     * 展示圖片的方法
     * @param imageView
     * @param url
     * @param options
     * @param loadingListener
     */
    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener loadingListener){

        if(imageLoader != null){
            imageLoader.displayImage(url,imageView,options,loadingListener);
        }
    }

    public void displayImage(ImageView imageView, String url, ImageLoadingListener loadingListener){

        if(imageLoader != null){
            imageLoader.displayImage(url,imageView,null,loadingListener);
        }
    }

    public void displayImage(ImageView imageView, String url){

        if(imageLoader != null){
            imageLoader.displayImage(url,imageView,null,null);
        }
    }
}
