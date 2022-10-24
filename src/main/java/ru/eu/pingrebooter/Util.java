package ru.eu.pingrebooter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Util {

    public boolean pingIPAddress(String ip) {
        Process p1 = null;
        boolean pingResult = false;
        try {
            p1 = Runtime.getRuntime().exec("ping -c 4 8.8.8.8");
            int returnVal = p1.waitFor();
            pingResult = (returnVal==0);
        } catch (Exception e) {
            pingResult = false;
        }
        return pingResult;
    }

    public void rebootSystem() {
        try {
            Runtime.getRuntime().exec("shutdown -r now");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
