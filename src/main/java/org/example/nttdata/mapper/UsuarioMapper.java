package org.example.nttdata.mapper;

import org.example.nttdata.dto.UsuarioDTO;
import org.example.nttdata.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {

    private final SucursalMapper sucursalMapper;

    @Autowired
    @Lazy
    public UsuarioMapper(SucursalMapper sucursalMapper) {
        this.sucursalMapper = sucursalMapper;
    }

    public UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setCorreo(usuario.getCorreo());
        dto.setContrasena(usuario.getContrasena());
        dto.setRango(usuario.getRango());

        dto.setSucursal(sucursalMapper.toDto(usuario.getSucursal()));

        dto.setReservasSalas(usuario.getReservasSala());
        dto.setReservasPuestos(usuario.getReservasPuesto());

        return dto;
    }
}