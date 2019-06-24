package com.yhcxxt.wanandroid.model;

/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:首页文章 model 三级
 * </pre>
 */
public class ArticleDatasBean {

    private String author;//作者
    private String niceDate;//日期
    private String title;//标题
    private String superChapterName;
    private String chapterName;
    private String link;
    private String collect;//收藏

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



    //    "data": {
//        "curPage": 1,
//                "datas": [
//        {
//            "apkLink": "",
//                "author": "KunMinX",
//                "chapterId": 77,
//                "chapterName": "响应式编程",
//                "collect": false,
//                "courseId": 13,
//                "desc": "",
//                "envelopePic": "",
//                "fresh": true,
//                "id": 8274,
//                "link": "https://juejin.im/post/5cb82a42e51d456e62545ac6",
//                "niceDate": "21小时前",
//                "origin": "",
//                "prefix": "",
//                "projectLink": "",
//                "publishTime": 1555593395000,
//                "superChapterId": 191,
//                "superChapterName": "热门专题",
//                "tags": [],
//            "title": "你用不惯 RxJava，只因缺了这把钥匙",
//                "type": 0,
//                "userId": -1,
//                "visible": 1,
//                "zan": 0
//        },
}
