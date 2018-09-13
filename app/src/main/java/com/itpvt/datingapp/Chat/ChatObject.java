package com.itpvt.datingapp.Chat;

/**
 * Created by Hassan on 9/12/2018.
 */

public class ChatObject {
private String message;
private  Boolean currentuser;


    public ChatObject(String message, Boolean currentuser) {
        this.message = message;
        this.currentuser = currentuser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(Boolean currentuser) {
        this.currentuser = currentuser;
    }
}
