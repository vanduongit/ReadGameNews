package com.android.dt.readgamenews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.dt.readgamenews.adapter.NavListAdapter;
import com.android.dt.readgamenews.fragment.Game4v_online;

import com.android.dt.readgamenews.fragment.PC_Console_Game4v;
import com.android.dt.readgamenews.fragment.TrangChu_Game4v;
import com.android.dt.readgamenews.models.NavItem;

import java.util.ArrayList;
import java.util.List;

public class Game4v extends FragmentActivity {

    DrawerLayout drawerLayout;
    RelativeLayout drawerpane;
    ListView lv;
    List<NavItem> navItemList;
    List<Fragment> fragmentList;
    String tbtitle;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4v);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerpane=(RelativeLayout)findViewById(R.id.drawer_pane);
        lv=(ListView)findViewById(R.id.nav_list);
        navItemList=new ArrayList<NavItem>();
        navItemList.add(new NavItem("Trang chu",R.mipmap.ic_launcher));
        navItemList.add(new NavItem("Game Online",R.mipmap.ic_launcher));
        navItemList.add(new NavItem("PC-Console", R.mipmap.ic_launcher));
        lv.setAdapter(new NavListAdapter(Game4v.this, navItemList));

        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(new TrangChu_Game4v());
        fragmentList.add(new Game4v_online());
        fragmentList.add(new PC_Console_Game4v());

        FragmentManager fmanager=getSupportFragmentManager();
        FragmentTransaction tran=fmanager.beginTransaction();
        tran.replace(R.id.main_content, fragmentList.get(0));
        tran.commit();
        setTitle(navItemList.get(0).getTitle());
        lv.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerpane);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tbtitle = navItemList.get(position).getTitle();
                FragmentManager fmanager = getSupportFragmentManager();
                FragmentTransaction tran = fmanager.beginTransaction();
                tran.replace(R.id.main_content, fragmentList.get(position));
                tran.commit();
                setTitle(navItemList.get(position).getTitle());
                lv.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerpane);
            }
        });
        tbtitle=(String)getTitle();
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.mipmap.ic_drawer,R.string.drawer_opened,R.string.drawer_closed)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("xem gi chon do");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setTitle(tbtitle);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
