package com.example.schedulemanagementdevelop.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table (name = "user")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String email;


    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void updateUsername (String username) {
        this.username = username;
    }

    public void updateEmail (String email) {
        this.email = email;
    }
}
