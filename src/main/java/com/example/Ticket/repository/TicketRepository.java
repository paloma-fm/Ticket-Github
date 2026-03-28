package com.example.Ticket.repository;

import com.example.Ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CAPA REPOSITORY.
 *
 * Interfaz que extiende JpaRepository.
 * Spring Data JPA genera la implementacion automaticamente.
 *
 * Metodos heredados disponibles:
 *   - save(entity)      → INSERT / UPDATE
 *   - findById(id)      → SELECT ... WHERE id = ?
 *   - findAll()          → SELECT *
 *   - deleteById(id)     → DELETE ... WHERE id = ?
 *   - existsById(id)     → SELECT COUNT(*) > 0
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // No necesitamos metodos personalizados por ahora.
    // JpaRepository ya provee todo lo que las APIs requieren.
}
