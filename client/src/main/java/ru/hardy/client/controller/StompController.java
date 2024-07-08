package ru.hardy.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.hardy.client.enttity.LogLine;
import ru.hardy.client.service.LogLineService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class StompController {

    private final LogLineService logLineService;

    //    @SendTo нам не нужен , так как мы не будем возвращать ответ
    // по хорошему тут стоит принимать DTO LogRow. Но так как особых монипуляций с ней не будет мримем Entity
    @MessageMapping("/getLL")

    public void handleGetLL(LogLine logLine){
        //        сохраняем  полученный  logLine
        logLineService.create(logLine);

    }
}
