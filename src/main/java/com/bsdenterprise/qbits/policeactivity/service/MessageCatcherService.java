package com.bsdenterprise.qbits.policeactivity.service;

import com.bsdenterprise.qbits.policeactivity.common.service.BaseService;
import com.bsdenterprise.qbits.policeactivity.persistence.message.MessageRepository;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageCatcherService extends BaseService<MessageRepository, MessageEntity> {

}
