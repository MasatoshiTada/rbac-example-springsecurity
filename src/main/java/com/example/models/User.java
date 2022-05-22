package com.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author kawasima
 * @author tada
 */
@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    @NonNull
    private String account;

    @NonNull
    private String lastName;

    @NonNull
    private String firstName;

    @NonNull
    private String password; // added for Spring Security

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    private Set<Role> roles;
}
