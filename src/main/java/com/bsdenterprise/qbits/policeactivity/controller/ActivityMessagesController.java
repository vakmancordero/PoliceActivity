package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.config.ws.MyStompSessionHandler;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.*;

import com.bsdenterprise.qbits.policeactivity.service.ActivityMessagesService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    @GetMapping("/find/suggestions")
    public ResponseEntity findActivities(@RequestParam("activityId") String activityId) {
        return ResponseEntity.ok(activityMessagesService.findActivitiesByActivityIdContaining(activityId));
    }

    @GetMapping("/find/{activityId}")
    public ResponseEntity findActivityMessagesByActivityId(@PathVariable("activityId") String activityId) {
        return ResponseEntity.ok(activityMessagesService.findActivityMessagesByActivityId(activityId));
    }

    @GetMapping("/find")
    public ResponseEntity findActivityMessages(@RequestParam(value = "activityId", required = false) String activityId,
                                               @RequestParam(value = "statusId", required = false) Integer statusId,
                                               @RequestParam(value = "environmentId", required = false) Long environmentId,
                                               @RequestParam(value = "moduleId", required = false) Long moduleId) {
        return ResponseEntity.ok(activityMessagesService.findActivityMessages(activityId, statusId, environmentId, moduleId));
    }

    /*
    * Stomp Client Test
    * */

    @Bean
    public WebSocketStompClient stompClient() {
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return webSocketStompClient;
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    private final String URL = "ws://localhost:8089/ws";

    @Autowired
    private WebSocketStompClient stompClient;

    @GetMapping("/test/sendMessage")
    public void sendMessage() throws InterruptedException, ExecutionException, TimeoutException {

        MyStompSessionHandler sessionHandler = new MyStompSessionHandler();

        StompSession stompSession = stompClient.connect(URL, sessionHandler).get(1, TimeUnit.SECONDS);

        StompSession.Receiptable send = stompSession.send("/app/activity.catchMessage", sessionHandler.getSampleMessage());

    }

}
