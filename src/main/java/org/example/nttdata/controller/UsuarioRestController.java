package org.example.nttdata.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.ActualizarSucursalDTO;
import org.example.nttdata.dto.LoginDTO;
import org.example.nttdata.dto.UsuarioDTO;
import org.example.nttdata.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @PostMapping("/validar") // Quitamos el /{id} de la ruta
    public ResponseEntity<UsuarioDTO> validarUsuario(@RequestBody LoginDTO loginRequest) {
        // Extraemos el correo y la contraseña del DTO
        String correo = loginRequest.getCorreo();
        String cont = loginRequest.getContrasena();

        // El servicio ahora debe buscar por correo
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorCorreoYValidarContrasena(correo, cont);

        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/{idUsuario}/sucursal")
    @Operation(summary = "Cambiar sucursal del usuario.")
    public ResponseEntity<Boolean> cambiarSucursal(
            @PathVariable Integer idUsuario,
            @RequestBody ActualizarSucursalDTO datos) { // <-- Cambiado de Integer a DTO

        // Extraemos el id de la sucursal desde el DTO
        Boolean actualizado = usuarioService.cambiarIdSucursalUsuario(idUsuario, datos.getIdSucursal());

        if (Boolean.FALSE.equals(actualizado)) {
            return ResponseEntity.badRequest().body(false);
        }

        return ResponseEntity.ok(actualizado);
    }
}