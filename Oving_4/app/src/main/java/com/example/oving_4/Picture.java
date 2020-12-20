package com.example.oving_4;

import android.graphics.drawable.Drawable;

public class Picture {
    public String title;
    public Drawable resource;
    public String info;

    public Picture(String title, Drawable resource, String info){
        this.title = title;
        this.resource = resource;
        this.info = info;

    }

    public String getTitle() {
        return title;
    }

    public Drawable getResource() {
        return resource;
    }

    public String getInfo() {
        return info;
    }
}
