package com.android.dt.readgamenews;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.dt.readgamenews.asytask.MainAsytask;
import com.android.dt.readgamenews.handler.MySaxParser;
import com.android.dt.readgamenews.models.HomePage;
import com.android.dt.readgamenews.models.Item;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import com.android.dt.readgamenews.HomePage;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    ListView lv;
    List<HomePage> ds_home;
    ArrayList<Item> items=new ArrayList<Item>();
    MySaxParser mysaxparser;
    String chuoi="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ds_home=new ArrayList<HomePage>();
        lv=(ListView)findViewById(R.id.listView2);
        new xulygetallhome().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class xulygetallhome extends AsyncTask<Void,Void,Void>
    {
        MyFunctions myfunctions;

        @Override
        protected Void doInBackground(Void... params) {
            String thanhcong=null;
            mysaxparser=new MySaxParser();
            try
            {
               myfunctions=new MyFunctions(MainActivity.this);
                JSONObject jsonobject=myfunctions.getAllHomePage();
                thanhcong=jsonobject.getString("thanhcong");
                if(Integer.parseInt(thanhcong)==1)
                {
                    JSONArray jsonarray=jsonobject.getJSONArray("sanpham");
                    HomePage homePage;
                    for(int i=0;i<jsonarray.length();i++)
                    {
                        JSONObject item=jsonarray.getJSONObject(i);
                        homePage = new HomePage();
                        homePage.setId(item.getString("home_page_id"));
                        homePage.setName(item.getString("home_page_name"));
                        homePage.setRss(item.getString("home_page_rss"));
                        ds_home.add(homePage);
                    }
                }
                for(HomePage homePage : ds_home)
                {
                    items.addAll((ArrayList<Item>)mysaxparser.xmlParser(homePage.getRss()));
                }
                for(int i=0;i<items.size();i++)
                {
                    chuoi+=items.get(i).getTitle()+" ";
                }
            }catch(Exception e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("dulieu",chuoi);
            //ListAdapter adapter=new SimpleAdapter(MainActivity.this,ds_home,R.layout.item_list,new String[]{"id","name","rss"},new int[]{R.id.textView,R.id.textView2,R.id.textView3});
            //lv.setAdapter(adapter);
        }
    }
}
