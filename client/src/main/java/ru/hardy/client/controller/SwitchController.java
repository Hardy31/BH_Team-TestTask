package ru.hardy.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hardy.client.dto.WsConfiguratorDto;
import ru.hardy.client.service.SwitchService;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
public class SwitchController {

    private final SwitchService switchService;

    //запускает сторонний Сервер
    @GetMapping("/start")
    public String start() {
        log.info("SwitchController start()");
        switchService.start();
        return "Server start";
    }

    //Останавливает сторонний Сервер
    @GetMapping("/stop")
    public String stop() {
        log.info("SwitchController stop()");
        switchService.stop();
        return "Server stop";
    }

    //запись данных поступивших от сервера в файл log.txt
    @GetMapping("/log")
    public String log() {
        log.info("SwitchController log()");
        switchService.Log();
        return "Server writes logs to file";
    }

    //изменение дефолтных конфигурационных данных для WS соединения
    @PostMapping("/wsConfig")
    public String wsConfig(@RequestBody WsConfiguratorDto wsConfiguratorDto) {
        log.info("SwitchController wsConfig - {}", wsConfiguratorDto);
        switchService.wsConfig(wsConfiguratorDto);
        return "SwitchController wsConfig ------ " +  wsConfiguratorDto.toString();
//        return "SwitchController wsConfig - " +  wsConfigurator.toString();
    }

}
