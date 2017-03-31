package app.axe.imooc.module.recommend;


import java.util.ArrayList;

import app.axe.imooc.module.BaseModel;

/**
 * @author: vision
 * @function:
 * @date: 16/9/2
 */
public class RecommandHeadValue extends BaseModel {

    public ArrayList<String> ads;
    public ArrayList<String> middle;
    public ArrayList<RecommandFooterValue> footer;

    public ArrayList<String> getAds() {
        return ads;
    }

    public void setAds(ArrayList<String> ads) {
        this.ads = ads;
    }

    public ArrayList<String> getMiddle() {
        return middle;
    }

    public void setMiddle(ArrayList<String> middle) {
        this.middle = middle;
    }

    public ArrayList<RecommandFooterValue> getFooter() {
        return footer;
    }

    public void setFooter(ArrayList<RecommandFooterValue> footer) {
        this.footer = footer;
    }
}
