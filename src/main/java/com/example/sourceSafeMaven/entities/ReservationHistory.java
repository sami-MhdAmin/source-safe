package com.example.sourceSafeMaven.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
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
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private CheckOutStatus CheckOutStatus;

    private LocalDateTime timestamp;

    @PrePersist
    private void setTimestamp() {
        this.timestamp = LocalDateTime.now();
        this.expirationTime=LocalDateTime.now().plusSeconds(2L);
    }

    private LocalDateTime expirationTime;

    private LocalDateTime checkOutEndTime;

//    public boolean isExpired() {
//        return expirationTime != null && LocalDateTime.now().isAfter(expirationTime);
//    }

//    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    private ScheduledFuture<?> timerTask;

//    public void calculateExpirationTime() {
//        if (timestamp != null && CheckOutStatus == null) {
//            DateTime expirationTime = new DateTime(timestamp).plusSeconds(5).toDateTime(DateTimeZone.getDefault());
//            long delay = expirationTime.getMillis() - System.currentTimeMillis();
//
//            if (timerTask != null) {
//                timerTask.cancel(false);
//            }
//
//            timerTask = scheduler.schedule(() -> {
//                synchronized (this) {
//                    if (CheckOutStatus == null) { // Check if the status is still null
//                        setCheckOutStatus(CheckOutStatus.TIMER_END);
//                        // Save the updated ReservationHistory in the database or perform any other necessary actions
//                        setCheckOutEndTime(LocalDateTime.now());
//                    }
//                }
//            }, Duration.millis(delay).getMillis());
//        }
//    }
}