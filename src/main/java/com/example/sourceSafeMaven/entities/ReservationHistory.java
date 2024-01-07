package com.example.sourceSafeMaven.entities;

import com.example.sourceSafeMaven.entities.enums.CheckOutStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.joda.time.Duration;
//import org.joda.time.DurationFieldType;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation_history")
public class ReservationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "text_file_id")
    private TextFile textFile;

    @ManyToOne
    @JoinColumn(name = "reserved_user_id")
//    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private CheckOutStatus checkOutStatus;

    private LocalDateTime timestamp;

    @PrePersist
    private void setTimestamp() {
        this.timestamp = LocalDateTime.now();
        this.expirationTime = LocalDateTime.now().plusMinutes(1L);
    }

    private LocalDateTime expirationTime;

    private LocalDateTime checkOutEndTime;

}