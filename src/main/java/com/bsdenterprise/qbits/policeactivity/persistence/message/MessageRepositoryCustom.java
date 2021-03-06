package com.bsdenterprise.qbits.policeactivity.persistence.message;

import com.bsdenterprise.qbits.policeactivity.dto.activity.ActivityDTO;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;

import java.util.List;
import java.util.Optional;

public interface MessageRepositoryCustom {

    List<ActivityDTO> findActivities();
    List<ActivityDTO> findActivitiesByActivityIdContaining(String activityId);
    Optional<ActivityDTO> findActivityById(String activityId);

    List<MessageEntity> findMessageDetail(String activityId);

}
