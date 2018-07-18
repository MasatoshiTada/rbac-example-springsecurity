package com.example.services;

import com.example.models.User;

import java.util.List;

/**
 * @author tada
 */
public interface UserService {

    public void register(User user, String roleName);

    public List<User> findAll();

}
