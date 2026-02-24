package org.example.nttdata.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/{id}/validar")
    @Operation(summary = "Comprobar que el usuario existe y su contraseña.")
    public ResponseEntity<UsuarioDTO> validarUsuario(
            @RequestBody LoginDTO loginRequest) {

        Integer id = loginRequest.getId();
        String cont = loginRequest.getContrasena();
        //Llamamos al servicio para validar credenciales
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioYValidarContrasena(id,cont);

        //Si el servicio no lanza excepción, devolvemos el usuario 200 OK
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/{idUsuario}/sucursal")
    @Operation(summary = "Cambiar sucursal del usuario.")
    public ResponseEntity<Boolean> cambiarSucursal(
            @PathVariable Integer idUsuario,
            @RequestBody Integer idSucursal) {

        //Ejecutamos el cambio y guardamos resultado
        Boolean actualizado = usuarioService.cambiarIdSucursalUsuario(idUsuario, idSucursal);

        //Si es false (error en el servicio), devolvemos un 400 Bad Request
        if (Boolean.FALSE.equals(actualizado)) {
            return ResponseEntity.badRequest().body(false);
        }

        //Si todo ok, 200 OK
        return ResponseEntity.ok(actualizado);
    }
}