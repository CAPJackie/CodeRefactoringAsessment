package com.belatrix.asessment.services.impl;

import com.belatrix.asessment.services.LogSiteService;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLog implements LogSiteService {
    @Override
    public void log(String message, Logger logger, Map dbParams, int messageType) {
        ConsoleHandler ch = new ConsoleHandler();

        logger.addHandler(ch);
        logger.log(Level.INFO, message);
    }
}
