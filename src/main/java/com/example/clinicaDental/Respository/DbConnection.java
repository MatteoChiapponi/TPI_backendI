package com.example.clinicaDental.Respository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;

@Repository
public class DbConnection {
    private static final Logger LOGGER = Logger.getLogger(DbConnection.class);
    static Connection connection;

    public static Connection getConnection() {
        LOGGER.info("intentando establecer una connection");
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("connection exitosa");
            return connection;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    public static void crearTablas() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
