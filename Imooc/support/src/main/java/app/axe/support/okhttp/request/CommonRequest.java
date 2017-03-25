package app.axe.support.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/3/24.
 * 接收请求参数，生成Request对象
 */

public class CommonRequest {

    /**
     * 创建一个post Request对象
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url,RequestParams params){
        FormBody.Builder formBodyBuild = new FormBody.Builder();
        if(params!=null){
            for(Map.Entry<String,String> entry :params.urlParams.entrySet()){
                //将请求参数遍历添加到请求构建类中
                formBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }

        //通过请求构建类的build方法获取到真正的请求对象
        FormBody formBody = formBodyBuild.build();

        return new Request.Builder().url(url).post(formBody).build();
    }

    /**
     * 创建一个get Request对象
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params!=null){
            for(Map.Entry<String,String> entry :params.urlParams.entrySet()){
                //将请求参数遍历添加到请求构建类中
               urlBuilder.append(entry.getKey()).append("=").append(entry.getValue())
               .append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1)).get().build();

    }
}
