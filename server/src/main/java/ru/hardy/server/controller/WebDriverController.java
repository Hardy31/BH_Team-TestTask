package ru.hardy.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.hardy.server.service.WebDriverService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/web-drivers")
public class WebDriverController {

    private final WebDriverService webDriverService;

    @GetMapping("/start")
    public void start() {
        log.info("SwitchController start()");
        webDriverService.on();
    }

    @GetMapping("/stop")
    public void stop() {
        log.info("SwitchController stop()");
        webDriverService.off();
    }

//    запрос c указанием порта для создания WebSocket соединения для транслирования Сервером  LogLine на клиент
    @GetMapping("/config/{port}")
    public String portСhanged(@PathVariable Integer port) {
        log.info("SwitchController config port - {}", port);
        webDriverService.changePort(port);
        return "порт трансляции  изменен";
    }

}
