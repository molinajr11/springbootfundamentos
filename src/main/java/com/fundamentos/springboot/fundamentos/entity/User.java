package com.fundamentos.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
@Id
@GeneratedValue
@Column(name = "id_user",nullable = false,unique = true)
private Long id;

@Column(length =50 )
private String name;
@Column(length = 50)
private String email;
private LocalDate brithDate;
@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JsonManagedReference
private List<Post> post= new ArrayList<>();

public User(){

}

public User (String name, String email,LocalDate brithDate){
    this.name=name;
    this.email=email;
    this.brithDate=brithDate;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(LocalDate brithDate) {
        this.brithDate = brithDate;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", brithDate=" + brithDate +
                ", post=" + post +
                '}';
    }
}
