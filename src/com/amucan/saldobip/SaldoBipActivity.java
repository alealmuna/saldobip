package com.amucan.saldobip;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SaldoBipActivity extends Activity
{
  public final static String EXTRA_MESSAGE = "com.amucan.saldobip.MESSAGE";
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  /**
  * Start an intent to RequestBalanceActivity with inserted card id value.
  */
  public void sendMessage(View view) {
    Intent intent = new Intent(this, RequestBalanceActivity.class);
    EditText editNumber = (EditText) findViewById(R.id.edit_message);
    String message = editNumber.getText().toString();
    intent.putExtra(EXTRA_MESSAGE, message);
    startActivity(intent);
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
            
            Intent intent = new Intent(this, RequestBalanceActivity.class);
            intent.putExtra(EXTRA_MESSAGE, upc);
            startActivity(intent);
          }
        }
        break;
      }
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
}
