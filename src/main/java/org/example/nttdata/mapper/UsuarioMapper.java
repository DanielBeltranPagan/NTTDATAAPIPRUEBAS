package org.example.nttdata.mapper;

import org.example.nttdata.dto.UsuarioDTO;
import org.example.nttdata.dto.ReservaSalaDTO;
import org.example.nttdata.dto.ReservaPuestoDTO;
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


        // Mapeo de Reservas de Puestos
        if (usuario.getReservasPuesto() != null) {
            dto.setReservasPuestos(usuario.getReservasPuesto().stream().map(res -> {
                ReservaPuestoDTO rDto = new ReservaPuestoDTO();
                rDto.setIdReserva(res.getIdReserva());
                rDto.setFecha(res.getFecha());
                rDto.setHoraInicio(res.getHoraInicio());
                rDto.setHoraFin(res.getHoraFin());
                rDto.setIdPuesto(res.getPuestoTrabajo().getIdPuesto());
                rDto.setIdUsuario(usuario.getIdUsuario());
                return rDto;
            }).toList());
        }

        if (usuario.getReservasSala() != null) {
            dto.setReservasSalas(usuario.getReservasSala().stream().map(res -> {
                ReservaSalaDTO sDto = new ReservaSalaDTO();
                sDto.setIdReserva(res.getIdReserva());
                sDto.setFecha(res.getFecha());
                sDto.setHoraInicio(res.getHoraInicio());
                sDto.setIdSala(res.getSala().getIdSala());
                sDto.setIdUsuario(usuario.getIdUsuario());
                return sDto;
            }).toList());
        }

        return dto;
    }
}