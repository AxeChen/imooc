package app.axe.support.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import app.axe.support.okhttp.https.HttpsUtils;
import app.axe.support.okhttp.response.CommonJsonCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/3/24.
 * 请求的发送，请求参数的配置，https的支持
 */

public class CommonOkHttpClient {
    /**
     * 超时参数
     */
    private static final int TIME_OUT = 30;

    private static OkHttpClient mOkHttpClient;

    //为client配置参数
    static {
        // 创建我们client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        // 为构建者设置超时时间
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        //允许重定向
        okHttpBuilder.followRedirects(true);
        //https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(),HttpsUtils.initTrustManager());

        //生成client对象
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送具体的http/https请求
     * @param request
     * @param commCallBack
     * @return
     */
    public static Call sendRequest(Request request, CommonJsonCallBack commCallBack){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commCallBack);
        return call;
    }

}
