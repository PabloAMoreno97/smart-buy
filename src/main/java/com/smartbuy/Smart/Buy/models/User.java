package com.smartbuy.Smart.Buy.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="user")
@ToString @EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name="_id")
    private Long id;
    @Getter @Setter @Column(name="first_name")
    private String firstName;
    @Getter @Setter @Column(name="username")
    private String username;
    @Getter @Setter @Column(name="last_name")
    private String lastName;
    @Getter @Setter @Column(name="email")
    private String email;
    @Getter @Setter @Column(name="phone")
    private String phone;
    @Getter @Setter @Column(name="password")
    private String password;
    @Getter @Setter @Column(name="admin")
    private boolean admin;
}
