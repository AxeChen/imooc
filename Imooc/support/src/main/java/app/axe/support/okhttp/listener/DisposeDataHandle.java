package app.axe.support.okhttp.listener;

/**
 * Created by Administrator on 2017/3/24.
 *
 */

public class DisposeDataHandle {

    public DisposeDataListener mListener;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener mListener) {
        this.mListener = mListener;
    }

    public DisposeDataHandle(DisposeDataListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
    }
}
