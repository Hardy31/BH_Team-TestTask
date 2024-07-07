package ru.hardy.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.java.games.input.Event;
import net.java.games.input.Mouse;
import org.springframework.stereotype.Service;
import ru.hardy.server.service.frame.MonitorResolution;
import ru.hardy.server.service.frame.MyWebDriver;
import ru.hardy.server.service.frame.MyWebDriverTask;
import ru.hardy.server.service.frame.ReadFirstMouse;


import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


@Service
@Slf4j
@RequiredArgsConstructor
public class SwitchService {

//    private Timer mTimer;
//    private MyTimerTask mMyTimerTask;

    public void start()  {
        //Выбор манитора
        MonitorResolution monitorResolution = new MonitorResolution();
        monitorResolution.selectionMonitor();

        MyWebDriver myWebDriver1 = new MyWebDriver(monitorResolution, 1);
        MyWebDriver myWebDriver2 = new MyWebDriver(monitorResolution, 2);
//        MyWebDriver myWebDriver3 = new MyWebDriver(monitorResolution, 3);


        MyWebDriverTask  myWebDriverTask1 = new MyWebDriverTask(myWebDriver1);

        myWebDriverTask1.start();


//        TimerTask timerTask1 = new TimerTask() {
//            @Override
//            public void run() {
//                myWebDriver1.getMousePosition();
//            }
//        };
//
//        Timer timer1 = new Timer();
//        timer1.schedule(timerTask1, 100, 200);
//
//
//
//
//
//
//
//
//        TimerTask timerTask2 = new TimerTask() {
//            @Override
//            public void run() {
//                myWebDriver1.circularMovement();
//            }
//        };
//
//        Timer timer2 = new Timer();
//        timer2.schedule(timerTask2, 10, 20);




//        TimerTask timerTask3 = new TimerTask() {
//            @Override
//            public void run() {
//                myWebDriver2.circularMovement();
//            }
//        };
//
//        Timer timer3 = new Timer();
//        timer3.schedule(timerTask3, 10, 20);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        timer2.cancel();




        myWebDriverTask1.interrupt();
//        timer1.cancel();

















//        Runnable runnable = () -> {
//            // your code here ...
//            MyWebDriver myWebDriver1 = new MyWebDriver(monitorResolution, 1);
//            log.info("---- DSGJKYTY______");
//            System.out.println("---- DSGJKYTY______");
//            try {
//                Runtime.getRuntime().exec("xinput create-master mouse1");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        };
//        Thread t = new Thread(runnable);
//        t.start();


//        myWebDriver1.mouseInCenter();
//        myWebDriver2.mouseInCenter();
//        myWebDriver3.mouseInCenter();
//
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        ThreadTask threadTask1 = new ThreadTask(myWebDriver1);
//        threadTask1.start();
//
//
//
//
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
////        threadTask1.stopp();
//        threadTask1.interrupt();


        };



}
