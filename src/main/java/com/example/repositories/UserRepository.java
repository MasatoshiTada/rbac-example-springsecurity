package com.example.repositories;

import com.example.models.Role;
import com.example.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;

/**
 * @author kawasima
 * @author tada
 */
@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public void register(User user, String roleName) {
        Role role = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", roleName)
                .getSingleResult();
        user.setRoles(new HashSet<Role>(){{
            add(role);
        }});
        em.merge(user);
    }

    public List<User> findAll() {
        List<User> users = em
                .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles r", User.class)
                .getResultList();
        return users;
    }

}
