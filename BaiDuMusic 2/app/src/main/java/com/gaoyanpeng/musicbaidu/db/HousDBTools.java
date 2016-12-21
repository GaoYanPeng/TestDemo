package com.gaoyanpeng.musicbaidu.db;

import com.gaoyanpeng.musicbaidu.app.MyApplication;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 高研鹏 on 2016/12/13.
 */
public class HousDBTools {
    private static HousDBTools ourInstance = new HousDBTools();
    private static HousPersonDao mPersonDao;

    public static HousDBTools getInstance() {
        if (ourInstance == null){
            synchronized (HousDBTools.class){
                if (ourInstance == null){
                    ourInstance = new HousDBTools();
                }
            }
        }
        //初始化xxxDao对象
        mPersonDao = MyApplication.getDaoSession().getHousPersonDao();
        return ourInstance;
    }

    private HousDBTools() {
    }
    //增加单一对象的方法
    public void insertPerson(HousPerson person){
        mPersonDao.insert(person);
    }
    //增加集合的方法
    public void insertList(List<HousPerson> list){
        mPersonDao.insertInTx(list);
    }
    //删除单一对象的方法
    public void deletePerson(HousPerson person){
        mPersonDao.delete(person);
    }
    //删除所有内容
    public void deleteAll(){
        mPersonDao.deleteAll();
    }
    //根据id进行删除
    public void deleteById(Long id){
        mPersonDao.deleteByKey(id);
    }
    //根据某一个字段进行删除操作
    public void deleteByName(String name){
        DeleteQuery<HousPerson> deleteQuery = mPersonDao.
                queryBuilder().where(
                HousPersonDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();;
    }
    //根据姓名 性别 年龄 进行删除
    public void deleteBy(String name,String songName,String image){
        DeleteQuery<HousPerson> deleteQuery = mPersonDao.queryBuilder()
                .where(HousPersonDao.Properties.Name.eq(name)
                        ,HousPersonDao.Properties.SongName.eq(songName),
                        HousPersonDao.Properties.Image.eq(image))
                .buildDelete();
        if (deleteQuery != null){
            deleteQuery.executeDeleteWithoutDetachingEntities();
        }
    }
    //查询所有的方法
    public List<HousPerson> queryAll(){
        //查询方法1
        List<HousPerson> list = mPersonDao.loadAll();
        //查询方法2
        List<HousPerson> personList = mPersonDao.queryBuilder().list();
        return list;
    }
    //查重方法
    //根据姓名来查询
    public boolean isSave(String name){
        QueryBuilder<HousPerson> queryBuilder = mPersonDao.
                queryBuilder().where(HousPersonDao.Properties.Name.eq(name));
        //获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }

    public boolean isSave(HousPerson person){
        QueryBuilder<HousPerson> queryBuilder =
                mPersonDao.queryBuilder().
                        where(HousPersonDao.Properties.Name.eq(person.getName())
                                ,HousPersonDao.Properties.Image.eq(person.getImage()),
                                HousPersonDao.Properties.SongName.eq(person.getSongName()));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
}
