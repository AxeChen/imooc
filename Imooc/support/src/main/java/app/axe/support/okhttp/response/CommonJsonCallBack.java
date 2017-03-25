package app.axe.support.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONObject;

import java.io.IOException;

import app.axe.support.okhttp.exception.OkHttpException;
import app.axe.support.okhttp.listener.DisposeDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/24.
 * 专门处理JSON的回调响应
 */

public class CommonJsonCallBack implements Callback {

    //与服务器放回字段的对应关系
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallBack(DisposeDataListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }

    //响应处理
    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(Object responseObj){
        if(responseObj == null || responseObj.toString().trim().length() == 0){
            mListener.onFailure(new OkHttpException(NETWORK_ERROR,EMPTY_MSG));
            return;
        }
        try{
            JSONObject result = new JSONObject(responseObj.toString());
            if(result.has(RESULT_CODE)){
                //从Json对象中取出响应码，如果是0则是正确的响应
                if(result.getInt(RESULT_CODE) == RESULT_CODE_VALUE){
                    if(mClass == null){
                        mListener.onSuccess(responseObj);
                    }else{
                        //将对象转化为对象
                        Object obj = new Object();
                        if(obj!=null){
                            mListener.onSuccess(obj);
                        }else{
                            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }
                }else{
                    //将服务器返回给我们的异常回调到应用去处理
                    mListener.onFailure(new OkHttpException(OTHER_ERROR,result.get(RESULT_CODE)));
                }
            }
        }catch (Exception e){
            mListener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }

    }
}
