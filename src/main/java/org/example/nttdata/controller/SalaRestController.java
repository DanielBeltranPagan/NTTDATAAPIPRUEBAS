package org.example.nttdata.controller;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.SalaResumenDTO;
import org.example.nttdata.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaRestController {

    private final SalaService salaService;

    @GetMapping("/{idSala}")
    public ResponseEntity<SalaResumenDTO> obtenerInformacionSala(@PathVariable Integer idSala) {

        // 1. Obtenemos el resumen desde el servicio
        SalaResumenDTO salaResumen = salaService.obtenerInformacionSala(idSala);

        // 2. Retornamos la información (200 OK)
        return ResponseEntity.ok(salaResumen);
    }
}