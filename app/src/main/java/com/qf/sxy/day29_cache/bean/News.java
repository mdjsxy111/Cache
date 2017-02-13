package com.qf.sxy.day29_cache.bean;

/**
 * Created by sxy on 2016/10/20.
 */
public class News {

//    "subject": "IBM核心业务将是超智能计算机",
//            "summary": "罗睿兰认为IBM未来的核心业务是能够像人一样讲话和推理的超级智能计算机。",
//            "cover": "/Attachs/Article/288231/4d1ca5d13a01421482a87290830db856_padmini.JPG",
    private String subject;
    private String summary;
    private String cover;



    public News(String subject, String summary, String cover) {
        this.subject = subject;
        this.summary = summary;
        this.cover = cover;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
