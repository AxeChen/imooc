package app.axe.imooc.module.course;


import java.util.ArrayList;

import app.axe.imooc.module.BaseModel;
import app.axe.support.core.AdValue;

/**
 * @author: vision
 * @function:
 * @date: 16/9/2
 */
public class CourseHeaderValue extends BaseModel {

    public ArrayList<String> photoUrls;
    public String text;
    public String name;
    public String logo;
    public String oldPrice;
    public String newPrice;
    public String zan;
    public String scan;
    public String hotComment;
    public String from;
    public String dayTime;
    public AdValue video;
}
