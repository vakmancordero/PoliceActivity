package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.dto.message.InputMessageDTO;
import com.bsdenterprise.qbits.policeactivity.dto.message.OutputMessageDTO;
import com.bsdenterprise.qbits.policeactivity.service.MessageCatcherService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageCatcherController {

    private final MessageCatcherService messageCatcherService;

    @MessageMapping("/activity.catchMessage")
    @SendTo("/topic/monitor")
    public OutputMessageDTO catchMessage(@Payload InputMessageDTO inputMessage) {

        try {
            return messageCatcherService.catchMessage(inputMessage);
        } catch(Exception ex) {
            return new OutputMessageDTO();
        }

    }

}
