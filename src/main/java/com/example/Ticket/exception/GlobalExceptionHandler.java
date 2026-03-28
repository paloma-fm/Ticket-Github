package com.example.Ticket.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * MANEJADOR GLOBAL DE EXCEPCIONES.
 *
 * @RestControllerAdvice intercepta TODAS las excepciones
 * lanzadas en cualquier Controller y las convierte en
 * respuestas JSON con formato uniforme (ErrorResponse).
 *
 * Ventajas:
 *   - Centraliza el manejo de errores en UN solo lugar
 *   - Elimina la necesidad de try-catch en cada Controller
 *   - El cliente siempre recibe el mismo formato de error
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ==========================================
    // 404: Ticket no encontrado
    // ==========================================
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<com.example.Ticket.exception.ErrorResponse> handleRecursoNoEncontrado(
            RecursoNoEncontradoException ex,
            HttpServletRequest request) {

        com.example.Ticket.exception.ErrorResponse error = new com.example.Ticket.exception.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso No Encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // ==========================================
    // 400: Errores de validacion (@Valid)
    // Ejemplo: campo "usuario" vacio, "prioridad" null, etc.
    // ==========================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<com.example.Ticket.exception.ErrorResponse> handleValidacion(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String mensajes = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        com.example.Ticket.exception.ErrorResponse error = new com.example.Ticket.exception.ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error de Validacion",
                mensajes,
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // ==========================================
    // 409: Violacion de integridad en la BD
    // Ejemplo: constraint UNIQUE violado
    // ==========================================
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<com.example.Ticket.exception.ErrorResponse> handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        com.example.Ticket.exception.ErrorResponse error = new com.example.Ticket.exception.ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Error de Integridad de Datos",
                "Operacion rechazada por la base de datos. Verifique los datos enviados.",
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // ==========================================
    // 500: Cualquier otra excepcion no controlada
    // ==========================================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.example.Ticket.exception.ErrorResponse> handleGeneral(
            Exception ex,
            HttpServletRequest request) {

        com.example.Ticket.exception.ErrorResponse error = new com.example.Ticket.exception.ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error Interno del Servidor",
                "Ocurrio un error inesperado. Contacte al administrador.",
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
