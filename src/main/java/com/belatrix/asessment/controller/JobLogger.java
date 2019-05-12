package com.belatrix.asessment.controller;

import com.belatrix.asessment.exception.LogException;
import com.belatrix.asessment.services.LogSiteService;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

public class JobLogger {


    public static final int MESSAGE_LOG = 1;
    public static final int WARNING_LOG = 2;
    public static final int ERROR_LOG = 3;

    private static LogSiteService logSiteService;


    private static int messageType;
    private static Map dbParams;
    private static final Logger logger = Logger.getLogger(JobLogger.class.getName());


    public JobLogger(int messageTypeParam, Map dbParamsMap) {
        JobLogger.messageType = messageTypeParam;
        JobLogger.dbParams = dbParamsMap;
    }

    public static void LogMessage(final String messageText, final int messageTypeParam)
            throws LogException {


        String message = messageText;

        if(message == null){
            return;
        } else{
            message = messageText.trim();
            if (message.length() == 0) {
                return;
            }
        }

        if(logSiteService == null){
            throw new LogException(LogException.INVALID_CONFIG);
        }


        String finalMessage = "";

        if (messageTypeParam == ERROR_LOG) {
            finalMessage += "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + message;
        }

        if (messageTypeParam == WARNING_LOG) {
            finalMessage += "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + message;
        }

        if (messageTypeParam == MESSAGE_LOG) {
            finalMessage += "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + message;
        }


        logSiteService.log(finalMessage, logger, dbParams, JobLogger.messageType);

    }


    public static void setLogSiteService (LogSiteService logSiteService){
        JobLogger.logSiteService = logSiteService;
    }

    public static void setDbParams (Map dbParams){
        JobLogger.dbParams = dbParams;
    }

    public static Map getDbParams(){
        return JobLogger.dbParams;
    }
}
