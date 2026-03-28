package com.example.Ticket.exception;

/**
 * Excepcion personalizada: Recurso no encontrado.
 * Se lanza cuando un ticket con el ID solicitado no existe en la BD.
 * El GlobalExceptionHandler la captura y retorna HTTP 404.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
