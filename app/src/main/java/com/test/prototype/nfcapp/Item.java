package com.test.prototype.nfcapp;

import android.graphics.Bitmap;

/**
 * Created by Tom on 20/03/2015.
 */
public class Item {

    String id;
    String name;
    String description;
    Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
