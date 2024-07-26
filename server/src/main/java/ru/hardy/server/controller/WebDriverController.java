package ru.hardy.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.hardy.server.service.WebDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/web-drivers")
public class WebDriverController {

    private final WebDriverService webDriverService;

//    @Autowired
//    private WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> customizer;

    @GetMapping("/start")
    public void start() {
        log.info("WebDriverController start()");
        webDriverService.on();
    }

    @GetMapping("/stop")
    public void stop() {
        log.info("WebDriverController stop()");
        webDriverService.off();
    }

//    запрос c указанием порта для создания WebSocket соединения для транслирования Сервером  LogLine на клиент
    @GetMapping("/config/{port}")
    public String portСhanged(@PathVariable String port) {
        log.info("WebDriverController config port - {}", port);
        webDriverService.changePort(port);

        System.setProperty("server.port", port);

        return "порт трансляции  изменен";
    }

}
