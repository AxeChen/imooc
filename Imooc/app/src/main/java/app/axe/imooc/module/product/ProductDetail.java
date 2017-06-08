package app.axe.imooc.module.product;

import java.util.List;

/**
 * Created by Chen on 2017/5/20.
 */

public class ProductDetail {

    /**
     * lessonType : 0
     * title : Grunt自动化测试
     * descrption : Grunt自动化测试，作为一个测试注解框架，风靡全球。全球著名的测试工程师Danny曾经称赞其为‘无法媲美的测试框架’！来自优酷首席安卓工程师兼首席测试工程师对其深有了解，于是录制了教学视频为广大测试工程师提供技术支持
     * teacher : qndroid
     * teacherId : imooc1234
     * timeStr : 4小时
     * start : 5
     * pics : ["http://img.mukewang.com/54f55ee60001850f05000280.jpg","http://img7.doubanio.com/view/note/large/public/p26832324.jpg","http://img.mukewang.com/546570370001f8a906000338-590-330.jpg","http://img.mukewang.com/55f93f980001f52125001408-590-330.jpg"]
     */

    private int lessonType;
    private String title;
    private String descrption;
    private String teacher;
    private String teacherId;
    private String timeStr;
    private int start;
    private List<String> pics;

    public int getLessonType() {
        return lessonType;
    }

    public void setLessonType(int lessonType) {
        this.lessonType = lessonType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
