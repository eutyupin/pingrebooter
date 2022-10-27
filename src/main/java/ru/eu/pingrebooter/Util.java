package ru.eu.pingrebooter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Util {

    public boolean pingIPAddress(String pingCommand) {
        Process p1 = null;
        boolean pingResult = false;
        try {
            p1 = Runtime.getRuntime().exec(pingCommand);
            int returnVal = p1.waitFor();
            pingResult = (returnVal==0);
        } catch (Exception e) {
            pingResult = false;
        }
        return pingResult;
    }


    public void rebootSystem(String systemRestartCommand) {
        try {
            Runtime.getRuntime().exec(systemRestartCommand);
        } catch (IOException e) {
            System.out.println(e.getMessage() + " --- " + e.getCause());
        }
    }

    public void restartCommMachine(String serviceRestartCommand) {
        try {
            Runtime.getRuntime().exec(serviceRestartCommand);
        } catch (IOException e) {
            System.out.println(e.getMessage() + " --- " + e.getCause());
        }
    }
}
