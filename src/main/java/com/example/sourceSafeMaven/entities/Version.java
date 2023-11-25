package com.example.sourceSafeMaven.entities;

import com.example.sourceSafeMaven.entities.User;
import jakarta.persistence.*;


@Entity
@Table(name = "version")
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "file_name")
    private String fileName;

//    @Lob
//    @Column(name = "file_content", columnDefinition = "BLOB")
//    private byte[] fileContent;


}
