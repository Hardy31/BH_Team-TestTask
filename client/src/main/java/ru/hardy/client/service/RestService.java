package ru.hardy.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestService {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    // этот метод переправляем запрос с Клиента на Сервер с командой  начать работу:
    //открыть 3и окна  разместить в центре мышь, подождать 5 секунд и начать двигать мыщ по кругу.
    // при этом  5 раз в секунду необходимо отправлять по socket данные с координатами  мыши.
    public void start() {
        String startUrl = "http://localhost:8080/v1/switch/start";
//        Или через локальный IP    String prefixUrl = "http://192.168.31.207:8080/v1/switch/start";
        sendingRequest(startUrl);
    }

    public void log() {
        String logUrl = "http://localhost:8080//v1/log";
        sendingRequest(logUrl);
    }


    public void finish() {
        String stopUrl = "http://localhost:8080//v1/switch/stop";
        sendingRequest(stopUrl);
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
//                            Toast.makeText(MainActivity1.this, " запрос отправлен", Toast.LENGTH_SHORT).show();

//                            textResponce.setText(response.toString());
                    // Process the response
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the error
                }
            }
        });
        thread.start();
        log.info( " запрос отправлен {} " , url);

    }


}
