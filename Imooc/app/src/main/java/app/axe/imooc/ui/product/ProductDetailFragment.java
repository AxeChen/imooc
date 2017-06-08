package app.axe.imooc.ui.product;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.imooc.customui.home.MultiImagesView;
import app.axe.imooc.network.RequestCenter;
import app.axe.support.okhttp.listener.DisposeDataListener;

/**
 * Created by Chen on 2017/5/20.
 */

public class ProductDetailFragment extends Fragment {

    private View mView;
    private Activity mActivity;
    private MultiImagesView multiImagesView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        multiImagesView = (MultiImagesView) mView.findViewById(R.id.images);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        setImages();
    }

    private void setImages() {
        List<String> list = new ArrayList<>();
        list.add("http://img.mukewang.com/54f55ee60001850f05000280.jpg");
        list.add("http://img7.doubanio.com/view/note/large/public/p26832324.jpg");
        list.add("http://img.mukewang.com/546570370001f8a906000338-590-330.jpg");
        list.add("http://img.mukewang.com/55f93f980001f52125001408-590-330.jpg");
        multiImagesView.setImages(list);
    }

    private void requestDetails() {
        RequestCenter.requestProductData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }
}
