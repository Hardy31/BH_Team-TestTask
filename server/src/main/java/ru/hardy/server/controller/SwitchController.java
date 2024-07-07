package ru.hardy.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.server.service.SwitchService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/switch")
public class SwitchController {

    private final SwitchService switchService;

    @GetMapping("/start")
    public void start() {
        log.info("SwitchController start()");
        switchService.on();
    }

    @GetMapping("/stop")
    public void stop() {
        log.info("SwitchController stop()");
        switchService.off();
    }



}
