package com.bsdenterprise.qbits.policeactivity.dto;

import lombok.Data;

@Data
public class InputMessage {

    private String activityId;

    private String message;
    private Long moduleId;
    private String status;
    //private String environment;

}
