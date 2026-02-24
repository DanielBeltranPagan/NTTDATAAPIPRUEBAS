package org.example.nttdata.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.ReservaSalaDTO;
import org.example.nttdata.service.ReservaSalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas-salas")
@RequiredArgsConstructor
public class ReservaSalaRestController {

    private final ReservaSalaService reservaSalaService;

    @PostMapping
    @Operation(summary = "Crear reserva de sala.")
    public ResponseEntity<ReservaSalaDTO> crearReservaSala(@RequestBody ReservaSalaDTO dto) {
        // 1. Creamos la reserva a través del servicio
        ReservaSalaDTO reservaCreada = reservaSalaService.crearReservaSala(dto);

        // 2. Retornamos 201 Created con el objeto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCreada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reserva de sala.")
    public ResponseEntity<Void> eliminarReservaSala(@PathVariable Integer id) {
        // 1. Ejecutamos la eliminación
        reservaSalaService.eliminarReservaPorIdReserva(id);

        // 2. Retornamos 204 No Content
        return ResponseEntity.noContent().build();
    }
}