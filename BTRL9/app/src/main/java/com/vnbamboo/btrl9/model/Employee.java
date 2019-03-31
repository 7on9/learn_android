package com.vnbamboo.btrl9.model;

public class Employee {
    private String code;
    private String name;
    private Boolean isMan;
    public Boolean isChecked;

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

    public Boolean isMan() {
        return isMan;
    }

    public void setMan( Boolean man ) {
        isMan = man;
    }

    public Employee( String code, String name, Boolean isMan ) {
        this.code = code;
        this.name = name;
        this.isMan = isMan;
        this.isChecked = false;
    }
}
