package org.example.nttdata.service;

import org.example.nttdata.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO obtenerUsuarioYValidarContrasena(Integer id, String contrasenaIntroducida);
    Boolean cambiarIdSucursalUsuario(Integer idUsuario, Integer idSucursal);
}
