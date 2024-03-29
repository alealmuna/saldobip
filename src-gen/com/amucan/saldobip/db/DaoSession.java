package com.amucan.saldobip.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.IdentityScopeType;

import com.amucan.saldobip.db.User;
import com.amucan.saldobip.db.Request;

import com.amucan.saldobip.db.UserDao;
import com.amucan.saldobip.db.RequestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig requestDaoConfig;

    private final UserDao userDao;
    private final RequestDao requestDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        requestDaoConfig = daoConfigMap.get(RequestDao.class).clone();
        requestDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        requestDao = new RequestDao(requestDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Request.class, requestDao);
    }
    
    public void clear() {
        userDaoConfig.getIdentityScope().clear();
        requestDaoConfig.getIdentityScope().clear();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public RequestDao getRequestDao() {
        return requestDao;
    }

}
