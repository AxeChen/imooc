package app.axe.support.okhttp.listener;

import java.util.Objects;

/**
 * Created by Administrator on 2017/3/24.
 */

public interface DisposeDataListener {
    /**
     * 请求成功回调事件处理
     * @param responseObj
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     * @param reasonObj
     */
    public void onFailure(Object reasonObj);
}
