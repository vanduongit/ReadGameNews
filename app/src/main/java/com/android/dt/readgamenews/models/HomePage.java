package com.android.dt.readgamenews.models;

/**
 * Created by toan on 12/14/2015.
 */
public class HomePage {
    String id;
    String name;
    String rss;

    public HomePage() {
    }

    public HomePage(String id, String name, String rss) {
        this.id = id;
        this.name = name;
        this.rss = rss;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }
}
