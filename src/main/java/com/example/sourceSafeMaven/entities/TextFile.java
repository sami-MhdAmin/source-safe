package com.example.sourceSafeMaven.entities;


import com.example.sourceSafeMaven.entities.enums.ReservationStatus;
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
@Table(name = "text_file")
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

    @OneToMany(mappedBy = "textFile")
    @JsonIgnore
    private List<Version> versions;

    @OneToMany(mappedBy = "textFile")
    @JsonIgnore
    private List<ReservationHistory> reservationHistories;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.FREE;


}

