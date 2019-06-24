package com.yhcxxt.wanandroid.model;
/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:微信公众号文章 model 三级
 * </pre>
 */
public class WechartArticleDatasBean {


//    "datas": [
//    {
//        "apkLink": "",
//            "author": "鸿洋",
//            "chapterId": 408,
//            "chapterName": "鸿洋",
//            "collect": false,
//            "courseId": 13,
//            "desc": "",
//            "envelopePic": "",
//            "fresh": false,
//            "id": 8318,
//            "link": "https://mp.weixin.qq.com/s/GYhA1P4hq_YThHddHXYHzw",
//            "niceDate": "1天前",
//            "origin": "",
//            "prefix": "",
//            "projectLink": "",
//            "publishTime": 1556465140000,
//            "superChapterId": 408,
//            "superChapterName": "公众号",
//            "tags": [
//        {
//            "name": "公众号",
//                "url": "/wxarticle/list/408/1"
//        }
//                ],
//        "title": "Android 你可能错过的信息 | 2 期",
//            "type": 0,
//            "userId": -1,
//            "visible": 0,
//            "zan": 0
//    },


    private String author;//作者
    private String niceDate;//日期
    private String title;//标题
    private String superChapterName;
    private String chapterName;
    private String link;
    private String collect;//收藏
    private String chapterId;//公众号ID

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}
