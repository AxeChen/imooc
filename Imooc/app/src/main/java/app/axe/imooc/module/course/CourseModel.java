package app.axe.imooc.module.course;


import java.util.ArrayList;

import app.axe.imooc.module.BaseModel;

/**
 * @author: vision
 * @function:
 * @date: 16/9/8
 */
public class CourseModel extends BaseModel {

    public CourseHeaderValue head;
    public CourseFooterValue footer;
    public ArrayList<CourseCommentValue> body;
}
