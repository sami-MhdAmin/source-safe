package com.example.sourceSafeMaven;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "`file`")
public class File {
    @Id
    @SequenceGenerator(name = "file_sequence", sequenceName = "file_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "file")
    private List<Version> versions;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}

