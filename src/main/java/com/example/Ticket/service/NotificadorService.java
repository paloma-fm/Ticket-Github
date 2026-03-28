package com.example.Ticket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * COMPONENTE DE NOTIFICACION.
 *
 * Simula el envio de notificaciones (correo / WhatsApp)
 * usando SLF4J (el logger que viene con Spring Boot).
 *
 * En produccion, aqui se integraria con:
 *   - JavaMailSender para correos reales
 *   - API de Twilio para WhatsApp
 *   - Firebase Cloud Messaging para push notifications
 *
 * Por ahora, SIMULA la notificacion escribiendo en el log del servidor.
 */
@Service
public class NotificadorService {

    // SLF4J Logger - imprime en la consola del servidor
    private static final Logger log = LoggerFactory.getLogger(NotificadorService.class);

    /**
     * Simula el envio de un correo electronico.
     * El mensaje aparece en la consola como un log INFO.
     */
    public void enviarCorreo(Long ticketId, String usuario) {
        log.info("=============================================================");
        log.info("  SIMULACION DE CORREO ELECTRONICO");
        log.info("  Para: {}", usuario);
        log.info("  Asunto: Ticket #{} creado exitosamente", ticketId);
        log.info("  Mensaje: Hola {}, tu ticket #{} ha sido registrado", usuario, ticketId);
        log.info("           y se encuentra en estado ABIERTO.");
        log.info("           Te notificaremos cuando haya una actualizacion.");
        log.info("=============================================================");
        log.info("Notificacion enviada para el Ticket ID: {}", ticketId);
    }

    /**
     * Simula el envio de un mensaje de WhatsApp.
     * El mensaje aparece en la consola como un log INFO.
     */
    public void enviarWhatsApp(Long ticketId, String usuario) {
        log.info("=============================================================");
        log.info("  SIMULACION DE WHATSAPP");
        log.info("  Destinatario: {}", usuario);
        log.info("  Mensaje: Ticket #{} creado para el usuario {}.", ticketId, usuario);
        log.info("           Estado actual: ABIERTO.");
        log.info("           Gracias por contactarnos.");
        log.info("=============================================================");
    }
}
