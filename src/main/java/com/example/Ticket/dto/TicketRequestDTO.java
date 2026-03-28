package com.example.Ticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de ENTRADA (Request) - Java Record.
 *
 * Recibe el JSON del cliente al crear un ticket.
 * Las anotaciones de @Valid disparan las validaciones automaticamente.
 *
 * Ejemplo de JSON esperado:
 * {
 *   "usuario": "Juan Perez",
 *   "descripcion": "No puedo acceder al sistema",
 *   "prioridad": "ALTA"
 * }
 */
public record TicketRequestDTO(

        @NotBlank(message = "El usuario es obligatorio")
        @Size(max = 100, message = "El usuario no puede exceder 100 caracteres")
        String usuario,

        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,

        @NotBlank(message = "La prioridad es obligatoria")
        @Size(max = 20, message = "La prioridad no puede exceder 20 caracteres")
        String prioridad
) {
}
