package org.example.nttdata.mapper;

import org.example.nttdata.dto.SalaDTO;
import org.example.nttdata.model.Sala;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SalaMapper {
    private final ReservaSalaMapper reservaSalaMapper;

    public SalaMapper(ReservaSalaMapper reservaSalaMapper) {
        this.reservaSalaMapper = reservaSalaMapper;
    }

    public SalaDTO toDto(Sala sala) {
        if (sala == null) return null;
        SalaDTO dto = new SalaDTO();
        dto.setIdSala(sala.getIdSala());
        dto.setCantidadAsientos(sala.getCantidadAsientos());
        dto.setProyector(sala.getProyector());

        dto.setReservasSala(sala.getReservasSala());

//        if (sala.getReservasSala() != null) {
//            dto.setReservasSala(sala.getReservasSala().stream()
//                    .map(reservaSalaMapper::toDto)
//                    .collect(Collectors.toList()));
//        }
        return dto;
    }
}
