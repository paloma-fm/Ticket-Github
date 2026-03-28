package com.example.Ticket.service;

import com.example.Ticket.dto.TicketEstadoDTO;
import com.example.Ticket.dto.TicketRequestDTO;
import com.example.Ticket.dto.TicketResponseDTO;

/**
 * CAPA SERVICE - Interfaz.
 *
 * Define el CONTRATO de operaciones del sistema de tickets.
 * El Controller depende de esta interfaz, NO de la implementacion concreta.
 *
 * Principio SOLID aplicado: Dependency Inversion
 *   → "Depender de abstracciones, no de implementaciones"
 */
public interface TicketService {

    /**
     * API 1: Registrar un nuevo ticket.
     * - Recibe los datos del JSON (TicketRequestDTO)
     * - Guarda en la BD con estado ABIERTO
     * - Notifica via log (simula correo/WhatsApp)
     * - Retorna los datos del ticket creado (TicketResponseDTO)
     */
    TicketResponseDTO registrarTicket(TicketRequestDTO request);

    /**
     * API 2: Consultar estado de un ticket por ID.
     * - Recibe el ID por URL
     * - Retorna estado + descripcion (TicketEstadoDTO)
     */
    TicketEstadoDTO consultarEstado(Long id);
}

