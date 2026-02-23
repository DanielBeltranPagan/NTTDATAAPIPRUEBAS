package org.example.nttdata.mapper;

import org.example.nttdata.dto.ReservaPuestoDTO;
import org.example.nttdata.model.PuestoTrabajo;
import org.example.nttdata.model.ReservaPuesto;
import org.example.nttdata.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ReservaPuestoMapper {
    public ReservaPuestoDTO toDto(ReservaPuesto reservaPuesto) {
        if (reservaPuesto == null) return null;
        ReservaPuestoDTO dto = new ReservaPuestoDTO();
        dto.setIdReserva(reservaPuesto.getIdReserva());
        dto.setFecha(reservaPuesto.getFecha());
        dto.setHoraInicio(reservaPuesto.getHoraInicio());
        dto.setHoraFin(reservaPuesto.getHoraFin());

        dto.setIdPuesto(reservaPuesto.getPuestoTrabajo() != null ? reservaPuesto.getPuestoTrabajo().getIdPuesto() : null);
        dto.setIdUsuario(reservaPuesto.getUsuario() != null ? reservaPuesto.getUsuario().getIdUsuario() : null);

        return dto;
    }
    public ReservaPuesto toEntity(ReservaPuestoDTO dto, PuestoTrabajo puesto, Usuario usuario) {
        if (dto == null) return null;

        ReservaPuesto reservaPuesto = new ReservaPuesto();
        reservaPuesto.setFecha(dto.getFecha());
        reservaPuesto.setHoraInicio(dto.getHoraInicio());
        reservaPuesto.setHoraFin(dto.getHoraFin());

        reservaPuesto.setPuestoTrabajo(puesto);
        reservaPuesto.setUsuario(usuario);

        return reservaPuesto;
    }
}
