# imooc
![home_page](https://cloud.githubusercontent.com/assets/19148112/24736050/7473d40a-1ab8-11e7-9314-945b02cf0493.jpg)  </br>

### 1、组件化开发  
**新建module，已将Okhttp，Universal-imageloader封装成为公用的组件**    </br>
可封装成sdk，供其他app使用。封装的思想会贯穿整个开发。

### 2、home界面开发。 </br>

**viewpager+fragment结构**   左右滑动切换界面   </br>
**home tab页面的开发:RecyclerView控件，封装公用的Adapter统一首次加载，加载下一页，没有数据的UI展示。**     </br>
```
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_NODATA = 0;
    private static final int VIEW_TYPE_FIRST_LOADING = 1;
    private static final int VIEW_TYPE_NEXT_LOADING = 2;
... ...
}
```
**单图和多图的item封装自定义view。  **</br>
直接传入图片的个数，就可以通过制定的规则算法将图片展示。   </br>
![custom_ui](https://cloud.githubusercontent.com/assets/19148112/24736734/67782194-1abc-11e7-8edf-bf196ebed2cc.png)  </br>

**轮播图片采用三方开源库：AutoScrollViewPager和CirclePageIndicator。 ** </br>

**分割线参用自定义RecyclerView.ItemDecoration。**  </br>
http://www.jianshu.com/p/13b6d1237e69

### 3、点击反馈，Android5.0后的点击波浪纹效果。 </br>
http://www.jianshu.com/p/7d2a8a5836e0

### 4、优化  </br>
减少重绘  </br>
![overdraw](https://cloud.githubusercontent.com/assets/19148112/24737072/a077d514-1abe-11e7-9595-dcdfa5bd9fc1.png)  </br>
项目还在持续开发优化中。  </br>
### 5、服务器数据：暂时采用Charles代理请求json。  </br>
### 6、添加二维码扫描功能。 </br>
依赖google官方框架：Zxing。  </br>
https://github.com/zxing/zxing  </br>
1、逻辑建立，扫描http://www.imooc.com 的scheme和host  </br>
2、进入商品界面和人员详情界面。  </br>
### 很久没有更新了，由于在找工作奔波于上海和杭州之间，最后还是在杭州找到了工作。
### 7、视频播放模块。</br>
添加视频播放模块
### 8、项目还在持续开发中。</br>  
### 这个项目原本想继续开发，当我学会mvp架构和butterknife 之后，我会觉得继续开发下去会浪费大量的时间（用的mvc架构，写了太多了findbyid操作，加上还要上班）。准备用mvp架构重构，图片框架将会替换成glide（原因是universal-imageloa已经不在维护），网络请求框架将会换成 retrofit+rxjava 封装。

