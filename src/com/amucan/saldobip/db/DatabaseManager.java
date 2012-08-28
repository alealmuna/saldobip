package com.amucan.saldobip.db;

import com.amucan.saldobip.db.DaoMaster.DevOpenHelper;
import com.amucan.saldobip.db.DaoSession;
import com.amucan.saldobip.db.RequestDao.Properties;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class DatabaseManager {

	private static DaoSession daoSession = null;
  private RequestDao requestDao;
	private DaoSession session;
  private static DatabaseManager databaseManager = null;

  private DatabaseManager(Context context){
    this.session = this.getDaoSession(context);
    this.requestDao = this.session.getRequestDao();
    }

	public static DaoSession getDaoSession(Context context){

		if(daoSession == null){

			DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "mayday-db", null);
			SQLiteDatabase db = helper.getWritableDatabase();
			DaoMaster daoMaster = new DaoMaster(db);
			daoSession = daoMaster.newSession();
		}
		
		return daoSession;
	}

	public static DatabaseManager getDBManagerInstance(Context context){

		if(databaseManager == null){
			databaseManager = new DatabaseManager(context);
			return databaseManager;
		}else{
			return databaseManager;
		}
	}
	
	public List<Request> getAllRequests(){
    List requests = this.requestDao.queryBuilder()
            .orderDesc(Properties.Date)
            .list();
		return requests;
	}
}
