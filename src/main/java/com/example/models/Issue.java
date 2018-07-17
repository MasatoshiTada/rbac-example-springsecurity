package com.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author kawasima
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Issue {
    @Id
    @GeneratedValue
    private Long issueId;

    @NonNull
    private String subject;

    @NonNull
    private String description;

    @ManyToOne
    private User postedBy;
}
