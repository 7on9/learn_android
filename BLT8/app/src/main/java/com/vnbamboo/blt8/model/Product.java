package com.vnbamboo.blt8.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private String img;
    private Bitmap bitmap;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice( int price ) {
        this.price = price;
    }

    public Product( String name, int price, Bitmap bitmap ) {
        this.name = name;
        this.price = price;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap( Bitmap bitmap ) {
        this.bitmap = bitmap;
    }

    public String getImg() {
        return img;
    }

    public void setImg( String img ) {
        this.img = img;
    }

    public Product( String name, int price, String img ) {
        this.name = name;
        this.price = price;
        this.img = img;
    }
}
