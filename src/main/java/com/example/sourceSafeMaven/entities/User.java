package com.example.sourceSafeMaven.entities;

import jakarta.persistence.*;

import java.util.Set;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    public Set<Group> getGroups() {
//        return groups;
//    }

    private String email;

//    public void setVersion(Version version) {
//        this.version = version;
//    }

    private String password;

//    public Version getVersion() {
//        return version;
//    }
}
