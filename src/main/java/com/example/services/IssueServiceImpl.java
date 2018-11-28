package com.example.services;

import com.example.models.Issue;
import com.example.repositories.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kawasima
 * @author tada
 */
@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

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
