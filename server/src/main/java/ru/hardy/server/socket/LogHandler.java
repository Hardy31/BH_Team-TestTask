package ru.hardy.server.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import ru.hardy.server.entity.LogLine;
import ru.hardy.server.service.LogLineBlockQueue;

@Slf4j
public class LogHandler implements WebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connection estableshed on session: {}", session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String tutorial = (String) message.getPayload();
        log.info("Message: {}", tutorial);
        ObjectMapper mapper = new ObjectMapper();


        //TODO Надо вынести этот цыкл в отдельный поток.
        while (true){
            LogLine logLine = LogLineBlockQueue.getInstance().take();
            String jsonLogLine = mapper.writeValueAsString(logLine);
            session.sendMessage(new TextMessage(jsonLogLine));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Exception occured:{} no session : {}", exception.getMessage(),session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection closed on session:{} with status : {}", session.getId(), closeStatus.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
