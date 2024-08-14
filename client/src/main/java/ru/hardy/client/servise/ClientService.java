package ru.hardy.client.servise;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.hardy.client.dto.LogLineDTO;
import ru.hardy.client.dto.WsConfiguratorDto;
import ru.hardy.client.entity.LogLine;
import ru.hardy.client.repository.LogLineRepository;


import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor


//https://www.baeldung.com/debug-websockets

public class ClientService {
    private final  OkHttpClient okHttpClient = new OkHttpClient();
    private final LogLineRepository logLineRepositiry;

    public void start() {
        //Запрос на Server (Где будет запущены 3и WebDriver  c мышюю)


        String startUrl = "http://localhost:8082/v1/server/start";
        sendingRequest(startUrl);
    }

    public void stop() {
//        String stopUrl = "http://localhost:8082/v1/server/stop";
//        sendingRequest(stopUrl);
//        //остананвливаем WS Client
//        WebSocketStompClient stompClient = WsConfigurator.getInstance().getStompClient();
//        stompClient.stop();
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

        log.info(" wsConfig -- {}", wsConfiguratorDto.toString());

        //Пересылаем новый порт соединения и частоту для настройки Сервера (который будет являться WS клиентом) а
        // Client ( будет являться WS Сервером)
        //Это  вызвано тем что Tomcat не может динамически менять порты прослушиванияю
        // А по условию задачи мы должны иметь возможность  менять порт трансляции ws Сервера.
        String configUrl = "http://localhost:8082/v1/server/ws-config/" + wsConfiguratorDto.getPort()+"/"+ wsConfiguratorDto.getFrequency();
        sendingRequest(configUrl);

    }

    public  void receive(LogLineDTO logLineDTO){
        LogLine logLine = new LogLine(
                logLineDTO.getX(),
                logLineDTO.getY(),
                logLineDTO.getRegisteredAt(),
                logLineDTO.getWebDriverId()
        );
        logLineRepositiry.save(logLine);
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
