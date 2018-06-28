package com.bsdenterprise.qbits.policeactivity.dto.message;

import com.bsdenterprise.qbits.policeactivity.dto.environment.EnvironmentOutDTO;
import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import lombok.Data;

@Data
public class InputMessage {

    private String activityId;

    private String message;
    private ModuleOutDTO module;
    private Integer statusId;
    private EnvironmentOutDTO environment;

}
