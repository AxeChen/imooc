package app.axe.imooc.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.R;
import app.axe.imooc.customui.HomeMultiImagesView;
import app.axe.imooc.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class MessageFragment extends BaseFragment {

    private View mView;
    private HomeMultiImagesView homeMultiImagesView;
    private List<String> images;

    private RelativeLayout mTest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_message_layout, container, false);
        homeMultiImagesView = (HomeMultiImagesView) mView.findViewById(R.id.message_fragment_multi_images);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initImages();
        homeMultiImagesView.setImages(images);
    }

    private void initImages() {
        images = new ArrayList<>();
        images.add("http://img.zcool.cn/community/01bba8564aefde32f87512f6a47ea3.jpg");
        images.add("http://i8.hexunimg.cn/2015-08-21/178515688.jpg");
        images.add("http://img.mukewang.com/54b619d70001294906000338-590-330.jpg");
        images.add("http://img.mukewang.com/56e617dd0001583d06000338-590-330.jpg");
        images.add("http://img.mukewang.com/5487a78e000105b606000338-590-330.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=1105015795,3331123080&fm=21&gp=0.jpg");
        images.add("http://i0.hdslb.com/bfs/archive/db522f316a9f555d8fa9bfe856c5a606612747d6.jpg");
//        images.add("http://img.mukewang.com/56556ecb00010b7806000338-590-330.jpg");
        images.add("http://img.mukewang.com/56e617dd0001583d06000338-590-330.jpg");
    }
}
