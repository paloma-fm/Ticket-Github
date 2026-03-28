package com.example.Ticket.repository;

import com.example.Ticket.model.Ticket;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class TicketRepositoryImpl {

    public Ticket guardar(Ticket ticket) {
        // 1. Pedimos la conexión al Singleton
        Connection conn = DatabaseManager.getInstance().getConnection();

        String sql = "INSERT INTO tickets (usuario, descripcion, prioridad, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, ticket.getUsuario());
            pstmt.setString(2, ticket.getDescripcion());
            pstmt.setString(3, ticket.getPrioridad());
            pstmt.setString(4, "ABIERTO");

            pstmt.executeUpdate();

            // 2. Recuperamos el ID que generó MySQL en Docker
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                ticket.setId(keys.getLong(1));
            }
            System.out.println("Base de Datos Ticket guardado con éxito.");

        } catch (SQLException e) {
            System.err.println("Error DB No se pudo guardar: " + e.getMessage());
        }
        return ticket;
    }
}