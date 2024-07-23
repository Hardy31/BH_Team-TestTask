package ru.hardy.client.service.ws_client;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import ru.hardy.client.enttity.LogLine;
import ru.hardy.client.repository.LogLineRepositiry;

import java.lang.reflect.Type;

public class WsClientSessionHandler extends StompSessionHandlerAdapter {
    private LogLineRepositiry logLineRepositiry;

    public WsClientSessionHandler(LogLineRepositiry logLineRepositiry) {
        this.logLineRepositiry = logLineRepositiry;
    }

    public Type getPayloadType(StompHeaders headers) {
        return LogLine.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        LogLine message = (LogLine) payload;
        System.out.println("Received : " + ((LogLine) payload).toString());
        logLineRepositiry.save(message);

    }
}
