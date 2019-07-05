package com.yhcxxt.wanandroid.greendao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;
/**
 * date:20190704
 */
public class GreendaoManager<T,D extends AbstractDao<T,Long>> {

    private D dao;

    public GreendaoManager(D dao) {
        this.dao = dao;
    }

    /**
     * 增或者替换（有的话就替换，没有则增）
     */
    public void insertOrReplace(T bean) {
        dao.insertOrReplace(bean);
    }


    /**
     * 删
     *
     * @param i 删除数据的id
     */
    public void delete(long i) {
        dao.deleteByKey(i);
        //当然Greendao还提供了其他的删除方法，只是传值不同而已
    }

    /**
     * 删除所有
     */
    public void deleteAll() {
        dao.deleteAll();
    }


    /**
     * 改
     *
     * @param
     */
    public void correct(T bean) {
        dao.update(bean);
    }


    /**
     * 查找符合某一条件的所有元素
     */
    public List<T> searchEveryWhere(WhereCondition condition) {
        LazyList<T> DBbeans = dao.queryBuilder().where(condition).listLazy();
        return DBbeans;
    }

    /**
     * 查询所有数据
     *
     * @param
     * @return
     */
    public List<T> queryAll() {
        //惰性
        LazyList<T> DBbeans = dao.queryBuilder().listLazy();
        return DBbeans;
    }
}
