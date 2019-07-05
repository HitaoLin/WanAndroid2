package com.yhcxxt.wanandroid.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * date:20190704
 */
@Entity
public class SearchHistory {

    @Id(autoincrement = true)//id自增长
    private long id;
    private String name;
    @Generated(hash = 174600038)
    public SearchHistory(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }



}
