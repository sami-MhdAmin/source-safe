package com.example.sourceSafeMaven.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "file_version")
public class TextFile { //change name just to avoid the conflict with File on java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group group;

    @OneToMany(mappedBy = "file")
    @JsonIgnore
    private List<Version> versions;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.FREE;

    private Long reservedByUserId;

}

