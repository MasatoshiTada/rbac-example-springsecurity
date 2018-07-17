package com.example.controllers;

import com.example.models.Issue;
import com.example.security.UserPrincipal;
import com.example.services.IssueService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author kawasima
 * @author tada
 */
@Controller
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/issues/")
    public String list(Model model) {
        List<Issue> issues = issueService.findAll();
        model.addAttribute("issues", issues);
        return "issue/list";
    }

    @GetMapping("/issue/new")
    public String newIssue() {
        return "issue/newIssue";
    }

    @PostMapping("/issues/")
    public String create(Issue issue, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        issueService.register(issue, userPrincipal.getUsername());
        return "redirect:/issues/";
    }
}
