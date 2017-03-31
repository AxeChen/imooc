package app.axe.imooc.network;

import app.axe.support.okhttp.CommonOkHttpClient;
import app.axe.support.okhttp.listener.DisposeDataListener;
import app.axe.support.okhttp.request.CommonRequest;
import app.axe.support.okhttp.request.RequestParams;

/**
 * Created by Administrator on 2017/3/30.
 */

public class CommonRequestUtils {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener) {
        CommonOkHttpClient.get(CommonRequest.
                createPostRequest(url, params), listener);
    }

    //根据参数发送所有post请求
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), listener);
    }
}
