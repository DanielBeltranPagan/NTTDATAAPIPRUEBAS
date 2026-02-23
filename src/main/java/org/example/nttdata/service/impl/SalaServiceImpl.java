package org.example.nttdata.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.SalaResumenDTO;
import org.example.nttdata.mapper.SalaResumenMapper;
import org.example.nttdata.model.Sala;
import org.example.nttdata.repository.SalaRepository;
import org.example.nttdata.service.SalaService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;
    private final SalaResumenMapper salaResumenMapper;

    @Override
    public SalaResumenDTO obtenerInformacionSala(Integer idSala) {
        Sala sala = salaRepository.findById(idSala)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        return salaResumenMapper.toResumenDto(sala);
    }
}
