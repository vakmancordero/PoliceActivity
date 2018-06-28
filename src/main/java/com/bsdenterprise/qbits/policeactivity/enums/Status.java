package com.bsdenterprise.qbits.policeactivity.enums;

import java.util.Optional;

public enum Status {

    SUCCESS(1), ERROR(2);

    private Integer id;

    Status(Integer id) { this.id = id;}

    public static Optional<Status> findStatusById(Integer statusId) {
        for (Status status : values())
            if (status.getId().equals(statusId)) return Optional.of(status);
        return Optional.empty();
    }

    public Integer getId() {
        return id;
    }
}
