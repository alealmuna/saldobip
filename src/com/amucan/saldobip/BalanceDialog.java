package com.amucan.saldobip;

import android.support.v4.app.DialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Custom Dialog that deploys the response information
 */
public class BalanceDialog extends DialogFragment {
  private String balance; 

  public BalanceDialog(){
    // Empty constructor required for DialogFragment
  }
  public BalanceDialog(String balance){
    this.balance = balance;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.balance_dialog, container);
    getDialog().setTitle(R.string.req_dialog_title);
    TextView text = (TextView) view.findViewById(R.id.text);
    text.setText('$' + balance);

    return view;
  }
}
