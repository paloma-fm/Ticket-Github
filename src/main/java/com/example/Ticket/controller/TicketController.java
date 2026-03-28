package com.example.Ticket.controller;

import com.example.Ticket.dto.TicketEstadoDTO;
import com.example.Ticket.dto.TicketRequestDTO;
import com.example.Ticket.dto.TicketResponseDTO;
import com.example.Ticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // ==========================================
    // API 1: REGISTRAR TICKET
    // POST http://localhost:8080/api/tickets
    //
    // Body JSON:
    // {
    //   "usuario": "Juan Perez",
    //   "descripcion": "No puedo acceder al sistema",
    //   "prioridad": "ALTA"
    // }
    //
    // Respuesta: 201 CREATED + JSON del ticket creado
    // ==========================================
    @PostMapping
    public ResponseEntity<TicketResponseDTO> registrarTicket(
            @Valid @RequestBody TicketRequestDTO request) {

        TicketResponseDTO respuesta = ticketService.registrarTicket(request);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    // ==========================================
    // API 2: CONSULTAR ESTADO
    // GET http://localhost:8080/api/tickets/1
    //
    // Respuesta: 200 OK + JSON con id, estado y descripcion
    // ==========================================
    @GetMapping("/{id}")
    public ResponseEntity<TicketEstadoDTO> consultarEstado(@PathVariable Long id) {

        TicketEstadoDTO respuesta = ticketService.consultarEstado(id);
        return ResponseEntity.ok(respuesta);
    }
}
