package com.sh.zsh.code;

import java.util.List;

/**
 * Created by zhush on 2017/1/11.
 * E-mail zhush@jerei.com
 * PS
 */
public class People {
    String firstName;
    String lastName;
    String email;
    List<Look> look;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Look> getLook() {
        return look;
    }

    public void setLook(List<Look> look) {
        this.look = look;
    }

    @Override
    public String toString() {
        return "People{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", look=" + look +
                '}';
    }
}
