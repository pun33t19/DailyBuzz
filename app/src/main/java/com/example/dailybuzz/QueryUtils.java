package com.example.dailybuzz;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

        public static URL stringToUrl(String urlString){
            URL url=null;

            try {
                url=new URL(urlString);
                return url;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }

        public static String makeHTTPRequest(URL url) {
            String jsonResponse=null;

            HttpURLConnection httpURLConnection=null;
            InputStream inputStream=null;

            try {
                httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (httpURLConnection.getResponseCode()==200){
                    inputStream= httpURLConnection.getInputStream();
                    jsonResponse=getJsonResponse(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        public static String getJsonResponse(InputStream inputStream){
            StringBuilder s1=new StringBuilder();

            if (inputStream!=null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader=new BufferedReader(inputStreamReader);

                try {
                    String line=reader.readLine();

                    while (line!=null){
                        s1.append(line);
                        line=reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.v("TAG","InputStreamReador is null");
            }

            return s1.toString();
        }

        public static List<NewsDataClass> parseJsonResponse(String jsonResponse){
            List<NewsDataClass> newsList=new ArrayList<>();

            if (jsonResponse==null){
                Log.v("Null","JsonResponse is null");
                        return null;
            }

            try {
                JSONObject root=new JSONObject(jsonResponse);

                JSONArray objectArray=root.optJSONArray("data");

                for (int i = 0; i < objectArray.length(); i++) {
                    JSONObject data= objectArray.optJSONObject(i);

                    String author=data.optString("author");
                    String content=data.optString("content");
                    String date=data.optString("date");
                    String imageUrl=data.optString("imageUrl");
                    String readMoreUrl=data.optString("readMoreUrl");
                    String time=data.optString("time");
                    String title=data.optString("title");

                    newsList.add(new NewsDataClass(author,content,date,imageUrl,readMoreUrl,time,title));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return newsList;
        }
}
