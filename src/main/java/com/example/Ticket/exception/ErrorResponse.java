package com.example.Ticket.exception;

import java.time.LocalDateTime;

/**
 * Estructura uniforme para TODAS las respuestas de error.
 * El cliente siempre recibe el mismo formato JSON de error.
 *
 * Ejemplo:
 * {
 *   "status": 404,
 *   "error": "Recurso No Encontrado",
 *   "mensaje": "Ticket no encontrado con ID: 99",
 *   "path": "/api/tickets/99",
 *   "timestamp": "2026-03-11T20:45:00"
 * }
 */
public record ErrorResponse(
        int status,
        String error,
        String mensaje,
        String path,
        LocalDateTime timestamp
) {
}
