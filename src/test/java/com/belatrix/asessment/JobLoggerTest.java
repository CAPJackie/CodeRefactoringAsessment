package com.belatrix.asessment;

import com.belatrix.asessment.controller.JobLogger;
import com.belatrix.asessment.exception.LogException;
import com.belatrix.asessment.services.impl.ConsoleLog;
import com.belatrix.asessment.services.impl.DatabaseLog;
import com.belatrix.asessment.services.impl.FileLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class JobLoggerTest {

    private static Logger log = Logger.getLogger(JobLogger.class.getName());
    private static OutputStream logCapturingStream;
    private static StreamHandler customLogHandler;

    @Before
    public void attachLogCapturer()
    {
        logCapturingStream = new ByteArrayOutputStream();
        Handler[] handlers = log.getParent().getHandlers();
        customLogHandler = new StreamHandler(logCapturingStream, handlers[0].getFormatter());
        log.addHandler(customLogHandler);
    }

    private String getTestCapturedLog() throws IOException
    {
        customLogHandler.flush();
        return logCapturingStream.toString();
    }

    @Test
    public void itShouldLogByConsole() throws LogException, IOException {
        JobLogger.setLogSiteService(new ConsoleLog());
        String message = "The variable b is declared but it is not used";

        JobLogger.LogMessage(message, JobLogger.WARNING_LOG);

        String capturedLog = getTestCapturedLog();
        System.out.println(capturedLog);
        Assert.assertTrue(capturedLog.contains(message));

    }


    @Test
    public void itShouldLogOnAFile() throws LogException, IOException {
        JobLogger.setLogSiteService(new FileLog());
        String message = "Syntax mismatch on line 45,43";

        JobLogger.LogMessage(message, JobLogger.ERROR_LOG);


        String capturedLog = getTestCapturedLog();
        Assert.assertTrue(capturedLog.contains(message));

    }


    @Test
    public void itShouldLogOnTheDatabase() throws LogException{
        JobLogger.setLogSiteService(new DatabaseLog());
        String message = "Configuration added to the core project";

        JobLogger.LogMessage(message, JobLogger.MESSAGE_LOG);

        Connection connection = null;
        Map dbParams = JobLogger.getDbParams();
        Properties connectionProps = new Properties();
        connectionProps.put("user", dbParams.get("userName"));
        connectionProps.put("password", dbParams.get("password"));


        try {
            connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
                    + ":" + dbParams.get("portNumber") + "/", connectionProps);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Log_Values WHERE message = '" + message + "' ");
            Assert.assertTrue(rs.getString("message").contains(message));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
