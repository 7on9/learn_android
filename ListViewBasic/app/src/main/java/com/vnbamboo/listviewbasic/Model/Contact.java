package com.vnbamboo.listviewbasic.Model;

public class Contact {
    private String code;
    private String name;
    private String phone;

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public Contact( String code, String name, String phone ) {
        this.code = code;
        this.name = name;
        this.phone = phone;
    }
}
