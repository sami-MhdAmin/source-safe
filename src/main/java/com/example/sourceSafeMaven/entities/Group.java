package com.example.sourceSafeMaven.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "group_fixed")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileVersion> files = new ArrayList<>();

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();
}