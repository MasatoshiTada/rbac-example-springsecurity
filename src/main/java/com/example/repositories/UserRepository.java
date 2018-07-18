package com.example.repositories;

import com.example.models.User;

import java.util.List;

/**
 * @author tada
 */
public interface UserRepository {

    public void register(User user, String roleName);

    public List<User> findAll();

}
