package com.bsdenterprise.qbits.policeactivity.persistence.message;

import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;

import java.util.List;

public interface MessageRepositoryCustom {

    List<String> findActivities();

    List<MessageEntity> findMessageDetail(String activityId);

}
