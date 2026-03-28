package com.example.Ticket.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            // URL para conectar desde IntelliJ al Docker de MySQL
            String url = "jdbc:mysql://localhost:3306/ticket_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String pass = "rootpassword"; // Debe coincidir con el docker-compose

            this.connection = DriverManager.getConnection(url, user, pass);
            System.out.println(">>> SINGLETON: Conexión exitosa a MySQL en Docker.");
        } catch (SQLException e) {
            throw new RuntimeException("Error: ¿Ya corriste 'docker-compose up -d'?", e);
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}