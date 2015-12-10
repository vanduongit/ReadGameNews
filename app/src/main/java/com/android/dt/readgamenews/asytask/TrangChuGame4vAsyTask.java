package com.android.dt.readgamenews.asytask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.android.dt.readgamenews.adapter.MyAdapter;
import com.android.dt.readgamenews.handler.MySaxHandler;
import com.android.dt.readgamenews.handler.MySaxParser;
import com.android.dt.readgamenews.models.Item;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by toan on 12/8/2015.
 */
public class TrangChuGame4vAsyTask extends AsyncTask<Void,Void,Void> {
    ArrayList<Item> items=new ArrayList<Item>();
    InputStream is;
    String chuoi="";
    String diachi="http://vnexpress.net/rss/tin-moi-nhat.rss";
    MySaxParser mysaxparser;
    ListView lv;
    Context context;

    public TrangChuGame4vAsyTask(Context context, ListView lv) {
        this.context=context;
        this.lv=lv;
    }

    @Override
    protected Void doInBackground(Void... params) {
            mysaxparser=new MySaxParser();
        try
        {
            URL url=new URL(diachi);
            URLConnection urlconnection=url.openConnection();
            is=urlconnection.getInputStream();
            items=(ArrayList<Item>) mysaxparser.xmlParser(diachi);
            for(int i=0;i<items.size();i++)
            {
                chuoi+= items.get(i).getTitle()+" ";
                chuoi+=items.get(i).getDescription()+"";
                chuoi+=items.get(i).getPubdate()+"\n";
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("dulieu", chuoi);
        lv.setAdapter(new MyAdapter(context,items));
    }
}
