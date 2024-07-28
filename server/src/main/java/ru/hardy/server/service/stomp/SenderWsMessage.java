package ru.hardy.server.service.stomp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.hardy.server.dto.LogLineDto;

@Component
@Configurable
@Slf4j
public class SenderWsMessage {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

//    @Scheduled(fixedRate = 5000)
    public void sendMessage(LogLineDto logLine) throws JsonProcessingException {

        String stringLogLine = objectMapper.writeValueAsString(logLine);

        OutgoingMessage outgoingMessage = new OutgoingMessage(stringLogLine);   //!!!
        simpMessagingTemplate.convertAndSend("/topic/messages",
                outgoingMessage);


        log.info(" SenderWsMessage sendMessage - {}, Port - {}", outgoingMessage.getContent(), "PORT");

//        https://stackoverflow.com/questions/33004078/nullpointerexception-on-simpmessagingtemplate-in-spring
    }
}
