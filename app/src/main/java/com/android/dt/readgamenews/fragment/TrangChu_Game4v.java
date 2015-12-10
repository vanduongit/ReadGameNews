package com.android.dt.readgamenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.dt.readgamenews.R;
import com.android.dt.readgamenews.asytask.TrangChuGame4vAsyTask;


public class TrangChu_Game4v extends Fragment {
    ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.trangchu_game4v,container,false);
        lv=(ListView)v.findViewById(R.id.listView);
        new TrangChuGame4vAsyTask(getContext(),lv).execute();
        return v;
    }
}
