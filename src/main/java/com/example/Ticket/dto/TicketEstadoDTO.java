package com.example.Ticket.dto;

/**
 * DTO de SALIDA para consulta de estado - Java Record.
 *
 * Se retorna al consultar GET /api/tickets/{id}.
 * Solo expone el estado y la descripcion (segun requerimiento).
 *
 * Ejemplo de JSON:
 * {
 *   "id": 1,
 *   "estado": "ABIERTO",
 *   "descripcion": "No puedo acceder al sistema"
 * }
 */
public record TicketEstadoDTO(
        Long id,
        String estado,
        String descripcion
) {
}

