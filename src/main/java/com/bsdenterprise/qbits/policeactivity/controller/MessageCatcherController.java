package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.EntityNotFoundException;
import com.bsdenterprise.qbits.policeactivity.dto.message.InputMessageDTO;
import com.bsdenterprise.qbits.policeactivity.service.MessageCatcherService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/catcher")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "message-catcher", description = "Endpoint for message catching")
public class MessageCatcherController {

    private final MessageCatcherService messageCatcherService;

    @PostMapping
    public ResponseEntity catchMessage(@RequestBody InputMessageDTO inputMessage) {

        try {

            return ResponseEntity.ok(messageCatcherService.catchMessage(inputMessage));

        } catch(EntityNotFoundException ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found, " + ex.getMessage());

        } catch(Exception ex) {

            return ResponseEntity.badRequest().body("The message could not be created, " + ex.getMessage());

        }

    }



}
