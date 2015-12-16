package com.android.dt.readgamenews.asytask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.dt.readgamenews.MyFunctions;
import com.android.dt.readgamenews.R;
import com.android.dt.readgamenews.handler.MySaxParser;
import com.android.dt.readgamenews.models.HomePage;
import com.android.dt.readgamenews.models.Item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by toan on 12/14/2015.
 */
public class MainAsytask extends AsyncTask<Void,Void,Void> {
    Context context;
    MyFunctions myfunctions;
    ListView lv;
    ArrayList<HashMap<String,String>>ds_trangchu;
    String diachi;
    String chuoi="";
    MySaxParser mysaxparser;
    ArrayList<Item> items=new ArrayList<Item>();

    public MainAsytask(Context context,ListView lv) {
        this.context=context;
        this.lv=lv;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String thanhcong=null;
        try
        {
            myfunctions= new MyFunctions(context);
            JSONObject jobj=myfunctions.getAllHomePage();
            thanhcong=jobj.getString("thanhcong");
            if(Integer.parseInt(thanhcong)==1) {
                JSONArray jsonarray = jobj.getJSONArray("sanpham");
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject item = jsonarray.getJSONObject(i);
                    String id = item.getString("home_page_id");
                    String name = item.getString("home_page_name");
                    String rss = item.getString("home_page_rss");
                    HashMap<String,String> hm=new HashMap<String,String>();
                    hm.put("id",id);
                    hm.put("name",name);
                    hm.put("rss",rss);
                    ds_trangchu.add(hm);
                }

            }

        }catch(Exception e)
        {

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        ListAdapter adapter=new SimpleAdapter(context,ds_trangchu, R.layout.item_list,new String[]{"id","name","rss"},new int[]{R.id.textView,R.id.textView2,R.id.textView3});
        lv.setAdapter(adapter);

    }
}
