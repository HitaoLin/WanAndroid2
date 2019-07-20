package com.yhcxxt.wanandroid.model;

/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:首页文章 model 三级
 * </pre>
 */
public class CollectDatasBean {

    private String author;//作者
    private String niceDate;//日期
    private String title;//标题
    private String superChapterName;
    private String chapterName;
    private String link;


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




    //    "author": "TeaOf",
    //                "chapterId": 423,
    //                "chapterName": "Architecture",
    //                "courseId": 13,
    //                "desc": "",
    //                "envelopePic": "",
    //                "id": 72544,
    //                "link": "https://www.jianshu.com/p/815c7db24b6d",
    //                "niceDate": "1小时前",
    //                "origin": "",
    //                "originId": 8700,
    //                "publishTime": 1563436492000,
    //                "title": "即学即用Android Jetpack - Room",
    //                "userId": 23071,
    //                "visible": 0,
    //                "zan": 0
}
