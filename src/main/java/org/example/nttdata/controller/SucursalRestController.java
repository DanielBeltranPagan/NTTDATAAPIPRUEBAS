package org.example.nttdata.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.SucursalDTO;
import org.example.nttdata.service.SucursalService;
import org.example.nttdata.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
public class SucursalRestController {

    private final SucursalService sucursalService;
    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener todas las sucursales.")
    public ResponseEntity<List<SucursalDTO>> obtenerTodasLasSucursales() {
        List<SucursalDTO> sucursales = sucursalService.obtenerTodasLasSucursales();
        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        //Retornamos 200 OK la información
        return ResponseEntity.ok(sucursales);
    }
}