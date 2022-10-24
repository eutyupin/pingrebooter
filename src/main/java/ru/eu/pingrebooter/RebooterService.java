package ru.eu.pingrebooter;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class RebooterService {
    private final Util util;
    private Logger logger = Logger.getLogger(RebooterService.class.getName());
    private final String DEFAULT_IP = "8.8.8.8";

    @Scheduled(cron = "@hourly") // "fixedDelay = 60*60*1000" --/or/--  cron = "@hourly"  --/or/--  cron = "${name-of-the-cron:0 0/30 * * * ?}"
    public void computePrice() throws InterruptedException {
        if (util.pingIPAddress(DEFAULT_IP)) {
           logger.info(" -- command: ping " + DEFAULT_IP + " returns 'OK' status, don't need to reboot.");
        } else {
            util.rebootSystem();
        }
    }

}
