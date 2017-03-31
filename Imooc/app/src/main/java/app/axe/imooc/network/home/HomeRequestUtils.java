package app.axe.imooc.network.home;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.axe.imooc.module.recommend.BaseRecommandModel;
import app.axe.imooc.module.recommend.RecommandBodyValue;
import app.axe.imooc.module.recommend.RecommandFooterValue;
import app.axe.imooc.module.recommend.RecommandHeadValue;
import app.axe.imooc.module.recommend.RecommandModel;
import app.axe.imooc.network.CommonRequestUtils;
import app.axe.support.okhttp.listener.DisposeDataListener;
import app.axe.support.okhttp.request.RequestParams;

/**
 * Created by Chen on 2017/3/30.
 */

public class HomeRequestUtils {
    //根据参数发送所有post请求
    public static void getTrending(String url, RequestParams params, final HomeRequestListener listener){
        CommonRequestUtils.postRequest(url, params, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseJsonObj) {
                Log.i("chen_log",responseJsonObj.toString());
                parserRecommendModel(responseJsonObj,listener);
            }

            @Override
            public void onFailure(Object reasonObj) {
                listener.onFailure();
            }
        });
    }

    private static void parserRecommendModel(Object responseJsonObj,HomeRequestListener listener) {
        if (responseJsonObj == null) {
            listener.onFailure();
        }
        if (responseJsonObj instanceof JSONObject) {
            BaseRecommandModel baseRecommandModel = new BaseRecommandModel();
            JSONObject baseRecommandObj = (JSONObject) responseJsonObj;
            baseRecommandModel.setEcode(baseRecommandObj.optString("ecode"));
            baseRecommandModel.setEmsg(baseRecommandObj.optString("emsg"));
            if (baseRecommandObj.has("data")) {
                try {
                    JSONObject recommandModelObj = baseRecommandObj.getJSONObject("data");
                    RecommandModel recommandModel = new RecommandModel();
                    if (recommandModelObj != null) {
                        ArrayList<RecommandBodyValue> recommandBodyValues = new ArrayList<>();
                        //解析List
                        if (recommandModelObj.has("list")) {
                            JSONArray listJson = recommandModelObj.getJSONArray("list");
                            for (int i = 0; i < listJson.length(); i++) {
                                JSONObject itemObj = listJson.getJSONObject(i);
                                if (itemObj != null) {
                                    RecommandBodyValue recommandBodyValue = new RecommandBodyValue();
                                    recommandBodyValue.setTitle(itemObj.optString("title"));
                                    recommandBodyValue.setFrom(itemObj.optString("from"));
                                    recommandBodyValue.setInfo(itemObj.optString("info"));
                                    recommandBodyValue.setLogo(itemObj.optString("logo"));
                                    recommandBodyValue.setZan(itemObj.optString("zan"));
                                    recommandBodyValue.setPrice(itemObj.optString("price"));
                                    recommandBodyValue.setText(itemObj.optString("text"));
                                    recommandBodyValue.setType(itemObj.optInt("type"));
                                    if (itemObj.has("url")) {
                                        JSONArray urlArray = itemObj.getJSONArray("url");
                                        ArrayList<String> urlList = new ArrayList<>();
                                        for (int j = 0; j < urlArray.length(); j++) {
                                            urlList.add(urlArray.optString(j));
                                        }
                                        recommandBodyValue.setUrl(urlList);
                                    }
                                    recommandBodyValues.add(recommandBodyValue);
                                }
                            }
                            recommandModel.setList(recommandBodyValues);
                        }
                        //解析head
                        if (recommandModelObj.has("head")) {
                            RecommandHeadValue recommandHeadValue = new RecommandHeadValue();
                            JSONObject headJson = recommandModelObj.getJSONObject("head");
                            if(headJson.has("ads")){
                                JSONArray adsArray = headJson.getJSONArray("ads");
                                if(adsArray!=null){
                                    ArrayList<String> adsList = new ArrayList<>();
                                    for(int i=0;i<adsArray.length();i++){
                                        adsList.add(adsArray.optString(i));
                                    }
                                    recommandHeadValue.setAds(adsList);
                                }
                            }
                            if(headJson.has("middle")){
                                JSONArray middleArray = headJson.getJSONArray("middle");
                                if(middleArray!=null){
                                    ArrayList<String> middleList = new ArrayList<>();
                                    for(int i=0;i<middleArray.length();i++){
                                        middleList.add(middleArray.optString(i));
                                    }
                                    recommandHeadValue.setMiddle(middleList);
                                }
                            }

                            if(headJson.has("footer")){
                                JSONArray footerArray = headJson.getJSONArray("footer");
                                if(footerArray!=null){
                                    ArrayList<RecommandFooterValue> recommandFooterValues = new ArrayList<>();
                                    for (int i =0;i<footerArray.length();i++){
                                        JSONObject footerObj = footerArray.getJSONObject(i);
                                        RecommandFooterValue recommandFooterValue = new RecommandFooterValue();
                                        recommandFooterValue.setDestationUrl(footerObj.optString("destationUrl"));
                                        recommandFooterValue.setFrom(footerObj.optString("from"));
                                        recommandFooterValue.setTitle(footerObj.optString("title"));
                                        recommandFooterValue.setImageOne(footerObj.optString("imageOne"));
                                        recommandFooterValue.setImageTwo(footerObj.optString("imageTwo"));
                                        recommandFooterValue.setDestationUrl(footerObj.optString("destationUrl"));
                                        recommandFooterValues.add(recommandFooterValue);
                                    }
                                    recommandHeadValue.setFooter(recommandFooterValues);
                                }
                            }
                            recommandModel.setHead(recommandHeadValue);
                        }
                        baseRecommandModel.setData(recommandModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.getRecommendModel(baseRecommandModel);
            } else {
                listener.onFailure();
            }
        } else {
            listener.onFailure();
        }
    }

    /**
     * 数据回调接口
     */
    public interface HomeRequestListener{
        public void getRecommendModel(BaseRecommandModel recommandModel);
        public void onFailure();
    }

}
