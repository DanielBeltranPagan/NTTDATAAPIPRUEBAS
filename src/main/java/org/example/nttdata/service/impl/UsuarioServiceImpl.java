package org.example.nttdata.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.UsuarioDTO;
import org.example.nttdata.mapper.UsuarioMapper;
import org.example.nttdata.model.Sucursal;
import org.example.nttdata.model.Usuario;
import org.example.nttdata.repository.SucursalRepository;
import org.example.nttdata.repository.UsuarioRepository;
import org.example.nttdata.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final SucursalRepository sucursalRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO obtenerUsuarioPorCorreoYValidarContrasena(String correo, String contrasenaIntroducida){

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuraio no encontrado."));

        if(usuario.getContrasena().equals(contrasenaIntroducida)){
            return usuarioMapper.toDto(usuario);
        } else {
            throw new RuntimeException("Contraseña incorrecta.");
        }
    }

    @Override
    @Transactional
    public Boolean cambiarIdSucursalUsuario(Integer idUsuario, Integer idSucursal) {
        try {

            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Sucursal nuevaSucursal = sucursalRepository.findById(idSucursal)
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

            usuario.setSucursal(nuevaSucursal);

            Usuario usuarioGuardado = usuarioRepository.saveAndFlush(usuario);

            Integer idSucursalGuardada = usuarioGuardado.getSucursal().getIdSucursal();
            boolean esMismoId = idSucursalGuardada.equals(idSucursal);

            return esMismoId;

        } catch (Exception e) {
            return false;
        }
    }
}
