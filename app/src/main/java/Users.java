package com.example.najss.javatp;

public class Users {
    private String id;
    private String first_name;
    private String last_name;
    private String gmail;
    private String password;
    public Users(){

    }

    public Users(String id,String first_name, String last_name, String gmail, String password) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.gmail = gmail;
        this.password = password;
        this.id=id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGmail() {
        return gmail;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}

