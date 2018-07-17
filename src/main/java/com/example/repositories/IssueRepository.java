package com.example.repositories;

import com.example.models.Issue;
import com.example.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author kawasima
 * @author tada
 */
@Repository
public class IssueRepository {
    @PersistenceContext
    EntityManager em;

    public List<Issue> findAll() {
        List<Issue> issues = em
                .createQuery("SELECT i FROM Issue i", Issue.class)
                .getResultList();
        return issues;
    }

    public void register(Issue issue, String account) {
        User posterBy = em.createQuery("SELECT u FROM User u WHERE u.account = :account", User.class)
                .setParameter("account", account)
                .getSingleResult();
        issue.setPostedBy(posterBy);
        em.merge(issue);
    }
}
