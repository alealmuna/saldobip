package com.amucan.saldobip;

import com.amucan.saldobip.db.DatabaseManager;
import com.amucan.saldobip.db.Request;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater; 
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class HistoryList extends ListFragment {
  private List <Request> history; 
  private RequestAdapter adapter;
  private DatabaseManager databaseManager;
  private ListView mListView;
  private static String TAG = HistoryList.class.toString();
  
  public HistoryList(){
  // Empty constructor required for ListFragment
  }
  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate");
    super.onCreate(savedInstanceState);
  } 

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Log.d(TAG, "onCreateView");
    View view = inflater.inflate(R.layout.history_list, container, false);
    return view;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    Log.d(TAG, "onActivityCreated");
    super.onActivityCreated(savedInstanceState);
    this.databaseManager = DatabaseManager.getDBManagerInstance(
        this.getActivity());
    this.history = this.databaseManager.getAllRequests();
    Log.v(TAG, "Count: "+history.size());
    this.adapter = new RequestAdapter(this.getActivity(),
        android.R.id.list,
        history, (long)history.size()); 
    setListAdapter(this.adapter); 
  }
    
}
