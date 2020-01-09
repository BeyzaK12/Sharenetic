package com.example.sharenetic.Models;

import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private String sClass;
    private String post;
    private String user_name;
    private String user_id;
    private Object timeStamp;

    private String postKey;

    public Post(String sClass, String post, String user_name, String user_id) {
        this.sClass = sClass;
        this.post = post;
        this.user_name = user_name;
        this.user_id = user_id;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public Post() {}

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Object getTimeStamp() {

        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String convertTimeStamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
        String dateStr = sdf.format(timeStamp);
        return dateStr;
    }
}
