package com.bsdenterprise.qbits.policeactivity.dto;

import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import lombok.Data;

@Data
public class InputMessage {

    private String activityId;

    private String message;
    private Long moduleId;
    private ModuleOutDTO module;
    private String status;
    private String environment;

}
