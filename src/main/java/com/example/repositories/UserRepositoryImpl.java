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
public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(User user, String roleName) {
        Role role = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", roleName)
                .getSingleResult();
        user.setRoles(new HashSet<>() {{
            add(role);
        }});
        entityManager.merge(user);
    }

    public List<User> findAll() {
        List<User> users = entityManager
                .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles r", User.class)
                .getResultList();
        return users;
    }

}
