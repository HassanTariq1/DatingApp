package com.itpvt.datingapp;

/**
 * Created by Hassan on 9/12/2018.
 */

public class MatchesObject {

    private String name;
    private String profileImageUrl;
    private String userId;
    public MatchesObject(String userId, String name, String profileImageUrl){
        this.userId = userId;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserId(){
        return userId;
    }
    public void setUserID(String userID){
        this.userId = userId;
    }

}
