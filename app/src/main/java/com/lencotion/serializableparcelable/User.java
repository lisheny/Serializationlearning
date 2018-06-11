package com.lencotion.serializableparcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
// java.io.InvalidClassException
//    private String testError;

    private int userId;

    private String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
