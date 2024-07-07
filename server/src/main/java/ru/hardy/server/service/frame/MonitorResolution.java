package ru.hardy.server.service.frame;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Slf4j
@Getter
public class MonitorResolution {

    // Графическое устройство в принципе этого было бы достатчно
    GraphicsDevice graphicsDevice;
    //разрешение монитора по горизонтали (ширина)
    private int width = 0;
    //разрешение монитора по вертикали (высота)
    private int height = 0;

    // так как у меня два монитора
    public void selectionMonitor(){

        System.setProperty("java.awt.headless", "false");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        log.info(" Получены графические устройства");
        for (int i = 0; i < gs.length; i++) {
            DisplayMode dm = gs[i].getDisplayMode();
            System.out.println(i + ", width: " + dm.getWidth() + ", height: " + dm.getHeight());
            if (width < dm.getWidth()){
                width = dm.getWidth();
                height = dm.getHeight();
                graphicsDevice = gs[i];
            }
            width = 1680;
            height = 1050;
        }
    }

    protected void selectionMainMonitor(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        log.info(" selected Monitor : width - {}, height - {}" , width, height);
    }

}
