package ru.hardy.server.service.frame;

import ru.hardy.server.entity.LogRow;

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
    LogRow logRow;
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
            @Override
            public void run() {
                Point mousePosition = myWebDriver.getMousePosition();
                logRow = new LogRow(mousePosition.getX(), mousePosition.getY(), LocalDateTime.now(), myWebDriver.getId());

                //TODO Отправка Socket

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
