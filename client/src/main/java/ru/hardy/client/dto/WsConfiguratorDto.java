package ru.hardy.client.dto;

import lombok.Getter;

@Getter
//  Простая реализация паттерна Singltone
public class WsConfiguratorDto {

    private static WsConfiguratorDto instance;

    private String ip= "127.0.0.1";

    //Дефолтное значение порта для WebSocket
    private String port = "8082";

    //частоат измеряется в герцах (число активных переходов в секунду)
    private Integer frequency = 5;  //по умолчанию 5 раз в секунду (1000/5 = 200)

    private WsConfiguratorDto(){}

    public static WsConfiguratorDto getInstance() {
        if (instance == null) {
            instance = new WsConfiguratorDto();
        }
        return instance;
    }

    // для возможности менять порт в конфигурационном файле
    public void setPort(String port) {
        this.port = port;
    }




}
