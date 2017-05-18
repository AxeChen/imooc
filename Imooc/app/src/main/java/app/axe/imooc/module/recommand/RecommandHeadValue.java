package app.axe.imooc.module.recommand;


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

}
