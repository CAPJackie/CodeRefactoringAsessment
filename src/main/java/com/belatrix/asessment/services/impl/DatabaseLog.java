package com.belatrix.asessment.services.impl;

import com.belatrix.asessment.services.LogSiteService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseLog implements LogSiteService {
    @Override
    public void log(String message, Logger logger, Map dbParams, int messageType) {

        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", dbParams.get("userName"));
        connectionProps.put("password", dbParams.get("password"));


        try {
            connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
                    + ":" + dbParams.get("portNumber") + "/", connectionProps);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into Log_Values('" + message + "', " + String.valueOf(messageType) + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
