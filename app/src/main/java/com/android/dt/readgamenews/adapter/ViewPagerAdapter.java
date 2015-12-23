package com.android.dt.readgamenews.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.dt.readgamenews.handler.MySaxParser;
import com.android.dt.readgamenews.models.HomePage;
import com.android.dt.readgamenews.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toan on 12/23/2015.
 */
public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    List<HomePage> ds_home;
    List<Item>items=new ArrayList<Item>();
    String chuoirss="";
    ListView lv;
    String test;
    public ViewPagerAdapter(Context context, List<HomePage> ds_home) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.ds_home = ds_home;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ds_home.size();
    }

    @Override
    public boolean isViewFromObject(View container, Object position) {
        // TODO Auto-generated method stub

        return container==position;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        chuoirss=ds_home.get(position).getRss();
        lv=new ListView(context);
        new xulyadapter().execute();
        ((ViewPager)container).addView(lv);
        return lv;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager)container).removeView((View) object);
    }

    public class xulyadapter extends AsyncTask<Void, Void, Void>
    {
        MySaxParser mysaxparser=new MySaxParser();;
        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                items=((ArrayList<Item>)mysaxparser.xmlParser(chuoirss));

            } catch (Exception e) {
                // TODO: handle exception
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stu
            ListViewAdapter adapter=new ListViewAdapter(context,items);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                }
            });
            super.onPostExecute(result);
        }

    }
}
