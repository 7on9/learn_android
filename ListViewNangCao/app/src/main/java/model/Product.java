package model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private String img;

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
