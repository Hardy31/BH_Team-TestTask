package ru.hardy.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.server.service.LogService;
import ru.hardy.server.service.SwitchService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/log")
public class LogController {

//    private final LogService logService;

    @GetMapping("file")
    public void file() {
        log.info("LogController file()");
    }
}
