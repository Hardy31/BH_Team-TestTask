package ru.hardy.server.service.frame;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Getter
public class MyWebDriver {
    private MonitorResolution monitorResolution;
    private Integer id;     // Идентификатор WebDriver
    private  Integer count = 3;
    private Robot robot;
    private double mousePositionX;
    private double mousePositionY;
    private  WebDriverFrame webDriverFrame;
    private WebDriver webDriver;
    private Timer timer;
    private TimerTask timerTask;

    int radius ;


    public MyWebDriver( MonitorResolution monitorResolution, Integer id) {
        this.monitorResolution = monitorResolution;
        this.id = id;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        //нициализация WebDriver
        // Заменить на FireFox
        webDriver = new ChromeDriver();

        positioning();
        mouseInCenter();
        radius = getRandomRadius();
    }

    public void positioning() {

        int widthMonitor = monitorResolution.getWidth();
        int heightMonitor = monitorResolution.getHeight();
        int widthWebDriver = (Integer)widthMonitor/3;
        int heightWebDriver = (Integer)heightMonitor/2;
        int xPosition = (id-1)*widthWebDriver;
        int yPosition = 0;

        //создание webDriverFrame
        webDriverFrame = new WebDriverFrame(widthWebDriver,heightWebDriver,xPosition,yPosition);

        //выставление размера WebDriver
        org.openqa.selenium.Dimension dimension = new org.openqa.selenium.Dimension(webDriverFrame.getWidth(), webDriverFrame.getHeight());
        webDriver.manage().window().setSize(dimension);

        // Установка положения WebDriver (верхний левый угол)
        org.openqa.selenium.Point position = new Point(xPosition, yPosition);
        webDriver.manage().window().setPosition(position);

    }

    public   void mouseInCenter(){
//        Actions actions = new Actions(webDriver);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        //перместили мышь в центр рамки WebDriver
        robot.mouseMove((int) webDriverFrame.getPointCenter().getX(), (int) webDriverFrame.getPointCenter().getY());


    }

    public void movementStop(){
        timer.cancel();
        timerTask.cancel();


    }


    double angle = 0;

    public void circularMovement() {

        double cx = webDriverFrame.getPointCenter().getX();
        double cy = webDriverFrame.getPointCenter().getY();
//        double angle = 0;
//        Point SenterPoint = new Point((int) cx, (int) cy);



//         timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                getMousePosition();
//            }
//        };
//
//        timer = new Timer();
//        timer.schedule(timerTask, 100, 200);




//        for (int i = 0; i < 10000; i += 1) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            double x = cx + radius * Math.cos(angle);
//            double y = cy + radius * Math.sin(angle);
//            angle += 0.05;
//
//            robot.mouseMove((int) x, (int) y);
//        }

        double x = cx + radius * Math.cos(angle);
        double y = cy + radius * Math.sin(angle);
        angle += 0.05;

        robot.mouseMove((int) x, (int) y);


//        timer.cancel();
    }

    public void circularStap() {

        double cx = webDriverFrame.getPointCenter().getX();
        double cy = webDriverFrame.getPointCenter().getY();
        double x = cx + radius * Math.cos(angle);
        double y = cy + radius * Math.sin(angle);
        angle += 0.05;
        robot.mouseMove((int) x, (int) y);


//        timer.cancel();
    }

    // получение рандомного радиуса (вписанного в размер  webDriverFrame)
    private int getRandomRadius(){
        int x;
        if(webDriverFrame.getWidth()> webDriverFrame.getHeight()){
            x =webDriverFrame.getHeight();
        }else{ x= webDriverFrame.getWidth();}

        double tempResult = Math.random() *  x / 4;
        return (int) Math.ceil(tempResult);

    }

    public java.awt.Point getMousePosition(){
        java.awt.Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePositionX = mousePoint.getX();
        mousePositionY = mousePoint.getY();

        log.info(" Mouse position X = {}, Y = {}", mousePositionX, mousePositionY);

        //Здесь должна быть отправка координат мыши на Клиент или там гкуда они возвращаются.
        return  mousePoint;
    }



}
