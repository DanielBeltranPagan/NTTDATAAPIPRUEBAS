package org.example.nttdata.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ReservaPuestoDTO {

    private Integer idReserva;

    @NotNull(message = "La fecha de reserva es obligatoria")
    @FutureOrPresent(message = "La reserva debe ser para hoy o una fecha futura")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    @NotNull(message = "El ID del puesto es obligatorio")
    @Positive(message = "El ID del puesto debe ser un número positivo")
    private Integer idPuesto;

    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser un número positivo")
    private Integer idUsuario;
}
