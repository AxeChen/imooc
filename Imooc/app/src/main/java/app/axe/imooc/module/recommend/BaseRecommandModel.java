package app.axe.imooc.module.recommend;


import app.axe.imooc.module.BaseModel;

/**
 * Created by renzhiqiang on 16/8/28.
 */
public class BaseRecommandModel extends BaseModel {

    public String ecode;
    public String emsg;
    public RecommandModel data;

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    public RecommandModel getData() {
        return data;
    }

    public void setData(RecommandModel data) {
        this.data = data;
    }
}
