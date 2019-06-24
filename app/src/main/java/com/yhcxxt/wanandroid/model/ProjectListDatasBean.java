package com.yhcxxt.wanandroid.model;

import java.util.List;

public class ProjectListDatasBean {

//      "apkLink": "",
//                "author": "jimmysuncpt",
//                "chapterId": 294,
//                "chapterName": "完整项目",
//                "collect": false,
//                "courseId": 13,
//                "desc": "UltraRecyclerView是一个封装多种特性的RecyclerView。主要功能包括：\r\n- 支持横向滑动／纵向滑动\r\n- 支持分页滑动，并且支持对齐方式和距离\r\n- 支持循环滚动\r\n- 支持定时自动滚动，计时器使用Handler实现\r\n- BannerView内置指示器，支持设置底部距离、已选/默认的颜色和宽度、高度和内部距离",
//                "envelopePic": "https://www.wanandroid.com/blogimgs/0858c600-1b34-41c1-a2ff-f67cdc376558.png",
//                "fresh": true,
//                "id": 8467,
//                "link": "http://www.wanandroid.com/blog/show/2574",
//                "niceDate": "20小时前",
//                "origin": "",
//                "prefix": "",
//                "projectLink": "https://github.com/jimmysuncpt/UltraRecyclerView",
//                "publishTime": 1558354164000,
//                "superChapterId": 294,
//                "superChapterName": "开源项目主Tab",
//                "tags": [
//                    {
//                        "name": "项目",
//                        "url": "/project/list/1?cid=294"
//                    }
//                ],
//                "title": "UltraRecyclerView是一个封装多种特性的RecyclerView",
//                "type": 0,
//                "userId": -1,
//                "visible": 1,
//                "zan": 0


    private String author;//作者
    private String chapterId;
    private String chapterName;//项目分类
    private String collect;//收藏
    private String desc;//描述
    private String envelopePic;//图片
    private String link;//链接
    private String niceDate;//时间
    private String projectLink;//项目地址
    private String superChapterName;
    private String title;//标题

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
