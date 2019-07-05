package com.yhcxxt.wanandroid.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * date:20190704
 */
public class MyApplication extends Application {

    public static MyApplication instance=null;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        setDataBase();
    }

    public static MyApplication getInstances(){
        return instance;
    }

    private void setDataBase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "lcp-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }


    public static List<SearchHistory> getBloodsLikeDate(long userId, String measureDate) {
        List<SearchHistory> list = MyApplication.getInstances().getDaoSession().getSearchHistoryDao().queryBuilder()
                .where(
                SearchHistoryDao.Properties.Id.eq(userId),
                SearchHistoryDao.Properties.Name.like(measureDate + "%")
        )
                .orderDesc(SearchHistoryDao.Properties.Name)
                .build()
                .list();

        return list;
    }


}
