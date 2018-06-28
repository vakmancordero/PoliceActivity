package com.bsdenterprise.qbits.policeactivity.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bsdenterprise.qbits.policeactivity.service.ActivityMessagesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/activity/messages")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "activity-messages", description = "Endpoint for activity messages visualization")
public class ActivityMessagesController {

    private final ActivityMessagesService activityMessagesService;

    @GetMapping
    public ResponseEntity allActivityMessages() {
        return ResponseEntity.ok(activityMessagesService.allActivityMessages());
    }

    @GetMapping("/find/{activityId}")
    public ResponseEntity findActivityMessagesByActivityId(@PathVariable("activityId") String activityId) {
        return ResponseEntity.ok(activityMessagesService.findActivityMessagesByActivityId(activityId));
    }

    @GetMapping("/find")
    public ResponseEntity findActivityMessages(@RequestParam(value = "activityId", required = false) String activityId,
                                               @RequestParam(value = "status", required = false) String status,
                                               @RequestParam(value = "environment", required = false) String environment) {
        return ResponseEntity.ok(activityMessagesService.findActivityMessages(activityId, status, environment));
    }

}
