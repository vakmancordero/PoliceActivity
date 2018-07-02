package com.bsdenterprise.qbits.policeactivity.dto.activity;

import com.bsdenterprise.qbits.policeactivity.dto.message.OutputMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActivityMessages {

    private ActivityDTO activity;
    private List<OutputMessageDTO> messages;

}
