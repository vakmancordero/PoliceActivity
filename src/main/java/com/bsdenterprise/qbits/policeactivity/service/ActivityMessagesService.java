package com.bsdenterprise.qbits.policeactivity.service;

import com.bsdenterprise.qbits.policeactivity.common.service.BaseService;
import com.bsdenterprise.qbits.policeactivity.dto.activity.ActivityMessages;
import com.bsdenterprise.qbits.policeactivity.dto.message.OutputMessage;
import com.bsdenterprise.qbits.policeactivity.enums.Status;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;
import com.bsdenterprise.qbits.policeactivity.persistence.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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

        repository.findActivities().forEach(activity -> {
            ActivityMessages am = new ActivityMessages(activity, convertUtils.convert(
                    repository.findMessageDetail(activity.getActivityId()), OutputMessage.class));

            am.getMessages().forEach(m -> Status.findStatusById(m.getStatusId()).ifPresent(st -> m.setStatus(st.name())));
            activityMessages.add(am);
        });

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

    public List<ActivityMessages> findActivityMessages(String activityId, Integer statusId, Long environmentId, Long moduleId) {

        Stream<ActivityMessages> stream = this.allActivityMessages().stream();

        if (!StringUtils.isEmpty(activityId))
            stream = stream.filter(am -> am.getActivity().getActivityId().equals(activityId));
        if (!ObjectUtils.isEmpty(environmentId))
            stream = stream.filter(am -> am.getActivity().getEnvironment().getId().equals(environmentId));
        if (!ObjectUtils.isEmpty(statusId))
            stream = stream.filter(am -> am.getMessages().removeIf(m -> !m.getStatusId().equals(statusId)));
        if (!ObjectUtils.isEmpty(moduleId))
            stream = stream.filter(am -> am.getMessages().removeIf(m -> !m.getModule().getId().equals(moduleId)));

        List<ActivityMessages> activityMessages = stream.collect(Collectors.toList());

        return activityMessages;
    }

}
