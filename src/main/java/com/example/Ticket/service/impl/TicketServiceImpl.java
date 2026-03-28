package com.example.Ticket.service.impl;

import com.example.Ticket.dto.TicketEstadoDTO;
import com.example.Ticket.dto.TicketRequestDTO;
import com.example.Ticket.dto.TicketResponseDTO;
import com.example.Ticket.exception.RecursoNoEncontradoException;
import com.example.Ticket.model.Ticket;
import com.example.Ticket.repository.DatabaseManager;
import com.example.Ticket.repository.TicketRepository;
import com.example.Ticket.service.NotificadorService;
import com.example.Ticket.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CAPA SERVICE - Implementacion.
 *
 * Contiene TODA la logica de negocio:
 *   1. Convierte DTO → Entity
 *   2. Persiste en BD via Repository
 *   3. Llama al NotificadorService para simular notificacion
 *   4. Convierte Entity → DTO de respuesta
 *
 * FLUJO COMPLETO (API 1 - Registrar):
 *   Controller recibe JSON
 *     → Service convierte DTO a Entity
 *       → Repository guarda en PostgreSQL
 *       → NotificadorService imprime log simulando correo/WhatsApp
 *     → Service convierte Entity a DTO
 *   Controller retorna JSON al cliente
 */
@Service
public class TicketServiceImpl implements TicketService {

    // ==========================================
    // INYECCION POR CONSTRUCTOR (sin @Autowired)
    // ==========================================
    private final TicketRepository ticketRepository;
    private final NotificadorService notificadorService;

    // Spring inyecta ambos beans automaticamente
    // porque solo existe UN constructor
    public TicketServiceImpl(TicketRepository ticketRepository,
                             NotificadorService notificadorService) {
        this.ticketRepository = ticketRepository;
        this.notificadorService = notificadorService;
    }

    // ==========================================
    // API 1: REGISTRAR TICKET
    // ==========================================
    @Override
    @Transactional
    public TicketResponseDTO registrarTicket(TicketRequestDTO request) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        System.out.println("Usando instancia Singleton: " + dbManager.hashCode());

        // PASO 1: Convertir DTO de entrada → Entity
        Ticket ticket = Ticket.builder()
                .usuario(request.usuario())
                .descripcion(request.descripcion())
                .prioridad(request.prioridad().toUpperCase())
                .build();
        // Nota: estado y fechaCreacion se asignan en @PrePersist

        // PASO 2: Repository guarda en PostgreSQL (INSERT)
        Ticket guardado = ticketRepository.save(ticket);

        // PASO 3: Simular notificacion via correo y WhatsApp
        notificadorService.enviarCorreo(guardado.getId(), guardado.getUsuario());
        notificadorService.enviarWhatsApp(guardado.getId(), guardado.getUsuario());

        // PASO 4: Convertir Entity → DTO de respuesta
        return mapToResponseDTO(guardado);
    }

    // ==========================================
    // API 2: CONSULTAR ESTADO
    // ==========================================
    @Override
    @Transactional(readOnly = true)
    public TicketEstadoDTO consultarEstado(Long id) {

        // Buscar en BD; si no existe, lanzar excepcion 404
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Ticket no encontrado con ID: " + id));

        // Retornar solo estado + descripcion
        return new TicketEstadoDTO(
                ticket.getId(),
                ticket.getEstado(),
                ticket.getDescripcion()
        );
    }

    // ==========================================
    // METODO PRIVADO: MAPPER Entity → DTO
    // ==========================================
    private TicketResponseDTO mapToResponseDTO(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getUsuario(),
                ticket.getDescripcion(),
                ticket.getPrioridad(),
                ticket.getEstado(),
                ticket.getFechaCreacion()
        );
    }
}

