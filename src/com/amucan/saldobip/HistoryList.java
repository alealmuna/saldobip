package com.amucan.saldobip;

import com.amucan.saldobip.db.DaoSession; 
import com.amucan.saldobip.db.DatabaseManager;
import com.amucan.saldobip.db.Request;
//import com.amucan.saldobip.db.RequestDao;

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
    super.onCreate(savedInstanceState);
    getActivity().setContentView(R.layout.history_list);
    Log.d(TAG, "onCreate");
    this.databaseManager = DatabaseManager.getDBManagerInstance(
        this.getActivity());
    this.history = this.databaseManager.getAllRequests();
  }
    
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    Log.d(TAG, "onActivityCreated");
    super.onActivityCreated(savedInstanceState);
    //this.mListView = (ListView) getView().findViewById(android.R.id.list);
    Log.v(TAG, "Count: "+history.size());
    //getActivity().setContentView(R.layout.history_list);
    this.adapter = new RequestAdapter(this.getActivity(),
        android.R.id.list,
        history, (long)history.size()); 
    setListAdapter(this.adapter); 
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.history_list, container, false);
    return view;
  }
}
