package com.android.dt.readgamenews;

import android.content.Context;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toan on 12/14/2015.
 */
public class MyFunctions {
    JSonParser jsonparser;
    String getallhomepage="http://10.0.2.2/readnews/GetAllHomePage.php";
    Context context;

    public MyFunctions(Context context) {
        jsonparser=new JSonParser();
        this.context = context;
    }


    public JSONObject getAllHomePage()
    {
        List<NameValuePair>cacdoiso=new ArrayList<NameValuePair>();
        JSONObject jobj=jsonparser.getJsonFromUrl(getallhomepage,cacdoiso);
        return jobj;
    }
}
