package com.example.sourceSafeMaven.dto.file;

import com.example.sourceSafeMaven.entities.enums.ReservationStatus;
import com.example.sourceSafeMaven.entities.TextFile;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileResponse {
    private Long id;

    private String name;

    private ReservationStatus reservationStatus;

    private Long userId; // TODO: this should be user object

    public FileResponse(TextFile textFile) {
        this.id = textFile.getId();
        this.name = textFile.getFileName();
        this.reservationStatus = textFile.getReservationStatus();
//        this.userId = textFile.getReservedByUserId();
    }
}
