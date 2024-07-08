package ru.hardy.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hardy.client.service.RestService;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
public class RestController {

    private final RestService restService;

    //
    @GetMapping("/start")
    public void start() {
        log.info("SwitchController start()");
        restService.start();
    }

    @GetMapping("/log")
    public void log() {
        log.info("SwitchController start()");
        restService.log();
    }


    @GetMapping("/finish")
    public void finish() {
        log.info("SwitchController stop()");
        restService.finish();
    }
}
