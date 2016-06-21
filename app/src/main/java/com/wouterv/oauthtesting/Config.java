package com.wouterv.oauthtesting;

/**
 * Created by wouter on 2-6-2016.
 */
public class Config {

    static Config instance;
//    private List<Course> courses;
    private String access_token;

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }


}
