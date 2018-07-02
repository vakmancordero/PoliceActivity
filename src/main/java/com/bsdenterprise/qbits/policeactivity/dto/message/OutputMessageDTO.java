package com.bsdenterprise.qbits.policeactivity.dto.message;

import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputMessageDTO {

    private Long id;
    private String message;
    private ModuleOutDTO module;
    private Integer statusId;
    private String status;

    private Timestamp createdAt;

}
