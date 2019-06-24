package com.yhcxxt.wanandroid.model;

public class BannerBean {

//     "data": [
//    {
//        "desc": "一起来做个App吧",
//            "id": 10,
//            "imagePath": "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
//            "isVisible": 1,
//            "order": 1,
//            "title": "一起来做个App吧",
//            "type": 0,
//            "url": "http://www.wanandroid.com/blog/show/2"
//    },

    private String desc;
    private String imagePath;//图片
    private String title;//标题
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
