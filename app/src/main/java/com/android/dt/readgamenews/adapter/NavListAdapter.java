package com.android.dt.readgamenews.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dt.readgamenews.R;
import com.android.dt.readgamenews.models.NavItem;

import java.util.List;


public class NavListAdapter extends BaseAdapter {

    Context context;
    List<NavItem> navItemList;
    TextView tv;
    ImageView iv;

    public NavListAdapter(Context context, List<NavItem> navItemList) {
        this.context = context;
        this.navItemList = navItemList;
    }

    @Override
    public int getCount() {
        return navItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NavItem navItem;
        LayoutInflater inf=((Activity)context).getLayoutInflater();
        if(convertView==null)
        {
            navItem=new NavItem();
            convertView=inf.inflate(R.layout.item_nav_list,null);
            tv=(TextView)convertView.findViewById(R.id.title);
            iv=(ImageView)convertView.findViewById(R.id.nav_icon);
            convertView.setTag(navItem);
        }else
        {
            navItem=(NavItem)convertView.getTag();
        }
        navItem=navItemList.get(position);
        tv.setText(navItem.getTitle());
        iv.setImageResource(navItem.getResIcon());

        return convertView;
    }
}
