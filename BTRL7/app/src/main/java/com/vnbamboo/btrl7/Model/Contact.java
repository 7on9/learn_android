package com.vnbamboo.btrl7.Model;

public class Contact {
    private String code;
    private String name;
    private String bank;
    private String phone;

    public String getInfo(){
        return code + "-" + bank + "-" + name;
    }
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

    public String getBank() {
        return bank;
    }

    public void setBank( String bank ) {
        this.bank = bank;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public Contact( String code, String name, String bank, String phone ) {
        this.code = code;
        this.name = name;
        this.bank = bank;
        this.phone = phone;
    }
}
