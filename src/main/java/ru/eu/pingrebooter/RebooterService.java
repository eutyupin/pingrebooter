package ru.eu.pingrebooter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class RebooterService {
    private final Util util;
    @Value("${commands.systemRestart}")
    private String systemRestartCommand;
    @Value("${commands.serviceRestart}")
    private String serviceRestartCommand;
    @Value("${commands.ping}")
    private String pingCommand;
    @Value("${commands.defaultIp}")
    private String defaultIp;
    private Logger logger = Logger.getLogger(RebooterService.class.getName());

    @Scheduled(fixedDelay = 5000) // "fixedDelay = 60*60*1000" --/or/--  cron = "@hourly"  --/or/--  cron = "0 0/20 * * * *" --every 20 minutes
    public void checkConnection() {
        if (util.pingIPAddress(pingCommand + " " + defaultIp)) {
           logger.info(" -- command: ping " + defaultIp + " returns 'OK' status, don't need to reboot.");
        } else {
            util.rebootSystem(systemRestartCommand);
        }
    }

    @Scheduled(cron = "0 0/30 * * * *") //restart comm_machine service every 30 minutes
    public void restartCommMachine() {
        util.restartCommMachine(serviceRestartCommand);
        logger.info("comm_machine has been restarted");
    }

}
