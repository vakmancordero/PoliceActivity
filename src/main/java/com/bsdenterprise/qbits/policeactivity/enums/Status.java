package com.bsdenterprise.qbits.policeactivity.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Status {

    SUCCESS(1), ERROR(2);

    private Integer id;

    Status(Integer id) { this.id = id;}

    public static Optional<Status> findStatusById(Integer statusId) {
        return Arrays.stream(values()).filter(st -> st.getId().equals(statusId)).findFirst();
    }

    public static List<Status> findStatusByQueryContains(String query) {
        return Arrays.stream(values())
                .filter(status -> StringUtils.isNumeric(query) ?
                            status.getId().equals(Integer.valueOf(query)) :
                            status.name().toLowerCase().contains(query.toLowerCase())
                ).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return this.name();
    }

}
