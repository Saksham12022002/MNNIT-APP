package com.example.mnnitcentral;

public class uploadimage{

    private String name;
    private String desc;
    private String key;
    private  String imageurl;

    public uploadimage() {
    }

    public uploadimage(String name,String desc,String imageurl){

        this.name = name;
        this.desc=desc;
        this.imageurl=imageurl;



    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}