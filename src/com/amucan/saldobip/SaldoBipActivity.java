package com.amucan.saldobip;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

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
    public void sendMessage(View view) {
        Intent intent = new Intent(this, RequestBalanceActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
