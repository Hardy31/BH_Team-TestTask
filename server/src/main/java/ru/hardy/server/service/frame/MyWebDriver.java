package ru.hardy.server.service.frame;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

@Slf4j
@Getter
//@ConfigurationProperties(prefix = "application.MyWebDriver")
public class MyWebDriver {
    private MonitorResolution monitorResolution;
    private Integer id;     // Идентификатор WebDriver
    private Integer count = 3;
    private Robot robot;
    private double mousePositionX;
    private double mousePositionY;
    private WebDriverFrame webDriverFrame;
    private WebDriver webDriver;
    //    @Value("${application.frequency}")
    private Integer period = 200;
    int radius;


    public MyWebDriver(MonitorResolution monitorResolution, Integer id) {
        this.monitorResolution = monitorResolution;
        this.id = id;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        //нициализация WebDriver
        // TODO Заменить на FireFox
        webDriver = new ChromeDriver();

        positioning();
        mouseInCenter();
        radius = getRandomRadius();
    }

    public void positioning() {

        int widthMonitor = monitorResolution.getWidth();
        int heightMonitor = monitorResolution.getHeight();
        int widthWebDriver = (Integer) widthMonitor / 3;
        int heightWebDriver = (Integer) heightMonitor / 2;
        int xPosition = (id - 1) * widthWebDriver;
        int yPosition = 0;

        //создание webDriverFrame
        webDriverFrame = new WebDriverFrame(widthWebDriver, heightWebDriver, xPosition, yPosition);

        //выставление размера WebDriver
        org.openqa.selenium.Dimension dimension = new org.openqa.selenium.Dimension(webDriverFrame.getWidth(), webDriverFrame.getHeight());
        webDriver.manage().window().setSize(dimension);

        // Установка положения WebDriver (верхний левый угол)
        org.openqa.selenium.Point position = new Point(xPosition, yPosition);
        webDriver.manage().window().setPosition(position);

    }

    public void mouseInCenter() {

        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        //перместили мышь в центр рамки WebDriver
        robot.mouseMove((int) webDriverFrame.getPointCenter().getX(), (int) webDriverFrame.getPointCenter().getY());
    }


    double angle = 0;

    public void circularMovement() {

        double cx = webDriverFrame.getPointCenter().getX();
        double cy = webDriverFrame.getPointCenter().getY();

        double x = cx + radius * Math.cos(angle);
        double y = cy + radius * Math.sin(angle);
        angle += 0.05;

        robot.mouseMove((int) x, (int) y);
    }

    public void circularStap() {

        double cx = webDriverFrame.getPointCenter().getX();
        double cy = webDriverFrame.getPointCenter().getY();
        double x = cx + radius * Math.cos(angle);
        double y = cy + radius * Math.sin(angle);
        angle += 0.05;
        robot.mouseMove((int) x, (int) y);

    }

    // получение рандомного радиуса (вписанного в размер  webDriverFrame)
    private int getRandomRadius() {
        int x;
        if (webDriverFrame.getWidth() > webDriverFrame.getHeight()) {
            x = webDriverFrame.getHeight();
        } else {
            x = webDriverFrame.getWidth();
        }

        double tempResult = Math.random() * x / 4;
        return (int) Math.ceil(tempResult);

    }

    public java.awt.Point getMousePosition() {
        java.awt.Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePositionX = mousePoint.getX();
        mousePositionY = mousePoint.getY();

        log.info(" Mouse position X = {}, Y = {}", mousePositionX, mousePositionY);

        //Здесь должна быть отправка координат мыши на Клиент или там гкуда они возвращаются.
        return mousePoint;
    }


}
