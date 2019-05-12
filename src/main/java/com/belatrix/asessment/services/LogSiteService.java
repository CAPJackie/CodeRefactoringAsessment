package com.belatrix.asessment.services;

import java.util.Map;
import java.util.logging.Logger;

public interface LogSiteService {
    void log(String message, Logger logger, Map dbParams, int messageType);
}
