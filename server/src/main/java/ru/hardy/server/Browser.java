package ru.hardy.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//@Component
//@NoArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
public class Browser {

    Integer id ;
    String pid;
    private Point upperLeftCorner;
    private int widthBrowser;
    private int heightBrowser;
    private Process process;
    private Process cursorProcess;
    private Integer frequency;

    public Browser(int id, Integer frq) throws IOException, InterruptedException {
        this.id = id;
        MonitorResolution monitorResolution = new MonitorResolution();
        monitorResolution.getResolution();
        widthBrowser = monitorResolution.width/3;
        heightBrowser = monitorResolution.height/2;
        upperLeftCorner = new Point((widthBrowser*(id-1)), 0);
        frequency = frq;
        runBrowser();
//        runCursor();



    }

    //запуститьБраузер
    public void runBrowser(){
        try {
            // Запуск Firefox через shell
            Process process = Runtime.getRuntime().exec("firefox");
            this.process = process;
//            process.waitFor();

//        } catch (IOException | InterruptedException e) {
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

//    private setPid(){
//        Set<String> pidList = new HashSet<>();
//    }

    //Запуск мыши по кругу браузера
    public void runCursor(){
        String command = "java/ru/hardy/server/create_pointer "+id.toString()+" "+frequency.toString();
        log.info("Запуск процесс мыщи - {}", command);
        try {
            this.cursorProcess = Runtime.getRuntime().exec(command);
            this.cursorProcess.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.cursorProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

//  Разрешение монитора
    class MonitorResolution {

        //разрешение монитора по горизонтали (ширина)
        private int width = 0;
        //разрешение монитора по вертикали (высота)
        private int height = 0;

        protected void getResolution() {
            System.setProperty("java.awt.headless", "false");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            log.info(" Получены графические устройства");
            for (int i = 0; i < gs.length; i++) {
                DisplayMode dm = gs[i].getDisplayMode();
                System.out.println(i + ", width: " + dm.getWidth() + ", height: " + dm.getHeight());
                if (width ==0 | width > dm.getWidth()){
                    width = dm.getWidth();
                    height = dm.getHeight();
                }
            }
            log.info(" Разрешение монитора  Monitor : width - {}, height - {}", width, height);
        }

    }



}
