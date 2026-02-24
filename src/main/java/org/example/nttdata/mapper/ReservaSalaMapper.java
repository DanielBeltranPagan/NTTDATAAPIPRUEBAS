package org.example.nttdata.mapper;

import org.example.nttdata.dto.ReservaSalaDTO;
import org.example.nttdata.model.ReservaSala;
import org.example.nttdata.model.Sala;
import org.example.nttdata.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ReservaSalaMapper {
    public ReservaSalaDTO toDto(ReservaSala reservaSala) {
        if (reservaSala == null) return null;
        ReservaSalaDTO dto = new ReservaSalaDTO();
        dto.setIdReserva(reservaSala.getIdReserva());
        dto.setFecha(reservaSala.getFecha());
        dto.setHoraInicio(reservaSala.getHoraInicio());
        dto.setHoraFin(reservaSala.getHoraFin());
        dto.setIdSala(reservaSala.getSala() != null ? reservaSala.getSala().getIdSala() : null);
        dto.setIdUsuario(reservaSala.getUsuario() != null ? reservaSala.getUsuario().getIdUsuario() : null);
        return dto;
    }

    public ReservaSala toEntity(ReservaSalaDTO dto, Sala sala, Usuario usuario) {
        if (dto == null) return null;

        ReservaSala reservaSala = new ReservaSala();

        reservaSala.setFecha(dto.getFecha());
        reservaSala.setHoraInicio(dto.getHoraInicio());
        reservaSala.setHoraFin(dto.getHoraFin());

        reservaSala.setSala(sala);
        reservaSala.setUsuario(usuario);

        return reservaSala;
    }
}
