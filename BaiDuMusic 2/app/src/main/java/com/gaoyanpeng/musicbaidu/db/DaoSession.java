package com.gaoyanpeng.musicbaidu.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.gaoyanpeng.musicbaidu.db.HousPerson;

import com.gaoyanpeng.musicbaidu.db.HousPersonDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig housPersonDaoConfig;

    private final HousPersonDao housPersonDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        housPersonDaoConfig = daoConfigMap.get(HousPersonDao.class).clone();
        housPersonDaoConfig.initIdentityScope(type);

        housPersonDao = new HousPersonDao(housPersonDaoConfig, this);

        registerDao(HousPerson.class, housPersonDao);
    }
    
    public void clear() {
        housPersonDaoConfig.clearIdentityScope();
    }

    public HousPersonDao getHousPersonDao() {
        return housPersonDao;
    }

}