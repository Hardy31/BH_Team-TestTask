package ru.hardy.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.client.service.FullService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
public class FullController {

    private final FullService fullService;

    @GetMapping("/start")
    public void start() {
        log.info("SwitchController start()");
        fullService.start();
    }

    @GetMapping("/log")
    public void log() {
        log.info("SwitchController start()");
        fullService.log();
    }


    @GetMapping("/finish")
    public void finish() {
        log.info("SwitchController stop()");
        fullService.finish();
    }
}
