package com.example.simas.fourthexercise.Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by simas on 8/15/2016.
 */
public class Nature {

    private String photo_url;

   String photo_name;

    public Nature () {

    }
    public Nature (String photoUrl, String photoName) {
        this.photo_url = photoUrl;
        this.photo_name = photoName;
    }

    public String getPhotoUrl() {
        return this.photo_url;
    }

    public void setPhotoUrl(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getPhotoName() {
        return this.photo_name;
    }

    public void setPhotoName(String photo_name) {
        this.photo_name = photo_name;
    }

}
