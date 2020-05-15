package com.example.mnnitcentral;

public class userslist {

 String username;
 String fullname;
 String email;
 String number;
 String passwd;

    public userslist() {
    }

    public userslist(String username, String fullname, String email, String number, String passwd) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.number = number;
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
