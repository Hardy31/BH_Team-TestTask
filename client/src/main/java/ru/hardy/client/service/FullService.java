package ru.hardy.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FullService {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public void start() {
        String prefixUrl = "http://localhost:8080/v1/switch/start";
//        Или через локальный IP    String prefixUrl = "http://192.168.31.207:8080/v1/switch/start";
    }

    public void log() {
        String prefixUrl = "http://localhost:8080//v1/log/file";
    }


    public void finish() {
        String stopUrl = "http://localhost:8080//v1/switch/stop";
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
