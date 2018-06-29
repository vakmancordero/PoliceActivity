package com.bsdenterprise.qbits.policeactivity.service;

import com.bsdenterprise.qbits.policeactivity.dto.status.StatusDTO;
import com.bsdenterprise.qbits.policeactivity.enums.Status;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    public List<StatusDTO> findAll() {
        return Arrays.stream(Status.values())
                .map(status -> new StatusDTO(status.getId(), status.getName()))
                .collect(Collectors.toList());
    }

    public List<StatusDTO> findByQueryContaining(String query) {
        return Status.findStatusByQueryContains(query).stream()
                .map(status -> new StatusDTO(status.getId(), status.getName()))
                .collect(Collectors.toList());
    }

}
