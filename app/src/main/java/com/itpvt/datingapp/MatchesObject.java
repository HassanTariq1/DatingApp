package com.itpvt.datingapp;

/**
 * Created by Hassan on 9/12/2018.
 */

public class MatchesObject {

    private String userId;


    public MatchesObject(String userId){
        this.userId = userId;


    }

    public String getUserId(){
        return userId;
    }
    public void setUserID(String userID){
        this.userId = userId;
    }

}
