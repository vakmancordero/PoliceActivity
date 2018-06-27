package com.bsdenterprise.qbits.policeactivity.service;

import com.bsdenterprise.qbits.policeactivity.common.service.BaseService;
import com.bsdenterprise.qbits.policeactivity.dto.ActivityMessages;
import com.bsdenterprise.qbits.policeactivity.dto.OutputMessage;
import com.bsdenterprise.qbits.policeactivity.persistence.message.MessageRepository;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageCatcherService extends BaseService<MessageRepository, MessageEntity> {

    public List<ActivityMessages> allActivityMessages() {

        List<ActivityMessages> activityMessages = new ArrayList<>();

        repository.findActivities().forEach(activity -> activityMessages.add(
                new ActivityMessages(activity, convertUtils.convert(
                        repository.findMessageDetail(activity), OutputMessage.class)
                )));

        return activityMessages;
    }

}
