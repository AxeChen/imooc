package app.axe.imooc.module.recommend;


import java.util.ArrayList;

import app.axe.imooc.module.BaseModel;

/**
 * *******************************************************
 *
 * @文件名称：RecommandValue.java
 * @文件作者：renzhiqiang
 * @创建时间：2015年7月9日 上午9:24:04
 * @文件描述：搜索实体
 * @修改历史：2015年7月9日创建初始版本 ********************************************************
 */
public class RecommandBodyValue extends BaseModel {

    public int type;
    public String logo;
    public String title;
    public String info;
    public String price;
    public String text;
    public String site;
    public String from;
    public String zan;
    public ArrayList<String> url;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }

    //视频专用
//    public String thumb;
//    public String resource;
//    public String resourceID;
//    public String adid;
//    public ArrayList<Monitor> startMonitor;
//    public ArrayList<Monitor> middleMonitor;
//    public ArrayList<Monitor> endMonitor;
//    public String clickUrl;
//    public ArrayList<Monitor> clickMonitor;
//    public EMEvent event;

}
