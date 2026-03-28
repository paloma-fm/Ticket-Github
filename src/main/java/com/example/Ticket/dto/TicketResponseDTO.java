package com.example.Ticket.dto;

import java.time.LocalDateTime;

/**
 * DTO de SALIDA (Response) - Java Record.
 *
 * Se retorna al cliente despues de CREAR un ticket.
 * Contiene todos los datos del ticket guardado.
 *
 * Ejemplo de JSON de respuesta:
 * {
 *   "id": 1,
 *   "usuario": "Juan Perez",
 *   "descripcion": "No puedo acceder al sistema",
 *   "prioridad": "ALTA",
 *   "estado": "ABIERTO",
 *   "fechaCreacion": "2026-03-11T20:30:00"
 * }
 */
public record TicketResponseDTO(
        Long id,
        String usuario,
        String descripcion,
        String prioridad,
        String estado,
        LocalDateTime fechaCreacion
) {
}
