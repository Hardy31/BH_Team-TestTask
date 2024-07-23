package ru.hardy.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.client.dto.WsConfigurator;
import ru.hardy.client.enttity.LogLine;
import ru.hardy.client.repository.LogLineRepositiry;
import ru.hardy.client.service.ws_client.WsClientListener;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SwitchService {
    private final OkHttpClient okHttpClient = new OkHttpClient();
    @Autowired
    private final LogLineRepositiry logLineRepositiry;

    public void start() {
        String startUrl = "http://localhost:8080/v1/web-drivers/start";
        sendingRequest(startUrl);
        //поднять WS Client c дефолтными портом 8082
        new WsClientListener(logLineRepositiry);
        //todo Запись полученных данных в PSQL
    }

    public void stop() {
        String stopUrl = "http://localhost:8080/v1/web-drivers/stop";
        sendingRequest(stopUrl);
        //todo остановить WS Client
    }

    public void Log()  {
        //получаем список из БД с сортировкых по : T - по времени, X - по значнию x, Y - по значению y.
        //Несколько вариантов исполнения,



//        List<LogLine> logLists = logLineRepositiry.findAll();
////        log.info(logLists.toString());
//
//
//                    List<LogLine> sortedLogs = logLists.stream().sorted(
//                    Comparator.comparing(LogLine::getRegisteredAt).thenComparing(LogLine::getX).thenComparing(LogLine::getY)
//            ).collect(Collectors.toList());
//
//        for(LogLine logLine : sortedLogs){
//            log.info( logLine.toString());
//        }

//        //сохранение данных в txt файл
        try{
//            List<LogLine> logLines = logLineRepositiry.findAllOrderByregisteredAtOrderByxOrderByy();
//            List<LogLine> logLines = logLineRepositiry.findAllSortetByTXY();

            List<LogLine> logLists = logLineRepositiry.findAll();
            log.info(logLists.toString());


            List<LogLine> sortedLogs = logLists.stream().sorted(
                    Comparator.comparing(LogLine::getRegisteredAt).thenComparing(LogLine::getX).thenComparing(LogLine::getY)
            ).collect(Collectors.toList());
//




//            log.info(logLines.toString());
            log.info(sortedLogs.toString());
//            FileWriter writer = new FileWriter("Lib.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Log.txt")));

            for(LogLine logLine : sortedLogs)
            {
//                log.info(logLine.toString());
                String line = logLine.toString();
                writer.write(line);
                writer.write("\n");

            }
            writer.flush();
            writer.close();
        }catch (IOException ex){ex.printStackTrace();}


    }

    public void wsConfig(WsConfigurator wsConfigurator) {
        String port = wsConfigurator.getPort();
        String configUrl = "http://localhost:8082/v1/web-drivers/config/" + port;
        //Запуск WS WSServer  с новым портом
        sendingRequest(configUrl);
        //Запуск WS Client c новым портом
        new WsClientListener(logLineRepositiry, port);
    }


    public void sendingRequest(String url){

        Request request = new Request.Builder()
                .url(url)
                .build();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        log.info( " запрос отправлен {} " , url);

    }
}
