package ru.hardy.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hardy.server.service.frame.MonitorResolution;
import ru.hardy.server.service.frame.MyWebDriver;
import ru.hardy.server.service.frame.MyWebDriverTask;


@Service
@Slf4j
@RequiredArgsConstructor
public class SwitchService {
    private MyWebDriverTask  myWebDriverTask1;
    private MyWebDriverTask  myWebDriverTask2;
    private MyWebDriverTask  myWebDriverTask3;


    public void on()  {
        //Выбор манитора
        MonitorResolution monitorResolution = new MonitorResolution();
        monitorResolution.selectionMonitor();

        MyWebDriver myWebDriver1 = new MyWebDriver(monitorResolution, 1);
        MyWebDriver myWebDriver2 = new MyWebDriver(monitorResolution, 2);
        MyWebDriver myWebDriver3 = new MyWebDriver(monitorResolution, 3);

     myWebDriverTask1 = new MyWebDriverTask(myWebDriver1);
      myWebDriverTask2 = new MyWebDriverTask(myWebDriver2);
        myWebDriverTask3 = new MyWebDriverTask(myWebDriver3);

        myWebDriverTask1.start();
        myWebDriverTask2.start();
        myWebDriverTask3.start();

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        myWebDriverTask1.cansel();
        myWebDriverTask2.cansel();
        myWebDriverTask3.cansel();

        };


    public void off()  {
        myWebDriverTask1.cansel();
        myWebDriverTask2.cansel();
        myWebDriverTask3.cansel();
    }

}
