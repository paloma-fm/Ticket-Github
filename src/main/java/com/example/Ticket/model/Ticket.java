package com.example.Ticket.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime; // Importante

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String descripcion;
    private String prioridad;
    private String estado;

    // ESTO ES LO QUE FALTA:
    private LocalDateTime fechaCreacion;

    // Este método se ejecuta solo antes de guardar en la DB
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}