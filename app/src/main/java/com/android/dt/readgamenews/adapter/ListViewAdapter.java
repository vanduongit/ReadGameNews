package com.android.dt.readgamenews.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.dt.readgamenews.R;
import com.android.dt.readgamenews.models.Item;

import java.util.List;

/**
 * Created by toan on 12/23/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    Context context;
    List<Item> items;

    public ListViewAdapter(Context context,List<Item> items) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.items=items;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    class view_mot_o
    {
        TextView tv;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        view_mot_o mot_o;
        if(convertView==null)
        {
            mot_o=new view_mot_o();
            LayoutInflater inf=((Activity)context).getLayoutInflater();
            convertView=inf.inflate(R.layout.list_itemviewpager, null);
            mot_o.tv=(TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(mot_o);

        }else
        {
            mot_o=(view_mot_o)convertView.getTag();
        }

        mot_o.tv.setText(items.get(position).getTitle().toString());
        return convertView;
    }
}
