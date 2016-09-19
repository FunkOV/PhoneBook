package ru.levelp;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


/**
 * Created by Olga on 09.09.2016.
 */

public class Contact implements Serializable {
    static final long serialVersionUID = 12L;

    @Expose
    private String name;
    @Expose
    private String phone;
    @Expose
    private String email;

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    public void print() {
        System.out.println(name + " " + phone + " " + email);
    }

}