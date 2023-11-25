package com.example.sourceSafeMaven.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


//    public Group() {}
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<File> getFiles() {
//        return files;
//    }
//    public void setFiles(List<File> files) {
//        this.files = files;
//    }
//    public void addFile(File file) {
//        files.add(file);
//        file.setGroup(this);
//    }
//
//    public void removeFile(File file) {
//        files.remove(file);
//        file.setGroup(null);
//    }

}