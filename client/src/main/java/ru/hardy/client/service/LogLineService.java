package ru.hardy.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hardy.client.Repository.LogLineRepositiry;
import ru.hardy.client.enttity.LogLine;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogLineService {
    public final LogLineRepositiry logLineRepositiry;
    public LogLine create(LogLine logLine){
       LogLine savedLogLine =  logLineRepositiry.save(logLine);
       return  savedLogLine;
    }
}
