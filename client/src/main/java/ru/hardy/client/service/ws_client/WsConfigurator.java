package ru.hardy.client.service.ws_client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//  Простая реализация паттерна Singltone
public class WsConfigurator {

    private static WsConfigurator instance;

    private String ip= "127.0.0.1";

    //Дефолтное значение порта для WebSocket
    private String port = "8082";

    //частоат измеряется в герцах (число активных переходов в секунду)
    private Integer frequency = 5;  //по умолчанию 5 раз в секунду (1000/5 = 200)

    private WsConfigurator(){}

    public static WsConfigurator getInstance() {
        if (instance == null) {
            instance = new WsConfigurator();
        }
        return instance;
    }

}
