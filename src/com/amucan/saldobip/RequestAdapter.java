package com.amucan.saldobip;

import com.amucan.saldobip.R;
import com.amucan.saldobip.db.Request;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.text.SimpleDateFormat;

public class RequestAdapter extends ArrayAdapter<Request>{
    private ListView mListView;
    private final Activity context;
    private List<Request> entries;
    private long count;
	  //private static String TAG = RequestsToApproveActivity.class.toString();
   
    public RequestAdapter(Activity context, int ViewResourceId, 
                    List<Request> entries, long count) {
        super(context, ViewResourceId, entries);
        this.context = context;
        this.entries = entries;
        this.count=count;
    }
   

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View v = convertView;
      ViewHolder holder = null; 

      if (v == null) {
        LayoutInflater vi =
            (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.request_item, null);
        holder = new ViewHolder();
        holder.card_id = (TextView) v.findViewById(R.id.card_id);
        holder.datetime = (TextView) v.findViewById(R.id.balance);
        holder.balance = (TextView) v.findViewById(R.id.datetime);
        v.setTag(holder);
      }
      else
      {
         holder=(ViewHolder)v.getTag();
      }
      Request request = entries.get(position);
      if (request != null) {
        holder.card_id.setText(request.getCardId());
        holder.datetime.setText("$" + request.getBalance());
        // TODO change getClientAdddress by getClientAddress
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("EEE, dd LLL hh:mm aa");
        String formattedDate = formatter.format(request.getDate());
        holder.balance.setText(formattedDate);
      }
      return v;
    }
/*                        
    public OnClickListener mOnAcceptClickListener  = new OnClickListener() {
      @Override
      public void onClick(View v) {
        final int position = this.context.getListView().getPositionForView(v);
        //Log.v(TAG, "Text clicked, row %d" + position);   
      }
    };
*/
    private class ViewHolder{
      public TextView card_id;
      public TextView datetime;
      public TextView balance;
    }

  }    
