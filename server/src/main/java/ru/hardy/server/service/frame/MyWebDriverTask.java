package ru.hardy.server.service.frame;

import java.util.Timer;
import java.util.TimerTask;

public class MyWebDriverTask extends Thread{
    MyWebDriver myWebDriver;

    public MyWebDriverTask(MyWebDriver myWebDriver){
        this.myWebDriver = myWebDriver;
    }

    public  void run(){


        TimerTask timerTaskStap = new TimerTask() {
            @Override
            public void run() {
                myWebDriver.circularStap();
            }
        };
        Timer timerSap = new Timer();
        timerSap.schedule(timerTaskStap, 0, 20);



        TimerTask timerTaskPosition = new TimerTask() {
            @Override
            public void run() {
                myWebDriver.getMousePosition();
            }
        };
        Timer timerPosition = new Timer();
        timerPosition.schedule(timerTaskPosition, 0, 200);
    }

//    public  void stopp(){
//        myWebDriver.movementStop();
//        myWebDriver.getWebDriver().close();
//
//        Thread.interrupted();
//    }
}
