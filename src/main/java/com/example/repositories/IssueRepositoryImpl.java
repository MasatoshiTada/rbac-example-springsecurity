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
public class IssueRepositoryImpl implements IssueRepository {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Issue> findAll() {
        List<Issue> issues = entityManager
                .createQuery("SELECT i FROM Issue i", Issue.class)
                .getResultList();
        return issues;
    }

    public void register(Issue issue, String account) {
        User posterBy = entityManager.createQuery("SELECT u FROM User u WHERE u.account = :account", User.class)
                .setParameter("account", account)
                .getSingleResult();
        issue.setPostedBy(posterBy);
        entityManager.merge(issue);
    }
}
