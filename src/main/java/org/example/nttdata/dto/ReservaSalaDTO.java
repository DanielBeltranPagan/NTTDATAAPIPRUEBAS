package org.example.nttdata.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservaSalaDTO {
    private Integer idReserva;
    private Integer idSala;
    private Integer idUsuario;
    private LocalDate fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
}
