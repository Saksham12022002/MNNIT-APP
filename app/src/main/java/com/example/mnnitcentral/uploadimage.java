package com.example.mnnitcentral;

public class uploadimage{

    private String name;
    private String desc;
    private String key;
    private String _name;
    private String _phone;
    private  String imageurl;

    public uploadimage() {
    }

    public uploadimage(String name,String desc,String imageurl,String _name,String _phone){

        this.name = name;
        this.desc=desc;
        this.imageurl=imageurl;
        this._name=_name;
        this._phone=_phone;



    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
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