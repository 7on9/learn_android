package com.vnbamboo.model;

public class MyFile {
    private String name;
    private String path;
    private String decription;
    private FileSupport fileType;
    private Boolean hasChild;

    public MyFile( String name, String path, String decription, FileSupport fileType, Boolean hasChild ) {
        this.name = name;
        this.path = path;
        this.decription = decription;
        this.fileType = fileType;
        this.hasChild = hasChild;
    }

    public MyFile() {

    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath( String path ) {
        this.path = path;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription( String decription ) {
        this.decription = decription;
    }

    public FileSupport getFileType() {
        return fileType;
    }

    public void setFileType( FileSupport fileType ) {
        this.fileType = fileType;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild( Boolean hasChild ) {
        this.hasChild = hasChild;
    }
}
