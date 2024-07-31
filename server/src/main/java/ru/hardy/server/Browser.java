package ru.hardy.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;

//@Component
//@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Browser {

    int id ;
    String pid;
    private Point upperLeftCorner;
    private int widthBrowser;
    private int heightBrowser;

    public Browser(int id, String pid) throws IOException, InterruptedException {
        this.id = id;
        this.pid = pid;
        MonitorResolution monitorResolution = new MonitorResolution();
        widthBrowser = monitorResolution.width/3;
        heightBrowser = monitorResolution.height/2;
        upperLeftCorner = new Point((widthBrowser*(id-1)), 0);
//        расположение окна браузера
        Process resizeMovingProcess = Runtime.getRuntime().exec("wmctrl -i "+pid+" -e 0, "+upperLeftCorner.getX()+", "+upperLeftCorner.getY()+", "+widthBrowser+", "+heightBrowser);
        resizeMovingProcess.waitFor();

    }

    public void runBrowser(){
        try {
            // Запуск через shell Firefox
            Process browser = Runtime.getRuntime().exec("firefox");
            browser.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    class MonitorResolution {

        //разрешение монитора по горизонтали (ширина)
        private int width = 0;
        //разрешение монитора по вертикали (высота)
        private int height = 0;

        protected void getResolution() {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            width = (int) screenSize.getWidth();
            height = (int) screenSize.getHeight();
            log.info(" Разрешение монитора  Monitor : width - {}, height - {}", width, height);
        }

    }
}
