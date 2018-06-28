package com.bsdenterprise.qbits.policeactivity.dto.message;

import lombok.Data;

@Data
public class InputMessageDTO {

    private String activityId;

    private String message;
    private Long moduleId;
    private Integer statusId;
    private Long environmentId;

}
