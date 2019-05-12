package com.belatrix.asessment.services.impl;

import com.belatrix.asessment.services.LogSiteService;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLog implements LogSiteService {
    @Override
    public void log(String message, Logger logger, Map dbParams, int messageType) {
        File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
                FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");

                logger.addHandler(fh);
                logger.log(Level.INFO, message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
