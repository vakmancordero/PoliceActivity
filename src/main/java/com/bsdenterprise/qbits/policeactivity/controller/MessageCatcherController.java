package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.EntityNotFoundException;
import com.bsdenterprise.qbits.policeactivity.dto.InputMessage;
import com.bsdenterprise.qbits.policeactivity.dto.OutputMessage;
import com.bsdenterprise.qbits.policeactivity.service.MessageCatcherService;
import com.bsdenterprise.qbits.policeactivity.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catcher")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageCatcherController {

    private final MessageCatcherService messageCatcherService;
    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity catchMessage(@RequestBody InputMessage inputMessage) {

        try {

            moduleService.findById(inputMessage.getModuleId(), null);

            OutputMessage outputMessage = messageCatcherService.save(inputMessage, OutputMessage.class);

            return ResponseEntity.ok(outputMessage);

        } catch(EntityNotFoundException ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");

        } catch(Exception ex) {

            return ResponseEntity.badRequest().body("The message could not be created");

        }

    }



}
