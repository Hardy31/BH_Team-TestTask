package ru.hardy.server.service.frame;

import lombok.SneakyThrows;
import ru.hardy.server.entity.LogLine;
import ru.hardy.server.service.LogLineBlockQueue;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MyWebDriverTask {
    MyWebDriver myWebDriver;
    TimerTask timerTaskStap;
    Timer timerStap;
    TimerTask timerTaskPosition;
    Timer timerPosition;
    LocalDateTime localDateTime;
    Point position;
    LogLine logLine;
    public MyWebDriverTask(MyWebDriver myWebDriver){
        this.myWebDriver = myWebDriver;
    }

    public  void start(){


        timerTaskStap = new TimerTask() {
            @Override
            public void run() {
                myWebDriver.circularStap();
            }
        };
        timerStap = new Timer();
        //интервал delay в 5 секунд (5000) по условию задачи
        timerStap.schedule(timerTaskStap, 5000, 50);



        timerTaskPosition = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                Point mousePosition = myWebDriver.getMousePosition();
                logLine = new LogLine(mousePosition.getX(), mousePosition.getY(), LocalDateTime.now(), myWebDriver.getId());
                //Запись LogLine в блокирующую очередь. Такка записываться в нее будут из 3х timerTaskPosition а читаться из Main
                LogLineBlockQueue.getInstance().putt(logLine);
            }
        };
        timerPosition = new Timer();
        timerPosition.schedule(timerTaskPosition, 0, myWebDriver.getPeriod());
    }

    public  void cansel(){
        timerTaskStap.cancel();
        timerStap.cancel();
        timerTaskPosition.cancel();
        timerPosition.cancel();
        myWebDriver.getWebDriver().close();
    }
}
