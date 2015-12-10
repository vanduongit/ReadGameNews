package com.android.dt.readgamenews.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dt.readgamenews.R;
import com.android.dt.readgamenews.fedorvlasov.lazylist.ImageLoader;
import com.android.dt.readgamenews.models.Item;

import java.util.ArrayList;

/**
 * Created by toan on 12/8/2015.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> items;
    //Bitmap bitmap;
    public ImageLoader imageLoader;
    private static LayoutInflater inflater = null;
    String urlstring = "";
    String chuoihinh = "";
    view_mot_o mot_o;
    String hinhtam = "http://st.f1.vnecdn.net/responsive/i/v20/logos/vne_logo_rss.png";

    public MyAdapter(Context context, ArrayList<Item> items) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.items = items;
        imageLoader = new ImageLoader(this.context.getApplicationContext());
        // this.urlhinh = urlhinh;
    }

    public class view_mot_o {
        TextView title, pubdate, tv3;
        ImageView iv;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        //LayoutInflater inf = ((Activity) context).getLayoutInflater();
        if (convertView == null) {

            mot_o = new view_mot_o();

            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mottin_layout, null);
            mot_o.title = (TextView) convertView.findViewById(R.id.title);
            // mot_o.tv3=(TextView)convertView.findViewById(R.id.textView1);
            // mot_o.pubdate=(TextView)convertView.findViewById(R.id.pubdate);
            mot_o.iv = (ImageView) convertView.findViewById(R.id.imageView1);
            convertView.setTag(mot_o);

        } else {
            mot_o = (view_mot_o) convertView.getTag();
        }

        String chuoi = items.get(position).getDescription().toString();
        int vitri = chuoi.indexOf("r>");
        String description = chuoi.substring(vitri + 2);
        int batdau = chuoi.indexOf("src");
        int ketthuc = chuoi.indexOf("</a>");
        if((ketthuc-batdau)>0){
            chuoihinh = chuoi.substring((batdau + 5), (ketthuc - 3));
       }else
        {
            chuoihinh=hinhtam;
        }
        imageLoader.DisplayImage(chuoihinh, mot_o.iv);

        Log.d("hinhanh1", chuoihinh);

        mot_o.title.setText(items.get(position).getTitle().toString());


        return convertView;
    }
}
