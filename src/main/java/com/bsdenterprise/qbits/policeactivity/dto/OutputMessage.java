package com.bsdenterprise.qbits.policeactivity.dto;

import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputMessage {

    private Long id;
    private String message;
    private ModuleOutDTO module;
    private String status;

    private Timestamp createdAt;

}
