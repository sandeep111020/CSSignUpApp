package com.example.cssignupapp;


public class profileinfo {

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String userName;
    public String userNumber,userEmail,userid,password;





    public String imageURL;

    public profileinfo() {
    }

    public profileinfo(String userName, String userNumber, String userEmail, String userid, String password, String url) {
        this.userName=userName;
        this.userNumber=userNumber;
        this.userEmail=userEmail;
        this.userid=userid;
        this.password=password;
        this.imageURL=url;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }



}