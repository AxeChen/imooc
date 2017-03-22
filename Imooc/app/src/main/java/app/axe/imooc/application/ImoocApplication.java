package app.axe.imooc.application;

import android.app.Application;

/**
 * Created by Administrator on 2017/3/22 0022.
 * 1、程序入口。2、初始化工作 3、为整个应用的其他模块提供上下文
 */

public class ImoocApplication extends Application{

    private static ImoocApplication mImoocApplication = null;

    public static ImoocApplication getInstance(){
        return mImoocApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mImoocApplication = this;
    }
}
