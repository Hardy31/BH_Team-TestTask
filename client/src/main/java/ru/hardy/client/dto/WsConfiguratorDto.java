package ru.hardy.client.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WsConfiguratorDto {
    private static WsConfiguratorDto instance;

    private String ip= "127.0.0.1";

    //Дефолтное значение порта для WebSocket
    @Setter
    private String port = "8082";

    //частота измеряется в герцах (число активных переходов в секунду)
    @Setter
    private Integer frequency = 5;  //по умолчанию 5 раз в секунду (1000/5 = 200)

    private WsConfiguratorDto(){}

    public static WsConfiguratorDto getInstance() {
        if (instance == null) {
            instance = new WsConfiguratorDto();
        }
        return instance;
    }

}
