package com.example.todosapp.Models;

public class User {
    private String Id;
    private String UserName;
    private String Email;
    private String Password;

    public User(String Id, String UserName, String Email, String Password){
        this.Id = Id;
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
    }
    public String getId() {
        return Id;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public String getUserName(){
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public void setPassword(String password) {
        Password = password;
    }
}

