package ru.hardy.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hardy.server.entity.LogRow;

@Service
@Slf4j
public class LogService {


    public void writeToFile()  {
        log.info("Запись в  Лога в файл");

//        LogRow logRow = new LogRow()
        //TODO

    }
}
