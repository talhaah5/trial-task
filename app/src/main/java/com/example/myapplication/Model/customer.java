package com.example.myapplication.Model;

public class customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Object password;


    public String getfirstName() {
        return firstName;
    }
    public void setfirstName(String name) {
        this.firstName = name;
    }

    public String getlastName() {
        return lastName;
    }
    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPassword() {
        return password;
    }
    public void setPassword(Object password) {
        this.password = password;
    }

    public String getphone() {
        return phone;
    }
    public void setphone(String phone) {
        this.phone = phone;
    }


}
