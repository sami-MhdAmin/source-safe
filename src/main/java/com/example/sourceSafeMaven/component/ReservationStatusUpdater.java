package com.example.sourceSafeMaven.component;

import com.example.sourceSafeMaven.entities.ReservationHistory;
import com.example.sourceSafeMaven.entities.TextFile;
import com.example.sourceSafeMaven.entities.enums.CheckOutStatus;
import com.example.sourceSafeMaven.entities.enums.ReservationStatus;
import com.example.sourceSafeMaven.repository.ReservationHistoryRepository;
import com.example.sourceSafeMaven.repository.TextFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReservationStatusUpdater {

    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;
    @Autowired
    private TextFileRepository textFileRepository;

    @Scheduled(fixedDelay = 1000) // Runs every second, you can adjust the value according to your needs
    public void updateCheckOutStatus() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<ReservationHistory> expiredReservations = reservationHistoryRepository.findByExpirationTimeBeforeAndCheckOutStatusIsNull(
                currentTime);


        for (ReservationHistory reservation : expiredReservations) {
            reservation.setCheckOutStatus(CheckOutStatus.TIMER_END);
            reservation.setCheckOutEndTime(currentTime);
            reservationHistoryRepository.save(reservation);
            TextFile textFile= reservation.getTextFile();
            textFile.setReservationStatus(ReservationStatus.FREE);
            textFileRepository.save(textFile);
        }
    }
}
