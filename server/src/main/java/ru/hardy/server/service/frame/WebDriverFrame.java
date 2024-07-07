package ru.hardy.server.service.frame;

import lombok.Getter;

import java.awt.*;


// POJO описывает все координаты  созданного WebDriver
@Getter
public class WebDriverFrame {
    private Point point00;
    private Point point01;
    private Point point10;
    private Point point11;
    private Point pointCenter;
    private int width;
    private int height;

    public WebDriverFrame(int width, int height, int positionX, int positionY) {
        this.width = width;
        this.height = height;

        this.point00 = new Point();
        this.point00.setLocation(positionX, positionY);


        this.point01 = new Point();
        this.point01.setLocation((positionX+width), positionY);


        this.point10 = new Point();
        this.point10.setLocation(positionX, (positionY+height));


        this.point11 = new Point();
        this.point11.setLocation((positionX+width), (positionY+height));


        this.pointCenter = new Point();
        this.pointCenter.setLocation(((point00.x + point01.x)/2), ((point00.y + point10.y)/2));

    }
}
