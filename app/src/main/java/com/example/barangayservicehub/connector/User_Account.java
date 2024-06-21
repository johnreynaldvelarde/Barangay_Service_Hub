package com.example.barangayservicehub.connector;

public class User_Account {
    private String name, email, password;
    private  int accountType, isOnline;

    public User_Account(String name, String email, String password, int accountType, int isOnline) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.isOnline = isOnline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }
}