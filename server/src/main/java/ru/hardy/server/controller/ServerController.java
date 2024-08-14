package ru.hardy.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.server.Conductor;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/server")
public class ServerController {
    private final Conductor conductor;
    @GetMapping("/start")
    public void start() {
        log.info("ServerController start()");
        conductor.on();
    }

    @GetMapping("/stop")
    public void stop() {
        log.info("ServerController stop()");
//        conductor.off();
    }

    //    запрос c указанием порта для создания WebSocket соединения для транслирования Сервером  LogLine на клиент
    @GetMapping("/config/{port}/{frequency}")
    public String configuration(@PathVariable String port, Integer frequency) {

        log.info("ServerController config old-port - {}, old-frequency -{}", port, frequency);
        System.setProperty("server.portDestination", port);
        System.setProperty("server.frequency", frequency.toString());
        log.info("ServerController config port - {}, frequency -{}", System.getProperty("server.portDestination"), System.getProperty("server.frequency"));
//        conductor.configuration(port, frequency);
        return "Конфигурация изменена";
    }
}
