package com.bsdenterprise.qbits.policeactivity.dto.activity;

import com.bsdenterprise.qbits.policeactivity.dto.environment.EnvironmentOutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivityDTO {

    private String activityId;
    private EnvironmentOutDTO environment = new EnvironmentOutDTO();

    public ActivityDTO(String activityId, Long environmentId, String environmentName) {
        this.activityId = activityId;
        this.environment.setId(environmentId);
        this.environment.setName(environmentName);
    }
}
