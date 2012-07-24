package com.amucan.saldobip;

import android.widget.TextView;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
* Request the balance for the given card_id
*/

public class RequestBalanceActivity extends Activity {
    private  List<NameValuePair> sessionParams = new ArrayList<NameValuePair>(7);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String card_id = intent.getStringExtra(SaldoBipActivity.EXTRA_MESSAGE);
        String balance = "";

        // Make request
        HttpClient client = new DefaultHttpClient();
        String webservice = "http://200.6.67.22/PortalCAE-WAR-MODULE/SesionPortalServlet";
        HttpPost httppost = new HttpPost(webservice);
        String line = "";

        try {
            setParams(card_id);
            // Execute request
            httppost.setEntity(new UrlEncodedFormEntity(sessionParams));
            HttpResponse response = client.execute(httppost);

            BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

            // Look gor balance
            while ((line = rd.readLine()) != null) {
                for(int i=0; i < line.length(); i++){
                    if (line.charAt(i) == '$'){
                        do {
                            balance += line.charAt(i);
                            i++;
                        } while(line.charAt(i) != '<');
                        break;
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(balance);

        setContentView(textView);
    }

    /**
    * Set params for balance request
    */
    public void setParams(String card_id){
        this.sessionParams.add(new BasicNameValuePair("NomDominio", "aft.cl"));
        this.sessionParams.add(new BasicNameValuePair("NomHost", "AFT"));
        this.sessionParams.add(new BasicNameValuePair("NomUsuario", "usuInternet"));
        this.sessionParams.add(new BasicNameValuePair("NumDistribuidor", "99"));
        this.sessionParams.add(new BasicNameValuePair("accion", "6"));
        this.sessionParams.add(new BasicNameValuePair("RutUsuario", "0"));
        this.sessionParams.add(new BasicNameValuePair("bloqueable", ""));
        this.sessionParams.add(new BasicNameValuePair("NumTarjeta", card_id));
    }
}
