package org.example.nttdata.controller;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.ReservaPuestoDTO;
import org.example.nttdata.service.ReservaPuestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas-puestos")
@RequiredArgsConstructor
public class ReservaPuestoRestController {

    private final ReservaPuestoService reservaPuestoService;

    @PostMapping
    public ResponseEntity<ReservaPuestoDTO> crearReservaPuesto(@RequestBody ReservaPuestoDTO dto) {
        // 1. Llamada al servicio para persistir la reserva
        ReservaPuestoDTO reservaCreada = reservaPuestoService.crearReservaPuesto(dto);

        // 2. Retorno de 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCreada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReservaPuesto(@PathVariable Integer id) {
        // 1. Eliminación por ID
        reservaPuestoService.eliminarReservaPorIdReserva(id);

        // 2. Retorno de 204 No Content
        return ResponseEntity.noContent().build();
    }
}