package ru.hardy.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

        Process windowsList = null;
        try {
			windowsList = Runtime.getRuntime().exec("wmctrl -l");
			windowsList.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(windowsList.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
//				if (line.contains("Firefox")){
//					browsersIdList.add(line.substring(0, 9));
//				}
			}
			reader.close();




        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

//		new Browser(1).runBrowser();
	}
}

