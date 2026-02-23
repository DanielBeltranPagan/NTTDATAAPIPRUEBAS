package org.example.nttdata.controller;

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
    public ResponseEntity<List<SucursalDTO>> obtenerTodasLasSucursales() {
        List<SucursalDTO> sucursales = sucursalService.obtenerTodasLasSucursales();
        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales);
    }

    // Endpoint para cambiar la sucursal de un usuario
    // PUT /api/sucursales/usuarios/5?idSucursal=2
    @PutMapping("/usuarios/{idUsuario}")
    public ResponseEntity<Boolean> cambiarSucursalUsuario(
            @PathVariable Integer idUsuario,
            @RequestParam Integer idSucursal) {

        Boolean resultado = usuarioService.cambiarIdSucursalUsuario(idUsuario, idSucursal);

        if (Boolean.FALSE.equals(resultado)) {
            // Si el servicio devuelve false, algo fue mal (IDs no encontrados, etc.)
            return ResponseEntity.badRequest().body(false);
        }

        return ResponseEntity.ok(resultado);
    }
}