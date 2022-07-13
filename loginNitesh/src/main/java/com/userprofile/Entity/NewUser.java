package com.userprofile.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="NewUser")
@SQLDelete(sql = "UPDATE new_user SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class NewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    public NewUser() {
        super();
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(unique = true)
    String email;

    public NewUser(int id, String name, String email, String gender, String profile_photo, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.profile_photo = profile_photo;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public  String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public String getPassword() {
        return password;
    }

    String gender;
    String profile_photo;

    public int getId() {
        return id;
    }

    String password;
    
    public boolean deleted =Boolean.FALSE;
    
    
}
