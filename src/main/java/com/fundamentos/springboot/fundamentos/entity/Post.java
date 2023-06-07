package com.fundamentos.springboot.fundamentos.entity;

import jakarta.persistence.*;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_post",nullable = false,unique = true)
    private Long Id;
    @Column(name = "description",length = 255)
    private String description;

    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(Long id, String description, User user) {
        Id = id;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Id=" + Id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
