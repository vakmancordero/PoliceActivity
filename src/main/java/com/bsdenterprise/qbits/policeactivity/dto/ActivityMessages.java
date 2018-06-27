package com.bsdenterprise.qbits.policeactivity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActivityMessages {

    private ActivityDTO activity;
    private List<OutputMessage> messages;

}
