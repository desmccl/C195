package com.example.test2.model;
/**This is the Users model class, it contains variables, setters and getters, a constructor and a toString method*/

public class Users {
    /**These are the variables, they are for user id, username and password*/
    private String userId;
    private String userName;
    private String password;
    /**This is the constructor*/

    public Users(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
/**This gets the user id*/
    public String getUserId() {
        return userId;
    }
    /**This sets the user id*/
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**This gets the username*/
    public String getUserName() {
        return userName;
    }
    /**This sets the username*/
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**This gets the password*/
    public String getPassword() {
        return password;
    }
    /**This sets the password*/
    public void setPassword(String password) {
        this.password = password;
    }
    /**This returns user id as a string*/
    @Override
    public String toString() {
        return userId;
    }
}
