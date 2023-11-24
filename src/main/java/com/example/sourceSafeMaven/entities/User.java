package com.example.sourceSafeMaven.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
//    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToMany()
    @JoinTable(
            name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups;
    @OneToOne(mappedBy = "user")
    private Version version;

    public Set<Group> getGroups() {
        return groups;
    }

    private String email;

    public void setVersion(Version version) {
        this.version = version;
    }

    private String password;

    public Version getVersion() {
        return version;
    }
}
