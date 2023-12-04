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


    @ManyToOne
    @JoinColumn(name = "text_file_id")
    private TextFile textFile;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


/*
    Certainly! In JPA (Java Persistence API),
     the @Lob (Large Object) annotation is used to indicate that the annotated field should be treated as a large object in the database.
      The @Column annotation is used to specify details about the column in the database table.
 */
    @Column(name = "file_content", columnDefinition = "BLOB")
    private byte[] fileContent;

}
