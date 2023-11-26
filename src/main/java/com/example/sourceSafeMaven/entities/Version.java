package com.example.sourceSafeMaven.entities;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

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
    private FileVersion file;

    @Column(name = "file_name")
    private String fileName;


/*
    Certainly! In JPA (Java Persistence API),
     the @Lob (Large Object) annotation is used to indicate that the annotated field should be treated as a large object in the database.
      The @Column annotation is used to specify details about the column in the database table.
 */
    @Lob
    @Column(name = "file_content", columnDefinition = "BLOB") //BLOB Binary Large Object
    private byte[] fileContent;

}
