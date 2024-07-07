package ru.hardy.server.service.frame;

import lombok.extern.slf4j.Slf4j;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReadFirstMouse {

    private static final Logger log = LoggerFactory.getLogger(ReadFirstMouse.class);

    public ReadFirstMouse() {
        /* Get the available controllers */
        Controller[] controllers = ControllerEnvironment
                .getDefaultEnvironment().getControllers();

        /*
         * Loop through the controllers, check the type of each one, and save
         * the first mouse we find.
         */
        Controller firstMouse = null;
        for (int i = 0; i < controllers.length && firstMouse == null; i++) {
            if (controllers[i].getType() == Controller.Type.MOUSE) {
                // Found a mouse
                firstMouse = controllers[i];
                log.info("------------------i");
            }
        }
        if (firstMouse == null) {
            // Couldn't find a mouse
            System.out.println("Found no mouse");
            System.exit(0);
        }

        System.out.println("First mouse is: " + firstMouse.getName());

        while (true) {
            /* Poll the controller */
            firstMouse.poll();

            /* Get all the axis and buttons */
            Component[] components = firstMouse.getComponents();
            StringBuffer buffer = new StringBuffer();

            /* For each component, get it's name, and it's current value */
            for (int i = 0; i < components.length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }
                buffer.append(components[i].getName());
                buffer.append(": ");
                if (components[i].isAnalog()) {
                    /* Get the value at the last poll of this component */
                    buffer.append(components[i].getPollData());
                } else {
                    if (components[i].getPollData() == 1.0f) {
                        buffer.append("On");
                    } else {
                        buffer.append("Off");
                    }
                }
            }
            System.out.println(buffer.toString());

            /*
             * Sleep for 20 millis, this is just so the example doesn't thrash
             * the system.
             */
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
