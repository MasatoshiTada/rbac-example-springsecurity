package com.example.services;

import com.example.models.Issue;
import com.example.repositories.IssueRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kawasima
 * @author tada
 */
@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @PreAuthorize("hasAuthority('readIssue')")
    @Transactional(readOnly = true)
    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    @PreAuthorize("hasAuthority('writeIssue')")
    @Transactional
    public void register(Issue issue, String account) {
        issueRepository.register(issue, account);
    }
}
