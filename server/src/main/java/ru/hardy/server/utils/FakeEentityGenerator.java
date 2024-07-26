package ru.hardy.server.utils;

import lombok.extern.slf4j.Slf4j;
import ru.hardy.server.dto.LogLineDto;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

//@Component
//@Configurable
@Slf4j
public class FakeEentityGenerator {
    TimerTask fakeTimerTaskStap;
    Timer fakeTimerStap;
    double angle = 0;

//  Прообраз взят отсюда https://stackoverflow.com/questions/33004078/nullpointerexception-on-simpmessagingtemplate-in-spring

//    @Autowired
//    SimpMessagingTemplate simpMessagingTemplate;

//    @Scheduled(fixedRate = 4000)
//    public void sendMessage(LogLine fakeLogLine) {
//        String text = fakeLogLine.toString();
//        System.out.println(text );
////        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        OutgoingMessage outgoingMessage = new OutgoingMessage(text);
//        simpMessagingTemplate.convertAndSend("/topic/messages",
//                outgoingMessage);
//   }




   public LogLineDto getFakeLogLine(){
       Point  fakeSenterMous = new Point(300, 400);
       Integer fakeRadius = 250;
       LogLineDto fakeLogLine  = new LogLineDto();
       fakeLogLine.setX( fakeSenterMous.getX() + fakeRadius * Math.cos(angle));
       fakeLogLine.setY(fakeSenterMous.getY() + fakeRadius * Math.sin(angle));
       fakeLogLine.setRegisteredAt(LocalDateTime.now());
       fakeLogLine.setWebDriverId(4);
       angle += 0.05;



//       fakeTimerTaskStap = new TimerTask() {
//           @Override
//           public void run() {
//
//               fakeLogLine.setX( fakeSenterMous.getX() + fakeRadius * Math.cos(angle));
//               fakeLogLine.setY(fakeSenterMous.getY() + fakeRadius * Math.sin(angle));
//               fakeLogLine.setRegisteredAt(LocalDateTime.now());
//               fakeLogLine.setWebDriverId(4);
//
//               angle += 0.05;
//           }
//
//       };
//       timerPosition = new Timer();
//       timerPosition.schedule(timerTaskPosition, 0, myWebDriver.getPeriod());
       log.info("Create - {}", fakeLogLine);
       return fakeLogLine;


   }
//    public  void cansel(){
//        fakeTimerTaskStap.cancel();
//        fakeTimerStap.cancel();
//        timerTaskStap.cancel();
//        timerStap.cancel();
//        timerTaskPosition.cancel();
//        timerPosition.cancel();
//        myWebDriver.getWebDriver().close();
//    }

}
