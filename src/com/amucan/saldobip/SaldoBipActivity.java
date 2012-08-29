package com.amucan.saldobip;

import com.amucan.saldobip.db.DaoSession;
import com.amucan.saldobip.db.DatabaseManager;
import com.amucan.saldobip.db.Request;
import com.amucan.saldobip.db.RequestDao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Date;
import java.util.Locale;

public class SaldoBipActivity extends FragmentActivity 
{
  public final static String EXTRA_MESSAGE = "com.amucan.saldobip.MESSAGE";
  public String balance = "";
	private DaoSession session;
	private RequestDao requestDao;
	private static String TAG = SaldoBipActivity.class.toString();
  private DatabaseManager databaseManager;
  private HistoryList historyList = new HistoryList(); 
  private FragmentManager fm = null;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    this.session = this.databaseManager.getDaoSession(this.getApplicationContext());
    this.requestDao = this.session.getRequestDao();
    //this.addMockData();
    Locale.setDefault(new Locale("es", "ES"));  
    setContentView(R.layout.main);
    this.fm = getSupportFragmentManager();
    if ((fm != null) && (savedInstanceState == null)) {
      FragmentTransaction ft = this.fm.beginTransaction();
      ft = fm.beginTransaction();
      ft.add(R.id.history_frame, this.historyList);
      ft.commit();
    }
    Log.d(TAG, "onCreate");
  }

  private void showBalanceDialog(String balance) {
    BalanceDialog balanceDialog = new BalanceDialog(balance);
    balanceDialog.show(this.fm, "balance_dialog");
  }

  /**
  * Start an intent to RequestBalanceActivity with inserted card id value.
  */
  public void sendMessage(View view) {
    EditText editNumber = (EditText) findViewById(R.id.edit_message);
    String message = editNumber.getText().toString();
    requestBalance(message);
  }

  /**
  * Initiate scanning when scan button pressed
  */
  public void scanCard(View view) {
    (new IntentIntegrator(this)).initiateScan();
  }

  /**
  * Barcode callback
  */
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch(requestCode) {
      case IntentIntegrator.REQUEST_CODE: {
        if (resultCode != RESULT_CANCELED) {
          IntentResult scanResult = IntentIntegrator.parseActivityResult(
            requestCode, resultCode, data);
          if (scanResult != null) {
            String upc = scanResult.getContents();
            requestBalance(upc);
          }
        }
        break;
      }
    }
  }

  /**
  * Run request
  */
  public void requestBalance(String card_id){
    RequestManager requestManager = new RequestManager();
    String balance = requestManager.runRequest(card_id);
    if (!balance.equals("")){
      showBalanceDialog(balance);
      addToHistory(balance,card_id);
    }
  }

  public void addToHistory(String balance, String card_id){
    Log.d(TAG, "Updating History");
    int i_balance = Integer.parseInt(balance.replace(".",""));
		try{
      Request request = new Request(null, card_id,
          -33.444518, -70.653664, i_balance, new Date(), new Long(123456));
      this.requestDao.insert(request);
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
    if (fm != null) {
      FragmentTransaction ft = this.fm.beginTransaction();
      ft.replace(R.id.history_frame, this.historyList);
      ft.commit();
    }
  }

  /**
  * Main Menu
  */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

	private void addMockData(){
		try{
		  this.requestDao.deleteAll();
      Request request = new Request(null,"123456",
          -33.444518, -70.653664, 1500, new Date(),new Long(654321));
      this.requestDao.insert(request);
      request = new Request(null,"234567",
          -33.444518, -70.653664, 2500, new Date(),new Long(765432));
      this.requestDao.insert(request);
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
  }
}
