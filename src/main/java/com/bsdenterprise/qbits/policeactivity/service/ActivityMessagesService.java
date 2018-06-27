package com.bsdenterprise.qbits.policeactivity.service;

import com.bsdenterprise.qbits.policeactivity.common.service.BaseService;
import com.bsdenterprise.qbits.policeactivity.dto.ActivityDTO;
import com.bsdenterprise.qbits.policeactivity.dto.ActivityMessages;
import com.bsdenterprise.qbits.policeactivity.dto.OutputMessage;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;
import com.bsdenterprise.qbits.policeactivity.persistence.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityMessagesService extends BaseService<MessageRepository, MessageEntity> {

    public List<ActivityMessages> allActivityMessages() {

        List<ActivityMessages> activityMessages = new ArrayList<>();

        repository.findActivities().forEach(activity -> activityMessages.add(
                new ActivityMessages(activity, convertUtils.convert(
                        repository.findMessageDetail(activity.getActivityId()), OutputMessage.class)
                )));

        return activityMessages;
    }

    public List<ActivityMessages> findActivityMessagesByActivityId(String activityId) {

        List<ActivityMessages> activityMessages = new ArrayList<>();

        repository.findActivityById(activityId).ifPresent(activity ->
                activityMessages.add(new ActivityMessages(activity, convertUtils.convert(
                        repository.findMessageDetail(activityId), OutputMessage.class)
                ))
        );

        return activityMessages;
    }

    public List<ActivityMessages> findActivityMessages(String activityId, String status, String environment) {

        Stream<ActivityMessages> stream = this.allActivityMessages().stream();

        if (!StringUtils.isEmpty(activityId))
            stream = stream.filter(am -> am.getActivity().getActivityId().equals(activityId));
        if (!StringUtils.isEmpty(environment))
            stream = stream.filter(am -> am.getActivity().getEnvironment().equals(environment));
        if (!StringUtils.isEmpty(status))
            stream = stream.filter(am -> am.getMessages().removeIf(m -> !m.getStatus().equals(status)));

        List<ActivityMessages> collect = stream.collect(Collectors.toList());

        return collect;
    }

}
