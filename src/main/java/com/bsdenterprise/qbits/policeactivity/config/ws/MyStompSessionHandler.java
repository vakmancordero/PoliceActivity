package com.bsdenterprise.qbits.policeactivity.config.ws;

import com.bsdenterprise.qbits.policeactivity.dto.message.InputMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

/**
 * This class is an implementation for <code>StompSessionHandlerAdapter</code>.
 * Once a connection is established, We subscribe to /topic/messages and 
 * send a sample message to server.
 * 
 * @author Kalyan
 *
 */
@Slf4j
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("! New session established : " + session.getSessionId());
        //session.subscribe("/topic/messages", this);
        log.info("! Subscribed to /topic/messages");
        //session.send("/app/chat", getSampleMessage());
        log.info("! Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return InputMessageDTO.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        InputMessageDTO msg = (InputMessageDTO) payload;
        log.info("Received : " + msg.toString());
    }

    /**
     * A sample message instance.
     * @return instance of <code>Message</code>
     */
    public InputMessageDTO getSampleMessage() {

        InputMessageDTO inputMessage = new InputMessageDTO();

        inputMessage.setActivityId("Test Activity 1");
        inputMessage.setEnvironmentId(1L);
        inputMessage.setModuleId(1L);
        inputMessage.setMessage("Some message");
        inputMessage.setStatusId(1);

        return inputMessage;
    }
}