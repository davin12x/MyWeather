package com.example.bagga.myweather;

import android.os.AsyncTask;
import android.telecom.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Davin12x on 16-04-07.
 */
public class Services extends AsyncTask<String,Void,String> {
    URL url ;
    HttpURLConnection connection = null;
    StringBuilder stringBuilder;

    private String _temp;
    private String _text;
    private String _cloud;
    private String _code;
    private  String _name;

    String Temp(){
        return _temp;
    }
    String Name(){
        return  _name;
    }
    String Text(){
        return  _text;
    }
    String Cloud(){
        return  _cloud;
    }
    String Code(){
        return _code;
    }
    public void result(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject weatherinf0 = jsonObject.getJSONObject("current");
            JSONObject condition = weatherinf0.getJSONObject("condition");
            String text = condition.getString("text");
            String icon = condition.getString("icon");
            String code = condition.getString("code");
            String temp_c = weatherinf0.getString("temp_c");
            String cloud = weatherinf0.getString("cloud");
            JSONObject location = jsonObject.getJSONObject("location");
            String name = location.getString("name");
            this._name = name;
            this._temp = temp_c;
            this._cloud = cloud;
            this._text = text;
            this._code = code;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected String doInBackground(String... urls) {
        try{
            url = new URL(urls[0]);
           connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            stringBuilder = new StringBuilder();
            int reader = inputStreamReader.read();
            while (reader != -1){
                char result = (char)reader;
                stringBuilder.append(result);
                reader = inputStreamReader.read();
            }
            return  stringBuilder.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
