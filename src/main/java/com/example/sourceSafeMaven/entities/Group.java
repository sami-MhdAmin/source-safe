package com.example.sourceSafeMaven.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "group_fixed")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "group_sequence", sequenceName = "group_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_sequence")
//    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();
}