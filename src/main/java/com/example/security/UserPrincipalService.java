package com.example.security;

import com.example.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author kawasima
 * @author tada
 */
@Service
public class UserPrincipalService implements UserDetailsService {

    @PersistenceContext
    EntityManager em;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        try {
            User user = em
                    .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions p WHERE u.account = :account", User.class)
                    .setParameter("account", account)
                    .getSingleResult();
            return new UserPrincipal(user);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("not found");
        }
    }
}
