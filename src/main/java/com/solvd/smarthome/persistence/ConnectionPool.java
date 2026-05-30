package com.solvd.smarthome.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static final int POOL_SIZE = 5;
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> connections;

    private ConnectionPool() {
        connections = new LinkedBlockingQueue<>(POOL_SIZE);
        try {
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            props.load(input);
            String driver = props.getProperty("db.driver");
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            Class.forName(driver);
            for (int i = 0; i < POOL_SIZE; i++) {
                connections.add(DriverManager.getConnection(url, username, password));
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to get connection", e);
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to release connection", e);
        }
    }
}