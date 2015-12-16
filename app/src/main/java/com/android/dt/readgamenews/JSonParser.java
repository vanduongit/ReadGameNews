package com.android.dt.readgamenews;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by toan on 12/14/2015.
 */
public class JSonParser {
    static InputStream is;
    static JSONObject jobj;
    static String json="";

    public JSonParser() {

    }

    public JSONObject getJsonFromUrl(String url,List<NameValuePair>cacdoiso)
    {
        try
        {
            DefaultHttpClient httpclient=new DefaultHttpClient();
            HttpPost httppost=new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(cacdoiso));
            HttpResponse httpresponse=httpclient.execute(httppost);
            HttpEntity httpentity=httpresponse.getEntity();
            is=httpentity.getContent();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"),8);
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=reader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
            is.close();
            json=sb.toString();
            jobj=new JSONObject(json);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return  jobj;
    }
}
