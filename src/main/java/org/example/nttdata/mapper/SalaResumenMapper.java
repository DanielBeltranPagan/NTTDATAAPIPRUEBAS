package org.example.nttdata.mapper;

import org.example.nttdata.dto.SalaResumenDTO;
import org.example.nttdata.model.Sala;
import org.springframework.stereotype.Component;

@Component
public class SalaResumenMapper {

    public SalaResumenDTO toResumenDto(Sala sala) {
        if (sala == null) return null;

        SalaResumenDTO dto = new SalaResumenDTO();
        dto.setCantidadAsientos(sala.getCantidadAsientos());
        dto.setProyector(sala.getProyector());

        return dto;
    }
}
