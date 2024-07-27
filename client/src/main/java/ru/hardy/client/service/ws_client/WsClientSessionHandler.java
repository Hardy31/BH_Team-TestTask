package ru.hardy.client.service.ws_client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import ru.hardy.client.dto.LogLineDto;
import ru.hardy.client.enttity.LogLine;
import ru.hardy.client.repository.LogLineRepositiry;

import java.lang.reflect.Type;

@Slf4j
public class WsClientSessionHandler extends StompSessionHandlerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private LogLineRepositiry logLineRepositiry;

    public WsClientSessionHandler(LogLineRepositiry logLineRepositiry) {
        this.logLineRepositiry = logLineRepositiry;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OutgoingMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        String textMessage = ((OutgoingMessage) payload).getContent();
        log.info("----Received  Object payload: {}",   textMessage);
        LogLineDto logLineDto = null;
        try {
            logLineDto = objectMapper.readValue(textMessage, LogLineDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("----Received  LogLineDto: {}",   logLineDto.toString());
        LogLine message = new LogLine(
                logLineDto.getX(),
                logLineDto.getY(),
                logLineDto.getRegisteredAt(),
                logLineDto.getWebDriverId()
        );
        logLineRepositiry.save(message);
    }
}
