package com.bsdenterprise.qbits.policeactivity.service;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.ValidationException;
import com.bsdenterprise.qbits.policeactivity.common.service.BaseService;
import com.bsdenterprise.qbits.policeactivity.dto.environment.EnvironmentOutDTO;
import com.bsdenterprise.qbits.policeactivity.dto.message.InputMessage;
import com.bsdenterprise.qbits.policeactivity.dto.message.InputMessageDTO;
import com.bsdenterprise.qbits.policeactivity.dto.message.OutputMessageDTO;
import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import com.bsdenterprise.qbits.policeactivity.enums.Status;
import com.bsdenterprise.qbits.policeactivity.persistence.message.MessageRepository;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageCatcherService extends BaseService<MessageRepository, MessageEntity> {

    private final ModuleService moduleService;
    private final EnvironmentService environmentService;

    public OutputMessageDTO catchMessage(InputMessageDTO inputMessageDTO) throws Exception {

        Optional<Status> status = Status.findStatusById(inputMessageDTO.getStatusId());
        if (!status.isPresent()) throw new ValidationException("Status not found");

        InputMessage inputMessage = convertUtils.convert(inputMessageDTO, InputMessage.class);

        inputMessage.setStatusId(status.get().getId());
        inputMessage.setModule(moduleService.findById(inputMessageDTO.getModuleId(), ModuleOutDTO.class));
        inputMessage.setEnvironment(environmentService.findById(inputMessageDTO.getEnvironmentId(), EnvironmentOutDTO.class));

        OutputMessageDTO savedMessage = this.save(inputMessage, OutputMessageDTO.class);
        savedMessage.setStatus(status.get().name());

        return savedMessage;
    }

}
