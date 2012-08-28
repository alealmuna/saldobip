package com.amucan.saldobip.db;

import com.amucan.saldobip.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table REQUEST.
 */
public class Request {

    private Long id;
    private String cardId;
    private Double latitude;
    private Double longitude;
    private Integer balance;
    private java.util.Date date;
    private Long userId;

    /** Used to resolve relations */
    private DaoSession daoSession;

    /** Used for active entity operations. */
    private RequestDao myDao;

    private User user;
    private Long user__resolvedKey;


    public Request() {
    }

    public Request(Long id) {
        this.id = id;
    }

    public Request(Long id, String cardId, Double latitude, Double longitude, Integer balance, java.util.Date date, Long userId) {
        this.id = id;
        this.cardId = cardId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.balance = balance;
        this.date = date;
        this.userId = userId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRequestDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /** To-one relationship, resolved on first access. */
    public User getUser() {
        if (user__resolvedKey == null || !user__resolvedKey.equals(userId)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            user = targetDao.load(userId);
            user__resolvedKey = userId;
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        userId = user == null ? null : user.getId();
        user__resolvedKey = userId;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
