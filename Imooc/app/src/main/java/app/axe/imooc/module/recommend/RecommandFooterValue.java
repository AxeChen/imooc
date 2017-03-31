package app.axe.imooc.module.recommend;


import app.axe.imooc.module.BaseModel;

/**
 * @author: vision
 * @function:
 * @date: 16/9/2
 */
public class RecommandFooterValue extends BaseModel {

    public String title;
    public String info;
    public String from;
    public String imageOne;
    public String imageTwo;
    public String destationUrl;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getDestationUrl() {
        return destationUrl;
    }

    public void setDestationUrl(String destationUrl) {
        this.destationUrl = destationUrl;
    }
}
