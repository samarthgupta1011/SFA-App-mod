package com.samarthgupta.sfa_app.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by samarthgupta on 03/10/17.
 */


//USE
public class Employee {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("dept")
    @Expose
    private String dept;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("status")
    @Expose
    private boolean status;

    public Employee(String name, String mobile, String email, String pass, String dept) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.pass = pass;
        this.dept = dept;
    }

    public Employee(String mobile, String pass) {
        this.mobile = mobile;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public boolean isStatus() {
        return status;
    }
}
