package ru.hardy.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import ru.hardy.client.dto.LogLineDTO;
import ru.hardy.client.dto.WsConfiguratorDto;
import ru.hardy.client.entity.LogLine;
import ru.hardy.client.servise.ClientService;

@Slf4j
@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    //запускает сторонний Сервер
    @GetMapping("/start")
    public String start() {
        log.info("ClientController start()");
        clientService.start();
        return "Server start";
    }

    //Останавливает сторонний Сервер
    @GetMapping("/stop")
    public String stop() {
        log.info("ClientController stop()");
        clientService.stop();
        return "Server stop";
    }

    //запись данных поступивших от сервера в файл log.txt
    @GetMapping("/log")
    public String log() {
        log.info("ClientController log()");
        clientService.Log();
        return "Server writes logs to file";
    }

    //изменение дефолтных конфигурационных данных для WS соединения
    @PostMapping("/wsConfig")
    public String wsConfig(@RequestBody WsConfiguratorDto wsConfiguratorDto) {
        log.info("ClientController wsConfig - {}", wsConfiguratorDto);
        clientService.wsConfig(wsConfiguratorDto);
        return "ClientController wsConfig ------ " +  wsConfiguratorDto.toString();
//        return "SwitchController wsConfig - " +  wsConfigurator.toString();
    }

    @MessageMapping("/websocket-server")
//    @SendTo("/topic/messages")
    public void receive(LogLineDTO logLineDTO) throws Exception {
        clientService.receive(logLineDTO);
        log.info("ClientController receive - {}", logLineDTO);

    }
}
