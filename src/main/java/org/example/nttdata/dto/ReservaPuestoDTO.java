package org.example.nttdata.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservaPuestoDTO {
    private Integer idReserva;
    private LocalDate fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Integer idPuesto;
    private Integer idUsuario;
}
