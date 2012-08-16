package com.amucan.saldobip;

import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
* Request the balance for the given card_id
*/

public class RequestManager {

  /* sessionParams holds the request parameters*/
  private  List<NameValuePair> sessionParams = new ArrayList<NameValuePair>(8);
  private String card_id;

  public RequestManager(){
  }

  public String runRequest(String card_id){
    this.card_id = card_id;
    // Make request
    HttpClient client = new DefaultHttpClient();
    String webservice = "http://200.6.67.22/PortalCAE-WAR-MODULE/SesionPortalServlet";
    HttpPost httppost = new HttpPost(webservice);
    String balance = "";

    try {
      setParams(card_id);
      // Execute request
      httppost.setEntity(new UrlEncodedFormEntity(sessionParams));
      HttpResponse response = client.execute(httppost);

      BufferedReader rd = new BufferedReader(
          new InputStreamReader(response.getEntity().getContent()));

      balance = searchToken(rd, '$');
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return balance;
  }

  /**
   * Search for the number next to the given token.
   */
  private String searchToken(BufferedReader rd, char token){
    String line = "";
    String balance = "";

    try {
      while ((line = rd.readLine()) != null) {
        for(int i=0; i < line.length(); i++){
          if (line.charAt(i) == token){
            do {
              balance += line.charAt(i);
              i++;
            } while(line.charAt(i) != '<');
            break;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return balance.substring(1);
  }
  /**
  * Set params for balance request
  */
  private void setParams(String card_id){
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
