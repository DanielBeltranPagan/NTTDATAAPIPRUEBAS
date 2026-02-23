package org.example.nttdata.controller;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.PlantaDTO;
import org.example.nttdata.service.PlantaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

//Error
@RestController
@RequestMapping("/api/plantas")
@RequiredArgsConstructor
public class PlantaRestController {

    private final PlantaService plantaService;

    @GetMapping("/{idPlanta}/estado")
    public ResponseEntity<PlantaDTO> obtenerEstadoPlanta(
            @PathVariable Integer idPlanta,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        // 1. Obtenemos el DTO con la lógica de filtrado del servicio
        PlantaDTO plantaDTO = plantaService.obtenerEstadoPlanta(idPlanta, fecha);

        // 2. Retornamos 200 OK con el cuerpo del DTO
        return ResponseEntity.ok(plantaDTO);
    }
}
