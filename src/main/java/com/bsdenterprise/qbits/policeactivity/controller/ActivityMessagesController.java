package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.service.ActivityMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity/messages")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityMessagesController {

    private final ActivityMessagesService activityMessagesService;

    @GetMapping
    public ResponseEntity allActivityMessages() {
        return ResponseEntity.ok(activityMessagesService.allActivityMessages());
    }

    @GetMapping("/{activityId}")
    public ResponseEntity findActivityMessagesByActivityId(@PathVariable("activityId") String activityId) {
        return ResponseEntity.ok(activityMessagesService.findActivityMessagesByActivityId(activityId));
    }

    @GetMapping("/find")
    public ResponseEntity findActivityMessages(@RequestParam("activityId") String activityId,
                                               @RequestParam("status") String status,
                                               @RequestParam("environment") String environment) {
        return ResponseEntity.ok(activityMessagesService.findActivityMessages(activityId, status, environment));
    }

}
