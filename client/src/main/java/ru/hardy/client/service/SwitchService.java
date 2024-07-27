package ru.hardy.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import ru.hardy.client.dto.WsConfiguratorDto;
import ru.hardy.client.enttity.LogLine;
import ru.hardy.client.repository.LogLineRepositiry;
import ru.hardy.client.service.ws_client.WsClientListener;
import ru.hardy.client.service.ws_client.WsConfigurator;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SwitchService {
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final LogLineRepositiry logLineRepositiry;

    public void start() {
        //Запрос на Server (Где будет запущены 3и WebDriver  c мышюю)
        String startUrl = "http://localhost:8082/v1/web-drivers/start";
        sendingRequest(startUrl);
        //поднимает WS Client с портом по дефолту 8082
        // Listener потому что только слушает отправляемые сервером LogLin
        new WsClientListener(logLineRepositiry);

    }

    public void stop() {
        String stopUrl = "http://localhost:8082/v1/web-drivers/stop";
        sendingRequest(stopUrl);
        //todo остановить WS Client
    }

    public void Log()  {
        //получаем список из БД с сортировкых по : T - по времени, X - по значнию x, Y - по значению y.

        try{
            //получаем все данные
            List<LogLine> logLists = logLineRepositiry.findAll();
            log.info(logLists.toString());

            //сортируем в стриме
            List<LogLine> sortedLogs = logLists.stream().sorted(
                    Comparator.comparing(LogLine::getRegisteredAt).thenComparing(LogLine::getX).thenComparing(LogLine::getY)
            ).collect(Collectors.toList());

            log.info(sortedLogs.toString());
            //пишем отсортированные данные  в файл Log.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Log.txt")));
            for(LogLine logLine : sortedLogs)
            {
                String line = logLine.toString();
                writer.write(line);
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        }catch (IOException ex){ex.printStackTrace();}
    }

    public void wsConfig(WsConfiguratorDto wsConfiguratorDto) {
        //Копируем полученные конфигурационные данные в Singltone WsConfigurator
        WsConfigurator wsConfigurator = WsConfigurator.getInstance();
        wsConfigurator.setIp(wsConfiguratorDto.getIp());
        wsConfigurator.setPort(wsConfiguratorDto.getPort());
        wsConfigurator.setFrequency(wsConfiguratorDto.getFrequency());

        log.info(" wsConfig -- {}", wsConfigurator.toString());

        //Пересылаем новый порт соединения для WS c
        String configUrl = "http://localhost:8082/v1/web-drivers/config/" + wsConfigurator.getPort();
        sendingRequest(configUrl);

        try {
            Thread.sleep(6000);     //ждем пока запустится сервер
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //поднимает WS Client с новым портом

        new WsClientListener(logLineRepositiry);
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
