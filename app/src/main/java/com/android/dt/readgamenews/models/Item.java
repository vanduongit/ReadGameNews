package com.android.dt.readgamenews.models;

import java.io.Serializable;

/**
 * Created by toan on 12/8/2015.
 */
public class Item implements Serializable{
    private String title;
    private String description;
    private String link;
    private String pubdate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}
