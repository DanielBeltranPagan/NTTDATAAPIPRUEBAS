package org.example.nttdata.service;

import org.example.nttdata.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO obtenerUsuarioPorCorreoYValidarContrasena(String correo, String contrasenaIntroducida);
    Boolean cambiarIdSucursalUsuario(Integer idUsuario, Integer idSucursal);
}
