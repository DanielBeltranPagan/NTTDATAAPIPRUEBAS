package org.example.nttdata.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Crear reserva de puesto.")
    public ResponseEntity<ReservaPuestoDTO> crearReservaPuesto(@RequestBody ReservaPuestoDTO dto) {
        //Llamada al servicio para guardar la reserva
        ReservaPuestoDTO reservaCreada = reservaPuestoService.crearReservaPuesto(dto);

        //Retorno de 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCreada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reserva de puesto.")
    public ResponseEntity<Void> eliminarReservaPuesto(@PathVariable Integer id) {
        //Borramos por ID
        reservaPuestoService.eliminarReservaPorIdReserva(id);

        //Retorno de 204 No Content
        return ResponseEntity.noContent().build();
    }
}