package ru.hardy.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.*;

import static java.lang.Thread.sleep;

@Slf4j
@Component
public class Conductor {
    private List<Browser> browserList= new ArrayList<>();
//    String frequency = "${server.frequency}";
    String frequency = "5";

    Integer fr = Integer.parseInt(frequency);
    private int waitingTime = 1000/fr;
    private int quantityBrousers = 3;
    private Set<String> pidSet = new HashSet<>();

    public void  on(){

        for(int k=1; k<=quantityBrousers; k++){
            try {
                Browser browser = new Browser(k, fr);
                browserList.add(browser);


            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



        try {
            resizeAndMoveBrowsers();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //todo Размешение мыши и запуск ее по кругу 

    }

    private void resizeAndMoveBrowsers() throws IOException, InterruptedException {
        sleep(5000);
        // получение списка PID  процесоов Firefox
        Process processList = Runtime.getRuntime().exec("wmctrl -l");
        sleep(1000);
        List<String> pidList = new ArrayList<>();
        processList.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(processList.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.contains("Firefox")){
                pidList.add(line.substring(0, 10));
            }
        }
        reader.close();
        log.info("PID LIST - {}", pidList);

        //Сопоставляем полученные PID c окнами Firefox
        for(int i = 0; i < browserList.size(); i++){
            browserList.get(i).setPid(pidList.get(i));

        }
        log.info("BROUSER LIST - {}", browserList);
        //Перемещение  окон Firefox по расчетным координатам
        for(Browser browser: browserList){
            Integer x = (int) browser.getUpperLeftCorner().getX();
            Integer y = (int) browser.getUpperLeftCorner().getY();

            System.out.println("----- wmctrl -r "+browser.getPid()+" -i-e 0,"+ x +","+y+","+browser.getWidthBrowser()+","+browser.getHeightBrowser());
            Process resizeMovingProcess = Runtime.getRuntime().exec("wmctrl -r "+browser.getPid()+" -i -e 0,"+ x +","+y+","+browser.getWidthBrowser()+","+browser.getHeightBrowser());

            resizeMovingProcess.waitFor();


            //-----------------------------todo
            browser.runCursor();

        }

        //ps -efH
        //wmctrl -l
        //wmctrl -p -G -l
        //
         // работает wmctrl -r 0x0380007e -i -e 0,560,0,560,0
//        http://localhost:8080/swagger-ui/index.html

         //  wmctrl -r -i 34953 -i -e 0,560,0,560,0
    }

}
