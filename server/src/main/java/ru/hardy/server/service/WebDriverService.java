package ru.hardy.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.server.dto.LogLineDto;
import ru.hardy.server.service.stomp.SenderWsMessage;
import ru.hardy.server.utils.FakeEentityGenerator;

import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
public class WebDriverService {
//    @Autowired
//    private WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> customizer;

    TimerTask fakeTimerTaskStap;
    Timer fakeTimerStap;

    FakeEentityGenerator util = new FakeEentityGenerator();
    @Autowired
    SenderWsMessage sender;

    public void on() {
        fakeTimerTaskStap = new TimerTask() {
           @Override
           public void run() {
//               FakeEentityGenerator util = new FakeEentityGenerator();
               LogLineDto fakeLogLine = util.getFakeLogLine();
               try {
                   sender.sendMessage(fakeLogLine);
               } catch (JsonProcessingException e) {
                   throw new RuntimeException(e);
               }

           }

       };
        fakeTimerStap = new Timer();
        fakeTimerStap.schedule(fakeTimerTaskStap, 0, 2000);


    }

    public void off() {
        fakeTimerTaskStap.cancel();
        fakeTimerStap.cancel();

    }
//    @Autowired
//    SimpMessagingTemplate simpMessagingTemplate;
//
//    @Scheduled(fixedRate = 5000)
//    public void sendMessage() {
//        String text = " SimpMessagingTemplate Send /topic/messages - " + System.currentTimeMillis();
//        System.out.println(text );
////        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        OutgoingMessage outgoingMessage = new OutgoingMessage(text);
//        simpMessagingTemplate.convertAndSend("/topic/messages",
//                outgoingMessage);
////        https://stackoverflow.com/questions/33004078/nullpointerexception-on-simpmessagingtemplate-in-spring
//    }



    public void changePort(String port) {

        //меняем порт
//        SpringApplication app = new SpringApplication(ServerApplication.class);
//        app.setDefaultProperties(Collections.singletonMap("server.port", port));
//        customizer.customize(factory -> factory.setPort(port));
//        ConfigurableApplicationContext context = app.run();

        System.setProperty("server.port", port);

        //здесь должна быть остановка тронсляции если она была и запуск новой.
    }

}
