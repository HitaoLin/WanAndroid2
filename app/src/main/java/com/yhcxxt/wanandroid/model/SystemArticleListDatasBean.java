package com.yhcxxt.wanandroid.model;
/**
 * <pre>
 *     author:LHT
 *     date:20190507
 *     desc:知识体系文章列表 model 三级
 * </pre>
 */
public class SystemArticleListDatasBean {

//    "apkLink": "",
//                "author": " ztq",
//                "chapterId": 60,
//                "chapterName": "Android Studio相关",
//                "collect": false,
//                "courseId": 13,
//                "desc": "",
//                "envelopePic": "",
//                "fresh": false,
//                "id": 8201,
//                "link": "https://juejin.im/post/5c9f2412f265da30bd3e428c",
//                "niceDate": "2019-04-06",
//                "origin": "",
//                "prefix": "",
//                "projectLink": "",
//                "publishTime": 1554538973000,
//                "superChapterId": 60,
//                "superChapterName": "开发环境",
//                "tags": [],
//                "title": "零报错基于Virtualbox虚拟机搭建Linux(Ubuntu)的Android开发环境",
//                "type": 0,
//                "userId": -1,
//                "visible": 1,
//                "zan": 0


    private String author;//作者
    private String title;//标题
    private String niceDate;//时间
    private String link;//文章链接
    private String chapterName;//属于
    private String collect;//收藏

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }
}
