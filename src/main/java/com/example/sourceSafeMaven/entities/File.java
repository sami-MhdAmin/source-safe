package com.example.sourceSafeMaven.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fil")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "file_sequence", sequenceName = "file_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_sequence")
//    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Version> versions;


//    public List<Version> getVersions() {
//        return versions;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//    public void setGroup(Group group) {
//        this.group = group;
//    }
//
//    public Group getGroup() {
//        return group;
//    }
//    public void setVersions(List<Version> versions) {
//        this.versions = versions;
//    }

}

