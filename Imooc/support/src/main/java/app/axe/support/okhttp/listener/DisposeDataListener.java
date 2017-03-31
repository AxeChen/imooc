package app.axe.support.okhttp.listener;

/**
 * Created by Administrator on 2017/3/24.
 */

public interface DisposeDataListener {
    /**
     * 请求成功回调事件处理,如果请求成功会返回一个JsonObject对象
     * @param responseJsonObj
     */
    public void onSuccess(Object responseJsonObj);

    /**
     * 请求失败回调事件处理
     * @param reasonObj
     */
    public void onFailure(Object reasonObj);
}
