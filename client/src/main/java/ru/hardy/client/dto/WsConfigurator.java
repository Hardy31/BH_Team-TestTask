package ru.hardy.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WsConfigurator {
    
    private String ip= "127.0.0.1";

    //Дефолтное значение порта для WebSocket , просие настройки Ыукмукф в файле server/src/main/resources/application.properties
    private String port = "8084";

    //частоат измеряется в герцах (число активных переходов в секунду)
    private Integer frequency = 5;  //по умолчанию 5 раз в секунду (1000/5 = 200)

}
