package ru.hardy.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebDriverService {
    public void on() {
    }

    public void off() {
    }

    public void changePort(Integer port) {
        //здесь должна быть остановка тронсляции если она была и запуск новой.
    }

}
